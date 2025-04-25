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
public class StatsOrderByYearDTO {
    
    private double sumPrice;
    private int year;
    private Integer quantityOrder;

    public StatsOrderByYearDTO(int year,double sumPrice,Integer quantityOrder) {
        this.sumPrice = sumPrice;
        this.year = year;
        this.quantityOrder = quantityOrder;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }
}
