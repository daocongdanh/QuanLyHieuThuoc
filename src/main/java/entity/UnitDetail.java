/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author daoducdanh
 */
@Entity
@Table(name = "unit_details")
public class UnitDetail {
    
    @Id
    @Column(name = "unit_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int unitDetailId;
    
    @Column(name = "conversion_rate")
    private int conversionRate;
    
    @Column(name = "is_default")
    private boolean isDefault;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
    
    public UnitDetail(){
        
    }

    public UnitDetail( int conversionRate, boolean isDefault, Product product, Unit unit) {
        this.conversionRate = conversionRate;
        this.isDefault = isDefault;
        this.product = product;
        this.unit = unit;
    }

    public int getUnitDetailId() {
        return unitDetailId;
    }

    public void setUnitDetailId(int unitDetailId) {
        this.unitDetailId = unitDetailId;
    }

    public int getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(int conversionRate) {
        this.conversionRate = conversionRate;
    }

    public boolean isIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit.getName();
    }
    
    
    
}
