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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
    
@Entity
@Table(name = "return_orders")
public class ReturnOrder implements Serializable {
    
    @Id
    @Column(name = "return_order_id")
    private String returnOrderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Column(name = "total_price")
    private double totalPrice;
    
    @Column(name = "status")
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    
    @OneToMany(mappedBy = "returnOrder", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ReturnOrderDetail> returnOrderDetails;

    
    public ReturnOrder(){
        
    }

    public ReturnOrder(String returnOrderId, LocalDateTime orderDate, Employee employee, Order order
    , List<ReturnOrderDetail> returnOrderDetails, boolean status) {
        this.returnOrderId = returnOrderId;
        setOrderDate(orderDate);
        setEmployee(employee);
        setOrder(order);
        setReturnOrderDetails(returnOrderDetails);
        this.status = status;
        for(ReturnOrderDetail returnOrderDetail : returnOrderDetails){
            returnOrderDetail.setReturnOrder(this);
        }
        setTotalPrice();
    }

    public String getReturnOrderId() {
        return returnOrderId;
    }

    public void setReturnOrderId(String returnOrderId) {
        this.returnOrderId = returnOrderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
//        LocalDateTime currentDate = LocalDateTime.now();
//        if(!orderDate.isEqual(currentDate)){
//            throw new RuntimeException("Ngày lập đơn nhập hàng không hợp lệ");
//        }
        this.orderDate = orderDate;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = returnOrderDetails.stream()
                .mapToDouble(ReturnOrderDetail::getLineTotal)
                .sum();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(employee == null)
            throw  new RuntimeException("Nhân viên không được rỗng");
        this.employee = employee;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        if(order == null){
            throw new RuntimeException("Hóa đơn không được rỗng");
        }
        this.order = order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ReturnOrderDetail> getReturnOrderDetails() {
        return returnOrderDetails;
    }

    public void setReturnOrderDetails(List<ReturnOrderDetail> returnOrderDetails) {
        if(returnOrderDetails == null)
            throw new RuntimeException("Danh sách chi tiết hóa đơn trả khách hàng không được rỗng");
        this.returnOrderDetails = returnOrderDetails;
    }

    
}
