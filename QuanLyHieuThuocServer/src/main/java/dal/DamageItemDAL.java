package dal;

import entity.DamageItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DamageItemDAL extends BaseDAL<DamageItem, String> {
    List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee);
    List<DamageItem> searchByDate(LocalDateTime start, LocalDateTime end);
    List<DamageItem> findByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID);
}
