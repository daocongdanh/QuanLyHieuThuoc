package dal.impl;

import dal.DamageItemDetailDAL;
import entity.DamageItem;
import entity.DamageItemDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DamageItemDetailDALImpl implements DamageItemDetailDAL {
    private EntityManager entityManager;

    public DamageItemDetailDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(DamageItemDetail damageItemDetail) {
        entityManager.persist(damageItemDetail);
        return true;
    }

    @Override
    public List<DamageItemDetail> getListDamageItemDetailByDamageItem(DamageItem damageItem) {
        TypedQuery<DamageItemDetail> query = entityManager.createQuery(
                "select did from DamageItemDetail did where did.damageItem = ?1", DamageItemDetail.class);
        query.setParameter(1, damageItem);
        return query.getResultList();
    }
}
