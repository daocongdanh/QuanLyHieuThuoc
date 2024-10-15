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
import java.util.List;

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
}
