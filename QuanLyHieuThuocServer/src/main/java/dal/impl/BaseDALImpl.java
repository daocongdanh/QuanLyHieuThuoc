package dal.impl;

import dal.BaseDAL;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BaseDALImpl<T, ID> implements BaseDAL<T, ID> {
    private EntityManager entityManager;
    private Class<T> type;

    public BaseDALImpl(EntityManager entityManager, Class<T> type) {
        this.entityManager = entityManager;
        this.type = type;
    }

    @Override
    public boolean insert(T t) {
        try {
            entityManager.persist(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(T t) {
        try {
            entityManager.merge(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try {
            T entity = entityManager.find(type, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return entityManager.createQuery("select t from " + type.getSimpleName() + " t", type).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
