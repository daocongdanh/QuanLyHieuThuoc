/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import entity.UnitDetail;

/**
 *
 * @author daoducdanh
 */
public class OrderDTO {
    private String productId;
    private UnitDetail unitDetail;
    private int quantity;
    private String batchName;
    
    public OrderDTO(){
        
    }

    public OrderDTO(String productId, UnitDetail unitDetail, int quantity, String batchName) {
        this.productId = productId;
        this.unitDetail = unitDetail;
        this.quantity = quantity;
        this.batchName = batchName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
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

    @Override
    public String toString() {
        return "OrderDTO{" + "productId=" + productId + ", unitDetail=" + unitDetail + ", quantity=" + quantity + ", batchName=" + batchName + '}';
    }


    
    
}
