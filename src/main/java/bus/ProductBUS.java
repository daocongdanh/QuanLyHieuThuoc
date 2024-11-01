/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.ProductDAL;
import dal.UnitDAL;
import dto.BatchDTO;
import jakarta.persistence.EntityManager;
import entity.*;
import enums.ProductType;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public class ProductBUS {

    private ProductDAL productDAL;
    private EntityTransaction transaction;
    private UnitDAL unitDAL;

    public ProductBUS(EntityManager entityManager) {
        this.productDAL = new ProductDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.unitDAL = new UnitDAL(entityManager);
    }

    public boolean createProduct(Product product) {
        try {
            transaction.begin();
            productDAL.insert(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    public List<Product> searchProductByKeyword(String keyword, String type, String productType, boolean active) {
        return productDAL.searchProduct(keyword, type, productType, active);
    }

    public Product searchProductBySDKOrId(String keyword) {
        return productDAL.searchProductBySDKOrId(keyword);
    }

    public Optional<Product> searchProductById(String id) {
        return productDAL.findById(id);
    }

    public List<BatchDTO> getListBatchEnableProduct(Product product) {
        List<Batch> listBatch = product.getListBatch();
        List<BatchDTO> batchDTOs = new ArrayList<>();
        for (Batch batch : listBatch) {
            if (batch.getExpirationDate().isBefore(LocalDate.now())) {
                BatchDTO tmp = new BatchDTO();
                tmp.setName(batch.getName());
                tmp.setStock(batch.getStock());
                tmp.setExpirationDate(batch.getExpirationDate());
                batchDTOs.add(tmp);
            }
        }
        return batchDTOs;
    }

    public List<Product> getAllProducts() {
        return productDAL.findAll();
    }

    public Product getProductById(String id) {
        return productDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
    }

    public boolean updateProduct(Product product) {
        try {
            transaction.begin();
            productDAL.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    public Product getProductBySDK(String SDK) {
        return productDAL.findBySDK(SDK);
    }

    public boolean checkExistSDK(String registrationNumber) {
        return productDAL.checkExistSDK(registrationNumber);
    }

    public Product searchBySDKAndUnitId( String sdk, String unitId ){
        return  productDAL.searchBySDKAndUnitId(sdk,unitId);
    }

    public List<Product> searchProductsBy4Field(String name, String registrationNumber, ProductType productType, Boolean active) {
        return productDAL.searchProductsBy4Field(name, registrationNumber, productType, active);
    }

}
