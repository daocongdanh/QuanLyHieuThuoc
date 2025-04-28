package bus.impl;

import bus.UnitBUS;
import dal.ProductDAL;
import dal.UnitDAL;
import dal.impl.ProductDALImpl;
import dal.impl.UnitDALImpl;
import entity.Unit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UnitBUSImpl extends UnicastRemoteObject implements UnitBUS {
    private final UnitDAL unitDAL;
    private final ProductDAL productDAL;
    private EntityTransaction transaction;

    public UnitBUSImpl(EntityManager entityManager)  throws RemoteException {
        this.unitDAL = new UnitDALImpl(entityManager);
        this.productDAL = new ProductDALImpl(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createUnit(Unit unit) throws RemoteException{
        try {
            transaction.begin();
            unitDAL.insert(unit);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateUnit(Unit unit) throws RemoteException{
        try {
            transaction.begin();
            unitDAL.update(unit);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Unit getUnitById(String id) throws RemoteException{
        return unitDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn vị tính với id = " + id));
    }

    @Override
    public List<Unit> getAllUnits() throws RemoteException{
        return unitDAL.findAll();
    }

    @Override
    public Unit getUnitByName(String name) throws RemoteException{
        return unitDAL.findByName(name);
    }

    @Override
    public List<Unit> getUnitByNameSearch(String name) throws RemoteException{
        return unitDAL.findByNameSearch(name);
    }

    @Override
    public boolean checkUnitForEdit(String unitId) throws RemoteException {
        // Check if the unit is not used by any product
        return productDAL.findByUnitId(unitId).isEmpty();
    }

}
