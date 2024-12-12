package bus;

import connectDB.ConnectDB;
import dal.*;
import entity.*;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class ReturnOrderDetailBUS {

    private final ReturnOrderDAL returnOrderDAL;
    private final ReturnOrderDetailDAL returnOrderDetailDAL;
    private final BatchDAL batchDAL;
    private BatchBUS batchBUS;
    private final EntityTransaction transaction;

    public ReturnOrderDetailBUS(EntityManager entityManager) {
        this.returnOrderDetailDAL = new ReturnOrderDetailDAL(ConnectDB.getEntityManager());
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();

    }
    
    public ReturnOrderDetail findByReturnOrderIdAndProductId( String returnOrderId, String productId ){
        return returnOrderDAL.findByReturnOrderIdAndProductId(returnOrderId, productId);
    }

    public boolean updateReturnOrderDetailToReturn(ReturnOrderDetail returnOrderDetail) {
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

    public boolean updateReturnOrderDetailToDamage(ReturnOrderDetail returnOrderDetail) {
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
    
    public List<ReturnOrderDetail> getListReturnOrderDetailsByReturnOrder(ReturnOrder returnOrder){
        return returnOrderDetailDAL.getListReturnOrderDetailsByReturnOrder(returnOrder);
    }
    
    public List<ReturnOrderDetail> getListReturnOrderDetailByType(ReturnOrderDetailStatus returnOrderDetailStatus){
        return returnOrderDetailDAL.getALLByStatus(returnOrderDetailStatus);
    }
}
