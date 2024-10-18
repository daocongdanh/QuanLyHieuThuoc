/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @Column(name = "customer_id")
    private String customerId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    @Nationalized
    private String address;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "gender")
    private boolean gender;
    
    public Customer(){
        
    }

    public Customer(String customerId, String name, String phone, String address, String email, boolean gender) {
        this.customerId = customerId;
        setName(name);
        setPhone(phone);
        setAddress(address);
        setEmail(email);
        this.gender = gender;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new RuntimeException("Tên khách hàng không được rỗng!");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!(phone.matches("^\\d{10}$"))) {
            throw new RuntimeException("Số điện thoại phải đủ 10 số!");
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address.equals("")) {
            throw new RuntimeException("Địa chỉ không được rỗng!");
        }
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!(email.matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.com$"))) {
            throw new RuntimeException("Email không đúng định dạng!");
        }
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", name=" + name + ", phone=" + phone + ", address=" + address + ", email=" + email + ", gender=" + gender + '}';
    }

    
    
    
}
