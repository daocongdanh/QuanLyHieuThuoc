package dal;

import entity.Order;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface OrderDAL extends BaseDAL<Order, String> {
    List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee);
    List<Order> searchByDate(LocalDateTime start, LocalDateTime end);
    List<Order> statisByDate(LocalDateTime start, LocalDateTime end);
    List<Order> findOrderByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID);
}
