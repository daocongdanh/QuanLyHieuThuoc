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
import java.util.List;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */
    
@Entity
@Table(name = "return_orders")
public class ReturnOrder {
    
    @Id
    @Column(name = "return_order_id")
    private String returnOrderId;
    
    @Column(name = "order_date")
    private LocalDate orderDate;
    
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
    private String reason;
    
    public ReturnOrder(){
        
    }

    public ReturnOrder(String returnOrderId, LocalDate orderDate, Employee employee, Order order
    , List<ReturnOrderDetail> returnOrderDetails, boolean status) {
        this.returnOrderId = returnOrderId;
        setOrderDate(orderDate);
        setReason(reason);
        setEmployee(employee);
        setOrder(order);
        setReturnOrderDetails(returnOrderDetails);

        this.orderDate = orderDate;
        this.employee = employee;
        this.order = order;
        this.returnOrderDetails = returnOrderDetails;

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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        LocalDate currentDate = LocalDate.now();
        if(!orderDate.isEqual(currentDate)){
            throw new RuntimeException("Ngày lập đơn nhập hàng không hợp lệ");
        }
        this.orderDate = orderDate;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        if(reason == null)
            throw new RuntimeException("Lí do trả hàng không được rỗng");
        this.reason = reason;
    }

//=======
//>>>>>>> dd4613967900d6b96bee59bfa7cb6b3502322f56
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

    @Override
    public String toString() {
        return "ReturnOrder{" + "returnOrderId=" + returnOrderId + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice + ", status=" + status + ", employee=" + employee + ", order=" + order + ", returnOrderDetails=" + returnOrderDetails + '}';
    }

    
    
}
