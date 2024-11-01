/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import enums.ReturnOrderDetailStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */
@Entity
@Table(name = "return_order_details")
public class ReturnOrderDetail {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "reason")
    @Nationalized
    private String reason;

    @Column(name = "line_total")
    private double lineTotal;

    @Column(name = "return_order_detail_status")
    @Enumerated(EnumType.STRING)
    private ReturnOrderDetailStatus returnOrderDetailStatus;

    @Column(name = "final_reason")
    private String finalReason;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Id
    private Product product;

    @ManyToOne
    @JoinColumn(name = "return_order_id")
    @Id
    private ReturnOrder returnOrder;

    public ReturnOrderDetail() {

    }

    public ReturnOrderDetail(int quantity, double price, Batch batch, ReturnOrderDetailStatus returnOrderDetailStatus, String reason, String finalReason) {
        setQuantity(quantity);
        setPrice(price);
        this.product = product;
        setReason(reason);
        this.returnOrderDetailStatus = returnOrderDetailStatus;
        setLineTotal();
        this.finalReason = finalReason;

    }

    public int getQuantity() {
        return quantity;
    }

    public String getFinalReason() {
        return finalReason;
    }

    public void setFinalReason(String finalReason) {
        this.finalReason = finalReason;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new RuntimeException("Số lượng sản phẩm không hợp lệ");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new RuntimeException("Giá sản phẩm không hợp lệ");
        }
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        if (reason.equals("")) {
            throw new RuntimeException("Lý do khách trả hàng không được rỗng");
        }
        this.reason = reason;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal() {
        this.lineTotal = (this.product.getVAT() + 1) * (this.quantity * this.price);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ReturnOrder getReturnOrder() {
        return returnOrder;
    }

    public void setReturnOrder(ReturnOrder returnOrder) {
        if (returnOrder == null) {
            throw new RuntimeException("Hóa đơn trả khách hàng không được rỗng");
        }
        this.returnOrder = returnOrder;
    }

    public ReturnOrderDetailStatus getReturnOrderDetailStatus() {
        return returnOrderDetailStatus;
    }

    public void setReturnOrderDetailStatus(ReturnOrderDetailStatus returnOrderDetailStatus) {
        this.returnOrderDetailStatus = returnOrderDetailStatus;
    }

}
