package bus;

import entity.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerBUS extends Remote {

    boolean createCustomer(Customer customer) throws RemoteException;

    boolean updateCustomer(Customer customer) throws RemoteException;

    Customer getCustomerByPhone(String phone) throws RemoteException;

    Customer getCustomerById(String id) throws RemoteException;

    List<Customer> getAllCustomers() throws RemoteException;

    List<Customer> getCustomerByKeyword(String keyword) throws RemoteException;
}
