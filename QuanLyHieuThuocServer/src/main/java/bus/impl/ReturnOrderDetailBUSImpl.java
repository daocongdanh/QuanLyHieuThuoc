package bus.impl;

import bus.ReturnOrderDetailBUS;
import connectDB.ConnectDB;
import dal.BatchDAL;
import dal.ReturnOrderDAL;
import dal.ReturnOrderDetailDAL;
import entity.Batch;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.rmi.RemoteException;

public class ReturnOrderDetailBUSImpl extends UnicastRemoteObject implements ReturnOrderDetailBUS {

    private final ReturnOrderDAL returnOrderDAL;
    private final ReturnOrderDetailDAL returnOrderDetailDAL;
    private final BatchDAL batchDAL;
    private final EntityTransaction transaction;

    public ReturnOrderDetailBUSImpl(EntityManager entityManager)  throws RemoteException {
        this.returnOrderDetailDAL = new ReturnOrderDetailDAL(ConnectDB.getEntityManager());
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId) throws RemoteException {
        return returnOrderDAL.findByReturnOrderIdAndProductId(returnOrderId, productId);
    }

    @Override
    public boolean updateReturnOrderDetailToReturn(ReturnOrderDetail returnOrderDetail) throws RemoteException {
        try {
            transaction.begin();
            ReturnOrderDetail reOld = returnOrderDetailDAL.findByReturnOrderIdAndProductId(returnOrderDetail.getReturnOrder().getReturnOrderId(),
                    returnOrderDetail.getProduct().getProductId());
            reOld.setReturnOrderDetailStatus(ReturnOrderDetailStatus.RETURNED);
            returnOrderDetailDAL.update(reOld);

            ReturnOrder returnOrder = returnOrderDetail.getReturnOrder();
            List<ReturnOrderDetail> detailList = returnOrder.getReturnOrderDetails();
            if (detailList.stream()
                    .allMatch(x -> x.getReturnOrderDetailStatus() != ReturnOrderDetailStatus.PENDING)) {

                returnOrder.setStatus(true);
                returnOrderDAL.update(returnOrder);
            }

            Batch batch = batchDAL.findBatchNearExpirationHaveProductId(returnOrderDetail.getProduct().getProductId());
            batch.setStock(batch.getStock() + returnOrderDetail.getQuantity());
            batchDAL.update(batch);

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateReturnOrderDetailToDamage(ReturnOrderDetail returnOrderDetail) throws RemoteException {
        try {
            transaction.begin();
            ReturnOrderDetail reOld = returnOrderDetailDAL.findByReturnOrderIdAndProductId(returnOrderDetail.getReturnOrder().getReturnOrderId(),
                    returnOrderDetail.getProduct().getProductId());

            reOld.setReturnOrderDetailStatus(ReturnOrderDetailStatus.DAMAGED);
            returnOrderDetailDAL.update(reOld);

            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String name = "TRAHANG" + dateFormat.format(now);
            Batch newBatch = new Batch(null,
                    name, LocalDate.now(),
                    returnOrderDetail.getQuantity(), returnOrderDetail.getProduct(), true);
            batchDAL.insert(newBatch);

            ReturnOrder returnOrder = returnOrderDetail.getReturnOrder();
            List<ReturnOrderDetail> detailList = returnOrder.getReturnOrderDetails();
            if (detailList.stream()
                    .allMatch(x -> x.getReturnOrderDetailStatus() != ReturnOrderDetailStatus.PENDING)) {

                returnOrder.setStatus(true);
                returnOrderDAL.update(returnOrder);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<ReturnOrderDetail> getListReturnOrderDetailsByReturnOrder(ReturnOrder returnOrder) throws RemoteException {
        return returnOrderDetailDAL.getListReturnOrderDetailsByReturnOrder(returnOrder);
    }

    @Override
    public List<ReturnOrderDetail> getListReturnOrderDetailByType(ReturnOrderDetailStatus returnOrderDetailStatus) throws RemoteException {
        return returnOrderDetailDAL.getALLByStatus(returnOrderDetailStatus);
    }
}
