package dal;

import entity.ReturnOrderDetail;
import entity.ReturnOrder;
import enums.ReturnOrderDetailStatus;
import java.util.List;

public interface ReturnOrderDetailDAL {
    boolean insert(ReturnOrderDetail returnOrderDetail);
    boolean update(ReturnOrderDetail returnOrderDetail);
    ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId);
    List<ReturnOrderDetail> getListReturnOrderDetailsByReturnOrder(ReturnOrder returnOrder);
    List<ReturnOrderDetail> getALLByStatus(ReturnOrderDetailStatus returnOrderDetailStatus);
}
