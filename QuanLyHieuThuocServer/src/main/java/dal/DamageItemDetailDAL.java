package dal;

import entity.DamageItem;
import entity.DamageItemDetail;
import java.util.List;

public interface DamageItemDetailDAL {
    boolean insert(DamageItemDetail damageItemDetail);
    List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem);
}
