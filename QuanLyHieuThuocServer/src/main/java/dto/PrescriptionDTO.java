/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author daoducdanh
 */
public class PrescriptionDTO implements Serializable {
    private String productId;
    private String unitName;
    private int quantity;
    private String description;

    public PrescriptionDTO(){
        
    }

    public PrescriptionDTO(String productId, String unitName, int quantity, String description) {
        this.productId = productId;
        this.unitName = unitName;
        this.quantity = quantity;
        this.description = description;
    }
    
    

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
