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
@Table(name = "purchase_order_details")
public class PurchaseOrderDetail {
    
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
    @JoinColumn(name = "purchase_order_id")
    @Id
    private PurchaseOrder purchaseOrder;
    
    public PurchaseOrderDetail(){
        
    }

    public PurchaseOrderDetail(int quantity, double price, Batch batch, UnitDetail unitDetail) {
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
        if(quantity < 0)
            throw new RuntimeException("Số lượng sản phẩm không hợp lệ");
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0)
            throw new RuntimeException("Giá sản phẩm không hợp lệ");
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
        if(batch == null )
            throw new RuntimeException("Lô hàng không được rỗng");
        this.batch = batch;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        if(unitDetail == null)
            throw new RuntimeException("Chi tiết đơn vị tính không được rỗng");
        this.unitDetail = unitDetail;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        if(purchaseOrder == null){
            throw new RuntimeException("Đơn hàng nhập khôgn được rỗng");
        }
        this.purchaseOrder = purchaseOrder;
    }

    @Override
    public String toString() {
        return "PurchaseOrderDetail{" + "quantity=" + quantity + ", price=" + price + ", lineTotal=" + lineTotal + ", batch=" + batch + ", unitDetail=" + unitDetail + ", purchaseOrder=" + purchaseOrder + '}';
    }
    
}
