package dal.impl;

import dal.DamageItemDAL;
import entity.DamageItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class DamageItemDALImpl extends BaseDALImpl<DamageItem, String> implements DamageItemDAL {
    private EntityManager entityManager;

    public DamageItemDALImpl(EntityManager entityManager) {
        super(entityManager, DamageItem.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(DamageItem damageItem) {
        GenerateId generateId = new GenerateId(entityManager);
        String id = generateId.generateOrderId(damageItem.getEmployee().getPhone(), DamageItem.class);
        damageItem.setDamageItemId(id);
        entityManager.persist(damageItem);
        return true;
    }

    @Override
    public boolean update(DamageItem t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Optional<DamageItem> findById(String id) {
        return Optional.ofNullable(entityManager.find(DamageItem.class, id));
    }

    @Override
    public List<DamageItem> findAll() {
        return entityManager.createQuery("select d from DamageItem d", DamageItem.class).getResultList();
    }

    @Override
    public List<DamageItem> search(LocalDateTime start, LocalDateTime end, String txtEmployee) {
        StringBuilder jpql = new StringBuilder("select d from DamageItem d where (d.orderDate between ?1 and ?2) ");
        
        if (!txtEmployee.equals("")) {
            jpql.append(" and d.employee.name like ?3");
        }
        
        TypedQuery<DamageItem> query = entityManager.createQuery(jpql.toString(), DamageItem.class);
        query.setParameter(1, start);
        query.setParameter(2, end);

        if (!txtEmployee.equals("")) {
            query.setParameter(3, '%' + txtEmployee + '%');
        }
        
        return query.getResultList();
    }

    @Override
    public List<DamageItem> searchByDate(LocalDateTime start, LocalDateTime end) {
        String jpql = "select d from DamageItem d where (d.orderDate between ?1 and ?2)";
        TypedQuery<DamageItem> query = entityManager.createQuery(jpql, DamageItem.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        return query.getResultList();
    }

    @Override
    public List<DamageItem> findByDateAndEmp(LocalDateTime start, LocalDateTime end, String employeeID) {
        String jpql = "select d from DamageItem d where (d.orderDate between ?1 and ?2) and d.employee.employeeId = ?3";
        TypedQuery<DamageItem> query = entityManager.createQuery(jpql, DamageItem.class);
        query.setParameter(1, start);
        query.setParameter(2, end);
        query.setParameter(3, employeeID);

        return query.getResultList();
    }
}
