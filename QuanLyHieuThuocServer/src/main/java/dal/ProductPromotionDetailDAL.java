package dal;

import entity.ProductPromotionDetail;
import entity.Promotion;
import entity.Product;
import java.util.List;

public interface ProductPromotionDetailDAL {
    boolean insert(ProductPromotionDetail productPromotionDetail);
    ProductPromotionDetail findByProductAndPromotion(Product product, Promotion promotion);
    List<ProductPromotionDetail> findAllByPromotion(Promotion promotion);
    boolean removeAll(List<ProductPromotionDetail> productPromotionDetail);
    boolean insertAll(List<ProductPromotionDetail> productPromotionDetail);
}
