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
import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "damage_items")
public class DamageItem {
    
    @Id
    @Column(name = "damage_item_id")
    private String damageItemId;
    
    @Column(name = "order_date")
    private LocalDate orderDate;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @ManyToOne 
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @OneToMany(mappedBy = "damageItem", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<DamageItemDetail> damageItemDetails;
    
    public DamageItem(){
        
    }

    public DamageItem(String damageItemId, LocalDate orderDate, Employee employee, 
            List<DamageItemDetail> damageItemDetails) {
        this.damageItemId = damageItemId;
        setOrderDate(orderDate);
        setEmployee(employee);
        setDamageItemDetails(damageItemDetails);
        for(DamageItemDetail damageItemDetail : damageItemDetails){
            damageItemDetail.setDamageItem(this);
        }
        setTotalPrice();
    }

    public String getDamageItemId() {
        return damageItemId;
    }

    public void setDamageItemId(String damageItemId) {
        this.damageItemId = damageItemId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        if (!(orderDate.isEqual(LocalDate.now()))) {
            throw new RuntimeException("Ngày lập đơn phải là ngày hiện tại!");
        }
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = damageItemDetails.stream()
                .mapToDouble(DamageItemDetail::getLineTotal)
                .sum();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Nhân viên lập đơn không được rỗng!");
        }
        this.employee = employee;
    }

    public List<DamageItemDetail> getDamageItemDetails() {
        return damageItemDetails;
    }

    public void setDamageItemDetails(List<DamageItemDetail> damageItemDetails) {
        if (damageItemDetails.size() < 1) {
            throw new RuntimeException("Các chi tiết đơn xuất hủy không được rỗng!");
        }
        this.damageItemDetails = damageItemDetails;
    }

    @Override
    public String toString() {
        return "DamageItem{" + "damageItemId=" + damageItemId + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", employee=" + employee + ", damageItemDetails=" + damageItemDetails + '}';
    }

    
}
