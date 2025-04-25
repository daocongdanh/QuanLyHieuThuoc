package bus;

import dto.ReturnOrderDetailDTO;
import dto.StatsPriceAndQuantityDTO;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.ReturnOrder;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface ReturnOrderBUS extends Remote {
    boolean createReturnOrder(Employee employee, Customer customer, Order order, List<ReturnOrderDetailDTO> returnOrderDetailDTOs) throws RemoteException;
    List<ReturnOrder> getAllReturnOrders() throws RemoteException;
    List<ReturnOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee, String orderId, Boolean status) throws RemoteException;
    ReturnOrder findById(String id) throws RemoteException;
    boolean checkOrderIsReturned(String orderId) throws RemoteException;
    List<ReturnOrder> getListReturnOrdersByStatus(boolean status) throws RemoteException;
    StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;
    List<ReturnOrder> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) throws RemoteException;
}
