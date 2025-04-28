package dal;

import entity.PurchaseOrder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Giao diện cho lớp PurchaseOrderDAL.
 */
public interface PurchaseOrderDAL extends BaseDAL<PurchaseOrder, String> {
    List<PurchaseOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee);
    List<PurchaseOrder> searchByDate(LocalDateTime start, LocalDateTime end);
    List<PurchaseOrder> searchByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID);
}
