/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import entity.UnitDetail;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class OrderDetailSelected {
    private UnitDetail unitDetail;
    private List<BatchDTO> batchDTOs;
    
    public OrderDetailSelected(){
        
    }

    public OrderDetailSelected(UnitDetail unitDetail, List<BatchDTO> batchDTOs) {
        this.unitDetail = unitDetail;
        this.batchDTOs = batchDTOs;
                
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    public List<BatchDTO> getBatchDTOs() {
        return batchDTOs;
    }

    public void setBatchDTOs(List<BatchDTO> batchDTOs) {
        this.batchDTOs = batchDTOs;
    }


}
