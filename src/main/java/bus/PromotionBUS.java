/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.ProductDAL;
import dal.ProductPromotionDetailDAL;
import dal.PromotionDAL;
import jakarta.persistence.EntityManager;
import entity.*;
import enums.PromotionType;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public class PromotionBUS {

    private PromotionDAL promotionDAL;
    private ProductDAL productDAL;
    private ProductPromotionDetailDAL productPromotionDetailDAL;
    private EntityTransaction transaction;

    public PromotionBUS(EntityManager entityManager) {
        this.promotionDAL = new PromotionDAL(entityManager);
        this.productDAL = new ProductDAL(entityManager);
        this.productPromotionDetailDAL = new ProductPromotionDetailDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    public boolean createPromotion(Promotion promotion, List<String> productIds) {
        try {
            transaction.begin();

            promotionDAL.insert(promotion);
            if (promotion.getPromotionType().equals(PromotionType.PRODUCT)) {
                for (String productId : productIds) {
                    Product product = productDAL.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
                    ProductPromotionDetail productPromotionDetail
                            = new ProductPromotionDetail(product, promotion);
                    productPromotionDetailDAL.insert(productPromotionDetail);
                }
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public Promotion getPromotionByProduct(Product product){
        List<Promotion> promotions = promotionDAL.findAllByCurrentDate(PromotionType.PRODUCT);
        for(Promotion promotion : promotions){
            ProductPromotionDetail productPromotionDetail = 
                    productPromotionDetailDAL.findByProductAndPromotion(product, promotion);
            if(productPromotionDetail != null)
                return promotion;
        }
        return null;
    }
    
    public Promotion getPromotionByOrder(){
        List<Promotion> promotions = promotionDAL.findAllByCurrentDate(PromotionType.ORDER);
        for(Promotion promotion : promotions){
            return promotion;
        }
        return null;
    }

    public List<Promotion> getAllPromotions() {
        return promotionDAL.findAll();
    }

    public List<Promotion> search(LocalDate date, String promotionType) {
        return promotionDAL.search(date, promotionType);
    }
    
    public Optional<Promotion> getPromotionById(String promotionId){
        return promotionDAL.findById(promotionId);
    }
    
    public boolean updatePromotion(Promotion promotion, List<String> productIds){
        try {
            transaction.begin();

            promotionDAL.update(promotion);
            if(promotion.getPromotionType().equals(PromotionType.PRODUCT)){
                List<ProductPromotionDetail> details = productPromotionDetailDAL.findAllByPromotion(promotion);
                productPromotionDetailDAL.removeAll(details);
                List<ProductPromotionDetail> insertDetails = productIds.stream()
                        .map(pid -> new ProductPromotionDetail(productDAL.findById(pid).get(), promotion))
                        .toList();
                productPromotionDetailDAL.insertAll(insertDetails);
            }
            
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public List<Product> getAllByPromotion(Promotion promotion){
        return productPromotionDetailDAL.findAllByPromotion(promotion)
                .stream()
                .map(ProductPromotionDetail::getProduct)
                .toList();
    }
}
