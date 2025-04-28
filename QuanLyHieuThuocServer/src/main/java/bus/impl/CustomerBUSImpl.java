package bus.impl;

import bus.CustomerBUS;
import dal.CustomerDAL;
import dal.impl.CustomerDALImpl;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CustomerBUSImpl extends UnicastRemoteObject implements CustomerBUS {

    private final CustomerDAL customerDAL;
    private final EntityTransaction transaction;

    public CustomerBUSImpl(EntityManager entityManager) throws RemoteException {
        this.customerDAL = new CustomerDALImpl(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createCustomer(Customer customer) throws RemoteException {
        try {
            transaction.begin();
            customerDAL.insert(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException {
        try {
            transaction.begin();
            customerDAL.update(customer);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Customer getCustomerByPhone(String phone) throws RemoteException {
        return customerDAL.findByPhone(phone);
    }

    @Override
    public Customer getCustomerById(String id) throws RemoteException {
        return customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng với id = " + id));
    }

    @Override
    public List<Customer> getAllCustomers() throws RemoteException {
        return customerDAL.findAll();
    }

    @Override
    public List<Customer> getCustomerByKeyword(String keyword) throws RemoteException {
        return customerDAL.findByKeyword(keyword);
    }
}
