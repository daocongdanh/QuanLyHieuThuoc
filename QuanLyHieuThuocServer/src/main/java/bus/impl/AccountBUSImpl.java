package bus.impl;

import bus.AccountBUS;
import dal.AccountDAL;
import entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.MailTemplate;
import util.PasswordUtil;
import util.SendMail;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccountBUSImpl extends UnicastRemoteObject implements AccountBUS {

    private final AccountDAL accountDAL;
    private final EntityTransaction transaction;
    private final SendMail sendMail;
    private final MailTemplate mailTemplate;

    public AccountBUSImpl(EntityManager entityManager) throws RemoteException {
        this.accountDAL = new AccountDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.sendMail = new SendMail();
        this.mailTemplate = new MailTemplate(entityManager);
    }

    @Override
    public boolean createAccount(Account account) throws RemoteException {
        try {
            transaction.begin();
            accountDAL.insert(account);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    @Override
    public boolean updateAccount(Account account) throws RemoteException {
        try {
            transaction.begin();
            accountDAL.update(account);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    @Override
    public Account login(String username, String password) throws RemoteException {
        Account account = accountDAL.login(username);
        if (account == null) {
            return null;
        }

        if (PasswordUtil.verify(password, account.getPassword())) {
            return account;
        }
        return null;
    }
    @Override
    public Account getByEmployeeID(String employeeId)  throws RemoteException{
        return accountDAL.findByEmployeeId(employeeId)
                .orElse(null);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 9000) + 1000;
    }
    @Override
    public void sendMail(String employeeID) throws RemoteException {
        Account acc = getByEmployeeID(employeeID);
        String newPass = acc.getAccountId() + generateRandomNumber();
        acc.setPassword(PasswordUtil.encode(newPass));
        updateAccount(acc);
        String body = mailTemplate.mailPassword(newPass);
        String subject = "Thư lấy lại mật khẩu";
        sendMail.sendMail(acc.getEmployee().getEmail(), subject, body);
    }
}
