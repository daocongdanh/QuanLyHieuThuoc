/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.persistence.EntityManager;
import entity.Account;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
/**
 *
 * @author daoducdanh
 */
public class AccountDAL implements BaseDAL<Account, String>{
    private EntityManager entityManager;
    
    public AccountDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Account account) {
        GenerateId generateId = new GenerateId(entityManager);
        account.setAccountId(generateId.generateOrtherId(Account.class, "TK"));
        entityManager.persist(account);
        return true;
    }

    @Override
    public boolean update(Account account) {
        entityManager.merge(account);
        return true;
    }

    @Override
    public Optional<Account> findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Account> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public Account login(String username, String password){
        String jpql = "select a from Account a where a.username = ?1 and a.password = ?2";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter(1, username);
        query.setParameter(2, password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Optional<Account> findByEmployeeId(String employeeId) {
        try {
            Account account = entityManager.createQuery(
                "select a from Account a where a.employee.employeeId = ?1", Account.class)
                .setParameter(1, employeeId)
                .getSingleResult();
            return Optional.ofNullable(account);
        } catch (Exception e) {
            return Optional.empty();
        }
    } 
        
}
