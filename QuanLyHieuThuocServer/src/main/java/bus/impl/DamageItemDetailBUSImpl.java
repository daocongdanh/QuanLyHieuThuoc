package bus.impl;

import bus.DamageItemDetailBUS;
import connectDB.ConnectDB;
import dal.DamageItemDetailDAL;
import entity.DamageItem;
import entity.DamageItemDetail;
import jakarta.persistence.EntityManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DamageItemDetailBUSImpl extends UnicastRemoteObject implements DamageItemDetailBUS {

    private final DamageItemDetailDAL damageItemDetailDAL;

    public DamageItemDetailBUSImpl(EntityManager entityManager) throws RemoteException {
        this.damageItemDetailDAL = new DamageItemDetailDAL(ConnectDB.getEntityManager());
    }

    @Override
    public List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem) throws RemoteException {
        return damageItemDetailDAL.getListDamageItemDetailByDamageItem(damageItem);
    }
}
