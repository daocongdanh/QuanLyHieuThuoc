package bus;

import entity.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountBUS extends Remote {

    boolean createAccount(Account account) throws RemoteException;

    boolean updateAccount(Account account) throws RemoteException;

    Account login(String username, String password) throws RemoteException;

    Account getByEmployeeID(String employeeId) throws RemoteException;

    void sendMail(String employeeID) throws RemoteException;
}
