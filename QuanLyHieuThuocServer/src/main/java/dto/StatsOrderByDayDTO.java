/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Hoang
 */
public class StatsOrderByDayDTO implements Serializable {
    
    private double sumPrice;
    private LocalDateTime time;
    private Integer quantityOrder;

    public StatsOrderByDayDTO(LocalDateTime time,double sumPrice,Integer quantityOrder) {
        this.sumPrice = sumPrice;
        this.time = time;
        this.quantityOrder = quantityOrder;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
}
