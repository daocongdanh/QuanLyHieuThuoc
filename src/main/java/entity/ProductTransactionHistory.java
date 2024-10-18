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
import java.time.LocalDateTime;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */
@Entity
@Table(name = "product_transaction_histories")
public class ProductTransactionHistory {
    @Id
    @Column(name = "transaction_id")
    private String transactionId;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @Id
    private Product product;
    
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    
    @Column(name = "transaction_type")
    @Nationalized
    private String transactionType;
    
    @Column(name = "partner")
    @Nationalized
    private String partner;
    
    @Column(name = "transaction_price")
    private double transactionPrice;
    
    @Column(name = "cost_price")
    private double costPrice;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "final_stock")
    private int finalStock;

    
    
    public ProductTransactionHistory(){
        
    }

    public ProductTransactionHistory(String transactionId, LocalDateTime transactionDate, String transactionType, 
            String partner, double transactionPrice, double costPrice, int quantity, int finalStock, Product product) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.partner = partner;
        this.transactionPrice = transactionPrice;
        this.costPrice = costPrice;
        this.quantity = quantity;
        this.finalStock = finalStock;
        this.product = product;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFinalStock() {
        return finalStock;
    }

    public void setFinalStock(int finalStock) {
        this.finalStock = finalStock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductTransactionHistory{" + "transactionId=" + transactionId + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType + ", partner=" + partner + ", transactionPrice=" + transactionPrice + ", costPrice=" + costPrice + ", quantity=" + quantity + ", finalStock=" + finalStock + ", product=" + product + '}';
    }
    
    
}
