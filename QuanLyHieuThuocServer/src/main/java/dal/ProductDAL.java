/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Product;
import enums.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

/**
 *
 * @author daoducdanh
 */
public class ProductDAL implements BaseDAL<Product, String> {

    private EntityManager entityManager;

    public ProductDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Product product) {
        GenerateId generateId = new GenerateId(entityManager);
        product.setProductId(generateId.generateOrtherId(Product.class, "SP"));
        entityManager.persist(product);
        return true;
    }

    @Override
    public boolean update(Product product) {
        entityManager.merge(product);
        return true;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    public List<Product> searchProduct(String keyword, String type, String productType, boolean active) {
        StringBuilder queryBuilder = new StringBuilder("select p from Product p where p.active = ?1");

        if (!keyword.isEmpty()) {
            if (type.equals("Số đăng ký")) {
                queryBuilder.append(" and p.registrationNumber = ?2");
            } else if (type.equals("Tên sản phẩm")) {
                queryBuilder.append(" and p.name like ?2");
            }
        }

        if (!productType.equals("Tất cả")) {
            queryBuilder.append(" and p.productType = ?3");
        }

        TypedQuery<Product> query = entityManager.createQuery(queryBuilder.toString(), Product.class);

        query.setParameter(1, active);

        if (!keyword.isEmpty()) {
            if (type.equals("Số đăng ký")) {
                query.setParameter(2, keyword);
            } else {
                query.setParameter(2, "%" + keyword + "%");
            }
        }

        if (!productType.equals("Tất cả")) {
            query.setParameter(3, ProductType.valueOf(productType));
        }

        return query.getResultList();
    }

    public Product searchProductBySDKOrId(String keyword) {
        String jpql = "SELECT p FROM Product p "
                + "WHERE p.active = true "
                + "AND (p.registrationNumber = :keyword OR p.productId = :keyword)";

        try {
            Query query = entityManager.createQuery(jpql, Product.class);
            query.setParameter("keyword", keyword);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;  // Trả về null nếu không tìm thấy sản phẩm
        }
    }

    public Product findBySDK(String SDK) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.registrationNumber = ?1", Product.class);
        query.setParameter(1, SDK);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkExistSDK(String registrationNumber) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.registrationNumber = ?1", Long.class);
        query.setParameter(1, registrationNumber);

        try {
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> searchProductsBy4Field(String name, String registrationNumber, ProductType productType, Boolean active) {
        String jpql = "SELECT p FROM Product p WHERE 1=1";

        if (name != null && !name.isEmpty()) {
            jpql += " AND p.name LIKE :name";
        }
        if (registrationNumber != null && !registrationNumber.isEmpty()) {
            jpql += " AND p.registrationNumber LIKE :registrationNumber";
        }
        if (productType != null) {
            jpql += " AND p.productType = :productType";
        }
        if (active != null) {
            jpql += " AND p.active = :active";
        }
        
        Query query = entityManager.createQuery(jpql, Product.class);

        if (name != null && !name.isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        if (registrationNumber != null && !registrationNumber.isEmpty()) {
            query.setParameter("registrationNumber", "%" + registrationNumber + "%");
        }
        if (productType != null) {
            query.setParameter("productType", productType);
        }
        if (active != null) {
            query.setParameter("active", active);
        }

        return query.getResultList();
    }
    
    public List<Product> findByUnitId( String unitId) {
        StringBuilder queryBuilder = new StringBuilder("select p from Product p where p.unit.unitId = ?1");
        Query query = entityManager.createQuery(queryBuilder.toString(), Product.class);
        query.setParameter(1, unitId);
        return query.getResultList();
    }

    public Product searchBySDKAndUnitId(String sdk, String unitId) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.registrationNumber = ?1" +
                " and p.unit.unitId = ?2 ", Product.class);
        query.setParameter(1, sdk);
        query.setParameter(2, unitId);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
