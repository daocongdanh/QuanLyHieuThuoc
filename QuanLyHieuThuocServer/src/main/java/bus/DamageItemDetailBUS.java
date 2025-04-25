package bus;

import entity.DamageItem;
import entity.DamageItemDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DamageItemDetailBUS extends Remote {
    List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem) throws RemoteException;
}
