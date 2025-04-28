package bus.impl;

import bus.DamageItemBUS;
import dal.BatchDAL;
import dal.DamageItemDAL;
import dal.impl.BatchDALImpl;
import dal.impl.DamageItemDALImpl;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.List;

public class DamageItemBUSImpl extends UnicastRemoteObject implements DamageItemBUS {

    private final DamageItemDAL damageItemDAL;
    private final BatchDAL batchDAL;
    private final EntityTransaction transaction;

    public DamageItemBUSImpl(EntityManager entityManager) throws RemoteException {
        this.damageItemDAL = new DamageItemDALImpl(entityManager);
        this.batchDAL = new BatchDALImpl(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createDamageItem(Employee employee, List<DamageItemDetail> damageItemDetails) throws RemoteException{
        try {
            transaction.begin();

            DamageItem damageItem = new DamageItem(null, LocalDateTime.now(), employee, damageItemDetails);
            damageItemDAL.insert(damageItem);

            for (DamageItemDetail damageItemDetail : damageItemDetails) {
                Batch batch = damageItemDetail.getBatch();
                batch.setStatus(false);
                batchDAL.update(batch);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<DamageItem> getAllDamageItems() throws RemoteException{
        return damageItemDAL.findAll();
    }

    @Override
    public List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee) throws RemoteException{
        return damageItemDAL.search(start, end, txtEmployee);
    }

    @Override
    public List<DamageItem> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) throws RemoteException {
        return damageItemDAL.findByDateAndEmp(start, end, empID);
    }

    @Override
    public DamageItem getByID(String id) throws RemoteException{
        return damageItemDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tồn tại phiếu xuất hủy này"));
    }
}
