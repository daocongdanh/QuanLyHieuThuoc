package bus;

import entity.Supplier;

import java.rmi.Remote;
import java.util.List;
import java.rmi.RemoteException;

public interface SupplierBUS extends Remote {
    boolean createSupplier(Supplier supplier) throws RemoteException;
    boolean updateSupplier(Supplier supplier) throws RemoteException;
    Supplier getSupplierById(String id) throws RemoteException;
    List<Supplier> getAllSuppliers() throws RemoteException;
    Supplier getSupByName(String name) throws RemoteException;
    List<Supplier> searchSuppliersByText(String text) throws RemoteException;
    Supplier getSupplierByPhone(String phone) throws RemoteException;
}
