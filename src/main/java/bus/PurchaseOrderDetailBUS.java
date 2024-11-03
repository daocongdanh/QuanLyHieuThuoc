/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.PurchaseOrderDetailDAL;
import entity.Batch;
import entity.PurchaseOrder;
import entity.PurchaseOrderDetail;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class PurchaseOrderDetailBUS {
    
    private PurchaseOrderDetailDAL purchaseOrderDetailDAL;
    
    public PurchaseOrderDetailBUS(EntityManager entityManager) {
        this.purchaseOrderDetailDAL = new PurchaseOrderDetailDAL(ConnectDB.getEntityManager());
    }
    
    public List<PurchaseOrderDetail> getListPurchaseOrderDetailsByPurchaseOrder(PurchaseOrder purchaseOrder){
        return purchaseOrderDetailDAL.getListPurchaseOrderDetailByPurchaseOrder(purchaseOrder);
    }

}
