/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.CustomerDAL;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class CustomerBUS {

    private CustomerDAL customerDAL;
    private EntityTransaction transaction;

    public CustomerBUS(EntityManager entityManager) {
        this.customerDAL = new CustomerDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    public boolean createCustomer(Customer customer) {
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

    public boolean updateCustomer(Customer customer) {
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

    public Customer getCustomerByPhone(String phone) {
        return customerDAL.findByPhone(phone);
    }

    public Customer getCustomerById(String id) {
        return customerDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tim thấy khách hàng với id = " + id));
    }

    public List<Customer> getAllCustomers() {
        return customerDAL.findAll();
    }

    public List<Customer> getCustomerByKeyword(String keyword) {
        return customerDAL.findByKeyword(keyword);
    }
}
