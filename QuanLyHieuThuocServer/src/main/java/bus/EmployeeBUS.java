package bus;

import entity.Employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeBUS extends Remote {
    boolean createEmployee(Employee employee) throws RemoteException;
    boolean updateEmployee(Employee employee) throws RemoteException;
    Employee getEmployeeId(String id) throws RemoteException;
    List<Employee> getAllEmployee() throws RemoteException;
    List<Employee> getEmployeeByKeyword(String keyword) throws RemoteException;
}
