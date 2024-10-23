/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Hoang
 */
public class BatchDTO {
    
    private String name;
    private int stock;
    private LocalDate expirationDate;
    private int quantity;

    public BatchDTO() {
    }

    public BatchDTO(String name, int stock, LocalDate expirationDate, int quantity) {
        this.name = name;
        this.stock = stock;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }  

    @Override
    public String toString() {
        return "BatchDTO{" + "name=" + name + ", stock=" + stock + ", expirationDate=" + expirationDate + ", quantity=" + quantity + '}';
    }
    
}
