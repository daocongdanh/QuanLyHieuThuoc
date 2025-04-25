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

import java.io.Serializable;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {
    
    @Id
    @Column(name = "supplier_id")
    private String supplierId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    @Column(name = "address")
    @Nationalized
    private String address;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "tax_code")
    private String taxCode;
    
    public Supplier(){
        
    }

    public Supplier(String supplierId, String name, String address, String phone, String email, String taxCode) {
        this.supplierId = supplierId;
        setName(name);
        setAddress(address);
        setPhone(phone);
        setEmail(email);
        setTaxCode(taxCode);
    }

 
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null)
            throw new RuntimeException("Tên nhà cung cấp không được rỗng");         
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address == null)
            throw new RuntimeException("Địa chỉ không được rỗng ");
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.matches("\\d{10}"))
            throw new RuntimeException("Số điện thoại không hợp lệ. Phải là dãy số gồm 10 chữ số.");
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[a-zA-Z0-9]+@[a-zA-Z]+\\.com$")) {
            throw new RuntimeException("Email không hợp lệ");
        }
        this.email = email;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        if(taxCode == null)
            throw new RuntimeException("Mã số thuế không được rỗng");
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", taxCode=" + taxCode + '}';
    }
    
    
}
