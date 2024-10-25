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

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "damage_item_details")
public class DamageItemDetail {
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "line_total")
    private double lineTotal;
    
    @ManyToOne
    @JoinColumn(name = "batch_id")
    @Id
    private Batch batch;
    
    @ManyToOne
    @JoinColumn(name = "unit_detail_id")
    @Id
    private UnitDetail unitDetail;
    
    @ManyToOne
    @JoinColumn(name = "damage_item_id")
    @Id
    private DamageItem damageItem;

    public DamageItemDetail() {
    }

    public DamageItemDetail(int quantity, double price, Batch batch, UnitDetail unitDetail) {
        setQuantity(quantity);
        setPrice(price);
        setBatch(batch);
        setUnitDetail(unitDetail);
        setLineTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("Số lượng sản phẩm phải > 0!");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new RuntimeException("Giá sản phẩm phải > 0!");
        }
        this.price = price;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal() {
        this.lineTotal = (this.batch.getProduct().getVAT() + 1) * (this.quantity * this.price);
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        if (batch == null) {
            throw new RuntimeException("Lô hàng không được rỗng!");
        }
        this.batch = batch;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        if (unitDetail == null) {
            throw new RuntimeException("Chi tiết đơn vị tính không được rỗng!");
        }
        this.unitDetail = unitDetail;
    }

    public DamageItem getDamageItem() {
        return damageItem;
    }

    public void setDamageItem(DamageItem damageItem) {
        if (damageItem == null) {
            throw new RuntimeException("Đơn xuất hủy không được rỗng!");
        }
        this.damageItem = damageItem;
    }

    @Override
    public String toString() {
        return "DamageItemDetail{" + "quantity=" + quantity + ", price=" + price + ", lineTotal=" + lineTotal + ", batch=" + batch + ", unitDetail=" + unitDetail + ", damageItem=" + damageItem + '}';
    }
    
    
}
