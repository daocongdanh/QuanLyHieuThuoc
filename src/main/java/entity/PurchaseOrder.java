/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    
    @Id
    @Column(name = "purchase_order_id")
    private String purchaseOrderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    
    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<PurchaseOrderDetail> purchaseOrderDetails;
    
    public PurchaseOrder(){
        
    }

    public PurchaseOrder(String purchaseOrderId, LocalDateTime orderDate, Employee employee, Supplier supplier, List<PurchaseOrderDetail> purchaseOrderDetails) {
        this.purchaseOrderId = purchaseOrderId;
        setOrderDate(orderDate);
        setEmployee(employee);
        setSupplier(supplier);
        setPurchaseOrderDetails(purchaseOrderDetails);
        for(PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails){
            purchaseOrderDetail.setPurchaseOrder(this);
        }
        setTotalPrice();
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(List<PurchaseOrderDetail> purchaseOrderDetails) {
        if(purchaseOrderDetails == null)
            throw new RuntimeException("Danh sách chi đơn nhập hàng không được rỗng");
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    

    public String getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(String purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        LocalDateTime currentDate = LocalDateTime.now();
        if(!orderDate.isEqual(currentDate)){
            throw new RuntimeException("Ngày lập đơn nhập hàng không hợp lệ");
        }
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = purchaseOrderDetails.stream()
                .mapToDouble(PurchaseOrderDetail::getLineTotal)
                .sum();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(employee == null ){
            throw new RuntimeException("Nhân viên không được rỗng");
        }
        this.employee = employee;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        if(supplier == null)
            throw new RuntimeException("Nhà cung cấp không được rỗng");
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "purchaseOrderId=" + purchaseOrderId + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", employee=" + employee + ", supplier=" + supplier + '}';
    }
    
    
}
