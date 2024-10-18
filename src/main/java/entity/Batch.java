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
@Table(name = "batchs")
public class Batch {
    
    @Id
    @Column(name = "batch_id")
    private String batchId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    
    @Column(name = "stock")
    private int stock;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    public Batch(){
        
    }

    public Batch(String batchId, String name, LocalDateTime expirationDate, int stock, Product product) {
        this.batchId = batchId;
        setName(name);
        setExpirationDate(expirationDate);
        setStock(stock);
        setProduct(product);
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
        if (name.equals("")) {
            throw new RuntimeException("Tên lô hàng không được rỗng!");
        }
        this.name = name;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        if (expirationDate.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Ngày hết hạn phải sau ngày hiện tại hoặc là ngày hiện tại!");
        }
        this.expirationDate = expirationDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new RuntimeException("Số lượng tồn phải >= 0!");
        }
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if (product == null) {
            throw new RuntimeException("Sản phẩm không được rỗng!");
        }
        this.product = product;
    }

    @Override
    public String toString() {
        return "Batch{" + "batchId=" + batchId + ", name=" + name + ", expirationDate=" + expirationDate + ", stock=" + stock + ", product=" + product + '}';
    }
    
    
}
