package bus;

import entity.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface DamageItemBUS extends Remote {

    boolean createDamageItem(Employee employee, List<DamageItemDetail> damageItemDetails) throws RemoteException;

    List<DamageItem> getAllDamageItems() throws RemoteException;

    List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee) throws RemoteException;

    List<DamageItem> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) throws RemoteException;

    DamageItem getByID(String Id) throws RemoteException;
}
