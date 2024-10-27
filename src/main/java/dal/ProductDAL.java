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
    public Product findByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.name = ?1", Product.class);
        query.setParameter(1, name);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
