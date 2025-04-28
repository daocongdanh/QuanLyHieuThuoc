package dal.impl;

import dal.SupplierDAL;
import entity.Supplier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import util.GenerateId;
import java.util.List;
import java.util.Optional;

public class SupplierDALImpl extends BaseDALImpl<Supplier, String> implements SupplierDAL {

    private EntityManager entityManager;

    public SupplierDALImpl(EntityManager entityManager) {
        super(entityManager, Supplier.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Supplier supplier) {
        GenerateId generateId = new GenerateId(entityManager);
        supplier.setSupplierId(generateId.generateOrtherId(Supplier.class, "NCC"));
        entityManager.persist(supplier);
        return true;
    }

    @Override
    public Supplier findByName(String name) {
        TypedQuery<Supplier> query = entityManager.createQuery("select s from Supplier s where s.name = ?1", Supplier.class);
        query.setParameter(1, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Supplier> searchSuppliersByText(String text) {
        TypedQuery<Supplier> query = entityManager.createQuery(
                "select s from Supplier s where lower(s.name) like lower(concat('%', ?1, '%')) or "
                + "lower(s.email) like lower(concat('%', ?1, '%')) or "
                + "s.phone like concat('%', ?1, '%')", Supplier.class);
        query.setParameter(1, text);
        return query.getResultList();
    }

    @Override
    public Supplier findByPhone(String txtTim) {
        TypedQuery<Supplier> query = entityManager.createQuery("select s from Supplier s where s.phone = ?1", Supplier.class);
        query.setParameter(1, txtTim);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
