/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.AccountDAL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import entity.Account;
import util.PasswordUtil;
import util.SendMail;
import util.MailTemplate;
/**
 *
 * @author daoducdanh
 */
public class AccountBUS {
    
    private AccountDAL accountDAL;
    private EntityTransaction transaction;
    private SendMail sendMail;
    private MailTemplate mailTemplate;
    
    public AccountBUS(EntityManager entityManager) {
        this.accountDAL = new AccountDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.sendMail = new SendMail();
        this.mailTemplate = new MailTemplate(entityManager);
    }
    
    public boolean createAccount(Account account){
        try{
            transaction.begin();

            accountDAL.insert(account);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public boolean updateAccount(Account account) {
        try {
            transaction.begin();
            accountDAL.update(account);
            transaction.commit();
            return true;
        } 
        catch(Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public Account login(String username, String password){
        Account account = accountDAL.login(username);
        if ( PasswordUtil.verify( password, account.getPassword()) ){
            return account;
        }
        return null;
    }
    
    public Account getByEmployeeID(String employeeId) {
        return accountDAL.findByEmployeeId(employeeId)
                .orElse(null);
    }
    
    public void sendMail(String employeeID){
        Account acc = getByEmployeeID(employeeID);
        
        String body = mailTemplate.mailPassword(acc);
        String subject = "Thư lấy lại mật khẩu";
        sendMail.sendMail(acc.getEmployee().getEmail(), subject, body);
    }
}
