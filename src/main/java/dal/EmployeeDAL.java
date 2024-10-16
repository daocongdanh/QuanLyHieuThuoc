/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;
/**
 *
 * @author daoducdanh
 */
public class EmployeeDAL implements BaseDAL<Employee, String>{
    private EntityManager entityManager;
    
    public EmployeeDAL(EntityManager entityManager) {
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

    public List<Employee> findByMultipleField(String name, String phone, String email) {
        StringBuilder queryBuilder = new StringBuilder("select e from Employee e where 1=1");

        if (!name.isEmpty()) {
            queryBuilder.append(" and e.name like :name");
        }
        if (!phone.isEmpty()) {
            queryBuilder.append(" and e.phone like :phone");
        }
        if (!email.isEmpty()) {
            queryBuilder.append(" and e.email like :email");
        }

        TypedQuery<Employee> query = entityManager.createQuery(queryBuilder.toString(), Employee.class);

        if (!name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (!phone.isEmpty()) {
            query.setParameter("phone", "%" + phone + "%");
        }
        if (!email.isEmpty()) {
            query.setParameter("email", "%" + email + "%");
        }
        return query.getResultList();
    }
    
}
