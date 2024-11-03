/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.DamageItemDetailDAL;
import entity.Batch;
import entity.DamageItem;
import entity.DamageItemDetail;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class DamageItemDetailBUS {
    
    private DamageItemDetailDAL damageItemDetailDAL;
    
    public DamageItemDetailBUS(EntityManager entityManager) {
        this.damageItemDetailDAL = new DamageItemDetailDAL(ConnectDB.getEntityManager());
    }
    
    public List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem){
        return damageItemDetailDAL.getListDamageItemDetailByDamageItem(damageItem);
    }

}
