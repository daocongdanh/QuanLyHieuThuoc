/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class OrderDetailSelected implements Serializable {
    private List<BatchDTO> batchDTOs;
    
    public OrderDetailSelected(){
        
    }

    public OrderDetailSelected(List<BatchDTO> batchDTOs) {
        this.batchDTOs = batchDTOs;
                
    }

    public List<BatchDTO> getBatchDTOs() {
        return batchDTOs;
    }

    public void setBatchDTOs(List<BatchDTO> batchDTOs) {
        this.batchDTOs = batchDTOs;
    }


}