/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.persistence.EntityManager;
import entity.ReturnOrderDetail;
/**
 *
 * @author daoducdanh
 */
public class ReturnOrderDetailDAL {
    private EntityManager entityManager;
    
    public ReturnOrderDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(ReturnOrderDetail returnOrderDetail) {
        entityManager.persist(returnOrderDetail);
        return true;
    }
    
}
