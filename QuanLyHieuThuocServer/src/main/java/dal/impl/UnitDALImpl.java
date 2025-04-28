package dal.impl;

import dal.UnitDAL;
import entity.Unit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.GenerateId;
import java.util.List;
import java.util.Optional;

public class UnitDALImpl extends BaseDALImpl<Unit,String> implements UnitDAL {

    private EntityManager entityManager;

    public UnitDALImpl(EntityManager entityManager) {
        super(entityManager, Unit.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Unit unit) {
        GenerateId generateId = new GenerateId(entityManager);
        unit.setUnitId(generateId.generateOrtherId(Unit.class, "DVT"));
        entityManager.persist(unit);
        return true;
    }

    @Override
    public Unit findByName(String name) {
        TypedQuery<Unit> query = entityManager.createQuery("select u from Unit u where u.name = ?1", Unit.class);
        query.setParameter(1, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Unit> findByNameSearch(String name) {
        TypedQuery<Unit> query = entityManager.createQuery("select u from Unit u where u.name like ?1", Unit.class);
        query.setParameter(1, "%" + name + "%");
        return query.getResultList();
    }
}
