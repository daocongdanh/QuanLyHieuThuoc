/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Unit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

/**
 *
 * @author daoducdanh
 */
public class UnitDAL implements BaseDAL<Unit, String> {

    private EntityManager entityManager;

    public UnitDAL(EntityManager entityManager) {
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
    public boolean update(Unit unit) {
        entityManager.merge(unit);
        return true;
    }

    @Override
    public Optional<Unit> findById(String id) {
        return Optional.ofNullable(entityManager.find(Unit.class, id));
    }

    @Override
    public List<Unit> findAll() {
        return entityManager.createQuery("select u from Unit u", Unit.class).getResultList();
    }

    public Unit findByName(String name) {
        TypedQuery<Unit> query = entityManager.createQuery("select u from Unit u where u.name = ?1", Unit.class);
        query.setParameter(1, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Unit> findByNameSearch(String name) {
        TypedQuery<Unit> query = entityManager.createQuery("select u from Unit u where u.name like ?1", Unit.class);
        query.setParameter(1, "%" + name + "%");
        return query.getResultList();
    }

}
