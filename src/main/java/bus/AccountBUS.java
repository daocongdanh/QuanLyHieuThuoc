/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.AccountDAL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.Account;
/**
 *
 * @author daoducdanh
 */
public class AccountBUS {
    
    private AccountDAL accountDAL;
    private EntityTransaction transaction;
    
    public AccountBUS(EntityManager entityManager) {
        this.accountDAL = new AccountDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public Account login(String username, String password){
        return accountDAL.login(username, password);
    }
}
