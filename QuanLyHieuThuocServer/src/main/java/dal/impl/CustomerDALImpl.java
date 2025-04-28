package dal.impl;

import dal.CustomerDAL;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class CustomerDALImpl extends BaseDALImpl<Customer, String> implements CustomerDAL {
    private EntityManager entityManager;
    
    public CustomerDALImpl(EntityManager entityManager) {
        super(entityManager, Customer.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Customer customer) {
        GenerateId generateId = new GenerateId(entityManager);
        customer.setCustomerId(generateId.generateOrtherId(Customer.class, "KH"));
        entityManager.persist(customer);
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        entityManager.merge(customer);
        return true;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer findByPhone(String phone) {
        String jpql = "SELECT c FROM Customer c WHERE c.phone = :keyword";
        try {
            Query query = entityManager.createQuery(jpql, Customer.class);
            query.setParameter("keyword", phone);
            return (Customer) query.getSingleResult();
        } catch (NoResultException e) {
            return null;  
        }
    }

    @Override
    public List<Customer> findByKeyword(String keyword) {
        TypedQuery<Customer> query = entityManager.createQuery("select e from Customer e where e.name like ?1 or e.phone like ?1 or e.email like ?1", Customer.class);
        query.setParameter(1, "%" + keyword + "%");
        return query.getResultList();
    }
}
