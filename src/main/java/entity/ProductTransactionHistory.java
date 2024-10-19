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
import java.time.LocalDate;
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
    private LocalDate transactionDate;
    
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

    public ProductTransactionHistory(String transactionId, LocalDate transactionDate, String transactionType, String partner, double transactionPrice, double costPrice, int quantity, int finalStock, Product product) {
        setTransactionId(transactionId);
        setTransactionDate(transactionDate);
        setTransactionType(transactionType);
        this.partner = partner;
        setTransactionPrice(transactionPrice);
        setCostPrice(costPrice);
        setQuantity(quantity);
        setFinalStock(finalStock);
        setProduct(product);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        if(transactionId == null){
            throw new RuntimeException("Mã giao dịch không hợp lệ");
        }
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        LocalDate curentDate = LocalDate.now();
        if(transactionDate == null || !transactionDate.isEqual(curentDate) ){
            throw  new RuntimeException("NGày giao dịch không hợp lệ");
        }
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        if(transactionType == null){
            throw new RuntimeException("Loaị giao dịch không được rỗng");
        }
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
        if(transactionPrice <0){
            throw new RuntimeException("Giá giao dịch không hợp lệ");
        }
        this.transactionPrice = transactionPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        if(costPrice <0){
            throw new RuntimeException("Giá gốc không hợp lệ");
        }
        this.costPrice = costPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity <0){
            throw new RuntimeException("Số lượng sản phẩm không hợp lệ");
        }
        this.quantity = quantity;
    }

    public int getFinalStock() {
        return finalStock;
    }

    public void setFinalStock(int finalStock) {
        if(finalStock <0){
            throw new RuntimeException("Số lượng tồn cuối không hợp lệ");
        }
        this.finalStock = finalStock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if(product == null){
            throw new RuntimeException("Sản phẩm không được rỗng");
        }
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductTransactionHistory{" + "transactionId=" + transactionId + ", transactionDate=" + transactionDate + ", transactionType=" + transactionType + ", partner=" + partner + ", transactionPrice=" + transactionPrice + ", costPrice=" + costPrice + ", quantity=" + quantity + ", finalStock=" + finalStock + ", product=" + product + '}';
    }
    
    
}
