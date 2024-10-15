/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import jakarta.persistence.EntityManager;
import entity.OrderDetail;
/**
 *
 * @author daoducdanh
 */
public class OrderDetailDAL{
    private EntityManager entityManager;
    
    public OrderDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(OrderDetail orderDetail) {
        entityManager.persist(orderDetail);
        return true;
    }
    
}
