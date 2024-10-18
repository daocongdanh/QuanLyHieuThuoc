/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enums.PromotionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "promotions")
public class Promotion {
    
    @Id
    @Column(name = "promotion_id")
    private String promotionId;
    
    @Column(name = "started_date")
    private LocalDateTime startedDate;
    
    @Column(name = "ended_date")
    private LocalDateTime endedDate;
    
    @Column(name = "discount")
    private double discount;
    
    @Column(name = "promotion_type")
    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;
    
    public Promotion(){
        
    }

    public Promotion(String promotionId, LocalDateTime startedDate, LocalDateTime endedDate, double discount, PromotionType promotionType) {
        this.promotionId = promotionId;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
        this.discount = discount;
        this.promotionType = promotionType;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public LocalDateTime getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(LocalDateTime startedDate) {
        this.startedDate = startedDate;
    }

    public LocalDateTime getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(LocalDateTime endedDate) {
        this.endedDate = endedDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    @Override
    public String toString() {
        return "Promotion{" + "promotionId=" + promotionId + ", startedDate=" + startedDate + ", endedDate=" + endedDate + ", discount=" + discount + ", promotionType=" + promotionType + '}';
    }
    
    
}
