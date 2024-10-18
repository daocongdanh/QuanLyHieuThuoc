/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

/**
 *
 * @author daoducdanh
 */
public class PurchaseOrderDTO {
    private String productId;
    private String unitName;
    private int quantity;
    private String batchName;
    private LocalDateTime expirationDate;
    
    public PurchaseOrderDTO(){
        
    }

    public PurchaseOrderDTO(String productId, String unitName, int quantity, String batchName, LocalDateTime expirationDate) {
        this.productId = productId;
        this.unitName = unitName;
        this.quantity = quantity;
        this.batchName = batchName;
        this.expirationDate = expirationDate;
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

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    
    
}
