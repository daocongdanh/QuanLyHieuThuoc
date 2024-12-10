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
    
    @Column(name = "password")
    private String password;
    
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Account(){
        
    }
    
    public Account(String accountId, String password, Employee employee) {
        this.accountId = accountId;
        setPassword(password);
        setEmployee(employee);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
        return "Account{" + "accountId=" + accountId + ", password=" + password + ", employee=" + employee + '}';
    }

    
    
    
}
