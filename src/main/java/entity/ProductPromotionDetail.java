/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
@Table(name = "product_promotion_details")
public class ProductPromotionDetail {
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    @Id
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @Id
    private Promotion promotion;
    
    public ProductPromotionDetail(){
        
    }

    public ProductPromotionDetail(Product product, Promotion promotion) {
        this.product = product;
        this.promotion = promotion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ProductPromotionDetail{" + "product=" + product + ", promotion=" + promotion + '}';
    }
    
    
    
}
