package bus;

import connectDB.ConnectDB;
import dal.*;
import entity.*;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class ReturnOrderDetailBUS {

    private final ProductTransactionHistoryDAL productTransactionHistoryDAL;
    private final ReturnOrderDAL returnOrderDAL;
    private final ReturnOrderDetailDAL returnOrderDetailDAL;
    private final BatchDAL batchDAL;
    private final EntityTransaction transaction;

    public ReturnOrderDetailBUS(EntityManager entityManager) {
        this.returnOrderDetailDAL = new ReturnOrderDetailDAL(ConnectDB.getEntityManager());
        this.productTransactionHistoryDAL = new ProductTransactionHistoryDAL(ConnectDB.getEntityManager());
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();

    }

    public boolean updateReturnOrderDetailToReturn(ReturnOrderDetail returnOrderDetail) {
        try{
            transaction.begin();
            ReturnOrderDetail reOld = returnOrderDetailDAL.findByReturnOrderIdAndUnitDetailIdAndBatchId(returnOrderDetail.getReturnOrder().getReturnOrderId(),
                    returnOrderDetail.getUnitDetail().getUnitDetailId(), returnOrderDetail.getBatch().getBatchId());
            reOld.setReturnOrderDetailStatus(returnOrderDetail.getReturnOrderDetailStatus());
            returnOrderDetailDAL.update(reOld);

            ReturnOrder returnOrder = returnOrderDetail.getReturnOrder();
            List<ReturnOrderDetail> detailList  = returnOrder.getReturnOrderDetails();
            if (detailList.stream()
                    .allMatch( x-> x.getReturnOrderDetailStatus() != ReturnOrderDetailStatus.PENDING)) {

                returnOrder.setStatus(true);
                returnOrderDAL.update(returnOrder);
            }

            Batch batch = returnOrderDetail.getBatch();
            batch.setStock(batch.getStock() + returnOrderDetail.getQuantity());
            batchDAL.update(batch);

            Product product = returnOrderDetail.getUnitDetail().getProduct();

            ProductTransactionHistory productTransactionHistory = productTransactionHistoryDAL.findByTransactionIdAndProductId(returnOrder.getReturnOrderId()
                            , product.getProductId()).orElse(null);
            if ( productTransactionHistory == null  ){
                productTransactionHistory = new ProductTransactionHistory();
                productTransactionHistory.setTransactionId(returnOrder.getReturnOrderId());
                productTransactionHistory.setProduct(product);
                productTransactionHistory.setQuantity(returnOrderDetail.getQuantity());
                productTransactionHistory.setTransactionDate(returnOrder.getOrderDate());
                productTransactionHistory.setTransactionPrice(returnOrderDetail.getLineTotal());
                productTransactionHistory.setTransactionType("Trả hàng");
                productTransactionHistory.setCostPrice(product.getPurchasePrice() * returnOrderDetail.getQuantity());
                productTransactionHistory.setFinalStock(batchDAL.getFinalStockByProduct(product.getProductId()));
                productTransactionHistory.setPartner( returnOrder.getOrder().getCustomer() == null ? "Khách vãng lai" :
                        (returnOrder.getOrder().getCustomer().getName()));
                productTransactionHistoryDAL.insert(productTransactionHistory);

            }
            else {
                productTransactionHistory.setFinalStock(batchDAL.getFinalStockByProduct(product.getProductId()));
                productTransactionHistory.setQuantity(productTransactionHistory.getQuantity() + returnOrderDetail.getQuantity());
                productTransactionHistory.setTransactionPrice(productTransactionHistory.getTransactionPrice() + returnOrderDetail.getLineTotal());
                productTransactionHistoryDAL.update(productTransactionHistory);
            }

            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }


    public ReturnOrderDetail findByReturnOrderIdAndUnitDetailIdAndBatchId( String returnOrderId, int unitDetailId, String batchId){
        return  returnOrderDetailDAL.findByReturnOrderIdAndUnitDetailIdAndBatchId(returnOrderId, unitDetailId, batchId);
    }

    public boolean updateReturnOrderDetailToDamage(ReturnOrderDetail returnOrderDetail) {
        try{
            transaction.begin();
            ReturnOrderDetail reOld = returnOrderDetailDAL.findByReturnOrderIdAndUnitDetailIdAndBatchId(returnOrderDetail.getReturnOrder().getReturnOrderId(),
                    returnOrderDetail.getUnitDetail().getUnitDetailId(), returnOrderDetail.getBatch().getBatchId());

            reOld.setReturnOrderDetailStatus(returnOrderDetail.getReturnOrderDetailStatus());
            returnOrderDetailDAL.update(reOld);

            ReturnOrder returnOrder = returnOrderDetail.getReturnOrder();
            List<ReturnOrderDetail> detailList  = returnOrder.getReturnOrderDetails();
            if (detailList.stream()
                    .allMatch( x-> x.getReturnOrderDetailStatus() != ReturnOrderDetailStatus.PENDING)) {

                returnOrder.setStatus(true);
                returnOrderDAL.update(returnOrder);
            }

            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }

    }
}
