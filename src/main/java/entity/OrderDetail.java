/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "order_details")
public class OrderDetail {
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "line_total")
    private double lineTotal;
    
    @Column(name = "discount")
    private double discount;
    
    @ManyToOne
    @JoinColumn(name = "batch_id")
    @Id
    private Batch batch;
    
    @ManyToOne
    @JoinColumn(name = "unit_detail_id")
    @Id
    private UnitDetail unitDetail;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    @Id
    private Order order;
    
    public OrderDetail(){
        
    }

    public OrderDetail(int quantity, double price, double discount, Batch batch, UnitDetail unitDetail) {
        this.quantity = quantity;
        this.price = price;
        this.batch = batch;
        this.unitDetail = unitDetail;
        this.discount = discount;
        setLineTotal();
        
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal() {
        double subTotal = this.quantity * this.price * (1 + this.batch.getProduct().getVAT());
        this.lineTotal = subTotal - subTotal * this.discount;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }  
    
}

