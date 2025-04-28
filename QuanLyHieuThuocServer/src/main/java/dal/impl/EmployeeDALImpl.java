package dal.impl;

import dal.EmployeeDAL;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class EmployeeDALImpl implements EmployeeDAL {
    private EntityManager entityManager;

    public EmployeeDALImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Employee employee) {
        GenerateId generateId = new GenerateId(entityManager);
        employee.setEmployeeId(generateId.generateOrtherId(Employee.class, "NV"));
        entityManager.persist(employee);
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        entityManager.merge(employee);
        return true;
    }

    @Override
    public Optional<Employee> findById(String id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public List<Employee> findByKeyword(String keyword) {
        TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e where e.name like ?1 or e.phone like ?1 or e.email like ?1", Employee.class);
        query.setParameter(1, "%" + keyword + "%");
        return query.getResultList();
    }  
}
