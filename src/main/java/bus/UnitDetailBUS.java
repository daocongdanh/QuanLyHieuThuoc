/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.UnitDetailDAL;
import entity.UnitDetail;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import entity.Product;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class UnitDetailBUS {

    private UnitDetailDAL unitDetailDAL;

    public UnitDetailBUS(EntityManager entityManager) {
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
    }

    public List<UnitDetail> getListUnitProduct(Product product) {
        List<UnitDetail> listUnitDetail = unitDetailDAL.findByProduct(product);
        List<UnitDetail> unitDetails = new ArrayList<>();
        for(UnitDetail unitDetail : listUnitDetail){
            if(unitDetail.isIsDefault())
                unitDetails.add(0, unitDetail);
            else unitDetails.add(unitDetail);
        }
        return unitDetails;
    }
}
