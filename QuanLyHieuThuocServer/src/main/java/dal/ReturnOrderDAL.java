package dal;

import entity.ReturnOrder;
import entity.ReturnOrderDetail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReturnOrderDAL extends BaseDAL<ReturnOrder, String> {
    List<ReturnOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee, String orderId, Boolean status);
    boolean checkOrderIsReturned(String orderId);
    List<ReturnOrder> getListReturnOrdersByStatus(boolean status);
    List<ReturnOrder> searchByDate(LocalDateTime start, LocalDateTime end);
    List<ReturnOrder> searchByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID);
    ReturnOrderDetail findByReturnOrderIdAndProductId(String returnOrderId, String productId);
    ReturnOrder checkExistByOrderId(String orderId);
}
