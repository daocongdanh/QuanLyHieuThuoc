/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.ProductDAL;
import dal.UnitDAL;
import dal.UnitDetailDAL;
import dto.BatchDTO;
import dto.UnitDTO;
import jakarta.persistence.EntityManager;
import entity.*;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private UnitDetailDAL unitDetailDAL;

    public ProductBUS(EntityManager entityManager) {
        this.productDAL = new ProductDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.unitDAL = new UnitDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
    }

    public boolean createProduct(Product product, List<UnitDTO> unitDTOs) {
        try {
            transaction.begin();
            productDAL.insert(product);
            for (UnitDTO unitDTO : unitDTOs) {
                Unit unit = unitDAL.findByName(unitDTO.getName());
                UnitDetail unitDetail = new UnitDetail(unitDTO.getConversionRate(),
                        unitDTO.isIsDefault(), product, unit);
                unitDetailDAL.insert(unitDetail);
            }
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
    
    public Product searchProductBySDKOrId( String keyword ) {
        return productDAL.searchProductBySDKOrId(keyword);
    }
    
    public Optional<Product> searchProductById( String id ){
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
    public List<Product> getAllProducts(){
        return productDAL.findAll();
    }
    
//    public boolean createProduct1(Product product){
//        try{
//            transaction.begin();
//            productDAL.insert(product);
//            transaction.commit();
//            return true;
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//            transaction.rollback();
//            return false;
//        }
//    }
    public Product getProductById(String id){
        return productDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
    }
    public Product getProByName(String name){
        return productDAL.findByName(name);
    }
    public boolean updateProduct(Product product){
        try{
            transaction.begin();
            productDAL.update(product);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
}
