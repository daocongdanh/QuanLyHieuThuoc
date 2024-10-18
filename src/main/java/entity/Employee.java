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
@Table(name = "employees")
public class Employee {
    
    @Id
    @Column(name = "employee_id")
    private String employeeId;
    
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
    
    @Column(name = "role")
    @Nationalized
    private String role;

    public Employee(){
        
    }

    public Employee(String employeeId, String name, String phone, String address, String email, String role) {
        this.employeeId = employeeId;
        setName(name);
        setPhone(phone);
        setAddress(address);
        setEmail(email);
        setRole(role);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new RuntimeException("Tên nhân viên không được rỗng!");
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
            throw new RuntimeException("Địa chỉ không được rỗng");
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (!(role.equalsIgnoreCase("Nhân viên")) && !(role.equalsIgnoreCase("Quản lý"))) {
            throw new RuntimeException("Chức vụ chỉ bao gồm Nhân viên và Quản Lý!");
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeId=" + employeeId + ", name=" + name + ", phone=" + phone + ", address=" + address + ", email=" + email + ", role=" + role + '}';
    }


}
