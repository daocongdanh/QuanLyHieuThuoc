package bus.impl;

import bus.PromotionBUS;
import dal.CustomerDAL;
import dal.ProductDAL;
import dal.ProductPromotionDetailDAL;
import dal.PromotionDAL;
import dal.impl.CustomerDALImpl;
import dal.impl.ProductDALImpl;
import dal.impl.ProductPromotionDetailDALImpl;
import dal.impl.PromotionDALImpl;
import jakarta.persistence.EntityManager;
import entity.*;
import enums.PromotionType;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import util.MailTemplate;
import util.SendMail;

public class PromotionBUSImpl extends UnicastRemoteObject implements PromotionBUS {

    private final PromotionDAL promotionDAL;
    private final ProductDAL productDAL;
    private final ProductPromotionDetailDAL productPromotionDetailDAL;
    private final MailTemplate mailTemplate;
    private final SendMail sendMail;
    private final CustomerDAL customerDAL;
    private final EntityTransaction transaction;

    public PromotionBUSImpl(EntityManager entityManager) throws RemoteException {
        this.promotionDAL = new PromotionDALImpl(entityManager);
        this.productDAL = new ProductDALImpl(entityManager);
        this.productPromotionDetailDAL = new ProductPromotionDetailDALImpl(entityManager);
        this.mailTemplate = new MailTemplate(entityManager);
        this.sendMail = new SendMail();
        this.customerDAL = new CustomerDALImpl(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createPromotion(Promotion promotion, List<String> productIds) throws RemoteException {
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

    @Override
    public Promotion getPromotionByProduct(Product product) throws RemoteException {
        List<Promotion> promotions = promotionDAL.findAllByCurrentDate(PromotionType.PRODUCT);
        for (Promotion promotion : promotions) {
            ProductPromotionDetail productPromotionDetail =
                    productPromotionDetailDAL.findByProductAndPromotion(product, promotion);
            if (productPromotionDetail != null)
                return promotion;
        }
        return null;
    }

    @Override
    public Promotion getPromotionByOrder() throws RemoteException {
        List<Promotion> promotions = promotionDAL.findAllByCurrentDate(PromotionType.ORDER);
        for (Promotion promotion : promotions) {
            return promotion;
        }
        return null;
    }

    @Override
    public List<Promotion> getAllPromotions() throws RemoteException {
        return promotionDAL.findAll();
    }

    @Override
    public List<Promotion> search(LocalDate date, String promotionType)  throws RemoteException{
        return promotionDAL.search(date, promotionType);
    }

    @Override
    public Optional<Promotion> getPromotionById(String promotionId) throws RemoteException {
        return promotionDAL.findById(promotionId);
    }

    @Override
    public boolean updatePromotion(Promotion promotion, List<String> productIds) throws RemoteException {
        try {
            transaction.begin();
            promotionDAL.update(promotion);
            if (promotion.getPromotionType().equals(PromotionType.PRODUCT)) {
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

    @Override
    public List<Product> getAllByPromotion(Promotion promotion) {
        return productPromotionDetailDAL.findAllByPromotion(promotion)
                .stream()
                .map(ProductPromotionDetail::getProduct)
                .toList();
    }

    @Override
    public void sendMail(String promotionId)  throws RemoteException{
        Promotion promotion = promotionDAL.findById(promotionId).get();
        if (promotion.getPromotionType().equals(PromotionType.ORDER)) {
            String body = mailTemplate.mailOrder(promotion);
            String subject = "🎉 Khuyến Mãi Giảm Giá Trên Hóa Đơn! Mua Sắm Ngay Tại Nhà Thuốc 🎉";
            sendMail.sendMail("gvhoang30112004@gmail.com", subject, body);
        } else {
            String body = mailTemplate.mailProduct(promotion);
            String subject = "🎉 Ưu Đãi Đặc Biệt Cho Một Số Sản Phẩm Tại Nhà Thuốc! 🎉";
            sendMail.sendMail("gvhoang30112004@gmail.com", subject, body);
        }
        promotion.setStatus(false);
        promotionDAL.update(promotion);
    }
}
