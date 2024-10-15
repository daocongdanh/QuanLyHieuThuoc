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
import enums.PaymentMethod;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @Column(name = "order_id")
    private String orderId;
    
    @Column(name = "order_date")
    private LocalDate orderDate;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
       
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails;
    
    public Order(){
        
    }

    public Order(String orderId, LocalDate orderDate, PaymentMethod paymentMethod, 
            Employee employee, Customer customer, Promotion promotion, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.employee = employee;
        this.customer = customer;
        this.promotion = promotion;
        this.orderDetails = orderDetails;
        for(OrderDetail orderDetail: orderDetails ){
            orderDetail.setOrder(this);
        }
        setTotalPrice();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        double totalLinePrice = this.orderDetails.stream()
                .mapToDouble(OrderDetail::getLineTotal)
                .sum();
        this.totalPrice = totalLinePrice - 
                ((this.promotion == null) ? 0 : totalLinePrice * this.promotion.getDiscount());
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", paymentMethod=" + paymentMethod + ", employee=" + employee + ", customer=" + customer + ", promotion=" + promotion + ", orderDetails=" + orderDetails + '}';
    }

    
    
     
}
