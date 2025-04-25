package bus.impl;

import bus.SupplierBUS;
import dal.SupplierDAL;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class SupplierBUSImpl extends UnicastRemoteObject implements SupplierBUS {
    private final SupplierDAL supplierDAL;
    private final EntityTransaction transaction;

    // Constructor
    public SupplierBUSImpl(EntityManager entityManager) throws RemoteException {
        this.supplierDAL = new SupplierDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createSupplier(Supplier supplier) throws RemoteException {
        try {
            transaction.begin();
            supplierDAL.insert(supplier);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier)  throws RemoteException{
        try {
            transaction.begin();
            supplierDAL.update(supplier);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Supplier getSupplierById(String id) throws RemoteException {
        return supplierDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp với id = " + id));
    }

    @Override
    public List<Supplier> getAllSuppliers() throws RemoteException {
        return supplierDAL.findAll();
    }

    @Override
    public Supplier getSupByName(String name) throws RemoteException {
        return supplierDAL.findByName(name);
    }

    @Override
    public List<Supplier> searchSuppliersByText(String text) throws RemoteException {
        return supplierDAL.searchSuppliersByText(text);
    }

    @Override
    public Supplier getSupplierByPhone(String phone) throws RemoteException {
        return supplierDAL.findByPhone(phone);
    }
}
