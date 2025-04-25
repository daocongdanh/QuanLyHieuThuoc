package bus;

import entity.Unit;

import java.rmi.Remote;
import java.util.List;
import java.rmi.RemoteException;

public interface UnitBUS extends Remote {
    boolean createUnit(Unit unit) throws RemoteException;
    boolean updateUnit(Unit unit) throws RemoteException;
    Unit getUnitById(String id) throws RemoteException;
    List<Unit> getAllUnits() throws RemoteException;
    Unit getUnitByName(String name) throws RemoteException;
    List<Unit> getUnitByNameSearch(String name) throws RemoteException;
    boolean checkUnitForEdit(String unitId) throws RemoteException;
}
