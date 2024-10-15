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
@Table(name = "batchs")
public class Batch {
    
    @Id
    @Column(name = "batch_id")
    private String batchId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    
    @Column(name = "stock")
    private int stock;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    public Batch(){
        
    }

    public Batch(String batchId, String name, LocalDate expirationDate, int stock, Product product) {
        this.batchId = batchId;
        this.name = name;
        this.expirationDate = expirationDate;
        this.stock = stock;
        this.product = product;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Batch{" + "batchId=" + batchId + ", name=" + name + ", expirationDate=" + expirationDate + ", stock=" + stock + ", product=" + product + '}';
    }
    
    
}
