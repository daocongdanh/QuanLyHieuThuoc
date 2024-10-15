/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "accounts")
public class Account {
    
    @Id
    @Column(name = "account_id")
    private String accountId;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Account(){
        
    }
    
    public Account(String accountId, String username, String password, Employee employee) {
        this.accountId = accountId;
        setUsername(username);
        setPassword(password);
        setEmployee(employee);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username.length() < 8)
            throw new RuntimeException("Tên đăng nhập không hợp lệ");
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String regex = "^[A-Z][A-Za-z0-9.,@&*^]{7,}$";
        if(!password.matches(regex))
            throw new RuntimeException("Mật khẩu không hợp lệ");
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(employee == null)
            throw new RuntimeException("Nhân viên không được rỗng");
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", username=" + username + ", password=" + password + ", employee=" + employee + '}';
    }
    
    
    
}
