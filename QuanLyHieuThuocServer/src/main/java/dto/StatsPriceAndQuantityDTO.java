/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Hoang
 */
public class StatsPriceAndQuantityDTO implements Serializable {
    private int quantity;
    private double sumPrice;

    public StatsPriceAndQuantityDTO(int quantity, double sumPrice) {
        this.quantity = quantity;
        this.sumPrice = sumPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
