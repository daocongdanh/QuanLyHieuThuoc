/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.ProductPromotionDetailDAL;
import entity.ProductPromotionDetail;
import entity.Promotion;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author daoducdanh
 */
public class MailTemplate {

    private ProductPromotionDetailDAL productPromotionDetailDAL;

    public MailTemplate(EntityManager entityManager) {
        productPromotionDetailDAL = new ProductPromotionDetailDAL(entityManager);
    }

    public String mailOrder(Promotion promotion) {
        String html = "";

        html += "<div marginwidth=\"0\" marginheight=\"0\"\n"
                + "    style=\"font-size:16px;line-height:24px!important;font-family:arial;background:#D2ECF5;padding:50px; color: #606060;\">\n"
                + "    <div style=\"max-width: 600px; margin: auto; margin-left: auto;\">\n"
                + "      <img src=\"https://cdnv2.tgdd.vn/mwg-static/ankhang/News/Thumb/1571386/25-10-31-10-elevit-dong-san-pham-cho-me-bau-giam-10-phan-tram-thumb638652897710923812.jpg\" alt=\"\"\n"
                + "        style=\"width: 100%; height: 300px; object-fit: cover;\">\n"
                + "      <div style=\"background-color: #ffffff; padding: 20px; margin-top: -5px;\">\n"
                + "        <p>Kính gửi Quý Khách Hàng,</p>\n"
                + "        <p>Nhằm tri ân sự tin tưởng của Quý Khách, chúng tôi xin gửi tới bạn chương trình <b>khuyến mãi đặc biệt giảm giá trên hóa đơn</b> với ưu đãi hấp dẫn:</p>\n"
                + "        <ul>";

        html += "<li><b>Giảm " + (int) (promotion.getDiscount() * 100) + "%</b> trên tổng giá trị hóa đơn của bạn!</li>";

        html += "<li><b>Thời gian áp dụng:</b> Từ ngày " + FormatDate.formatLocalDate(promotion.getStartedDate()) + " đến hết ngày "
                + FormatDate.formatLocalDate(promotion.getEndedDate()) + "</li>";

        html += "</ul>\n"
                + "        <p>Nhanh tay mua sắm để tận hưởng ưu đãi đặc biệt này nhé! <br>\n"
                + "          Mọi thắc mắc xin vui lòng liên hệ với chúng tôi qua số hotline hoặc email</p>\n"
                + "        <p>Xin chân thành cảm ơn và hân hạnh được phục vụ Quý Khách!</p>\n"
                + "        <p>Thân trọng, <br>\n"
                + "          Nhà Thuốc Tây Á</p>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </div>";

        return html;
    }

    public String mailProduct(Promotion promotion) {
        String html = "";

        html += "<div marginwidth=\"0\" marginheight=\"0\"\n"
                + "    style=\"font-size:16px;line-height:24px!important;font-family:arial;background:#D2ECF5;padding:50px; color: #606060;\">\n"
                + "    <div style=\"max-width: 600px; margin: auto; margin-left: auto;\">\n"
                + "      <img src=\"https://cdnv2.tgdd.vn/mwg-static/ankhang/News/Thumb/1571386/25-10-31-10-elevit-dong-san-pham-cho-me-bau-giam-10-phan-tram-thumb638652897710923812.jpg\" alt=\"\"\n"
                + "        style=\"width: 100%; height: 300px; object-fit: cover;\">\n"
                + "      <div style=\"background-color: #ffffff; padding: 20px; margin-top: -5px;\">\n"
                + "        <p>Kính gửi Quý Khách Hàng,</p>\n"
                + "        <p>Chúng tôi xin gửi đến bạn chương trình khuyến mãi đặc biệt <b>giảm giá lên tới ";

        html += (int) (promotion.getDiscount() * 100) + "%</b> cho một số sản phẩm được chọn:</p>";

        html += "<ul>\n"
                + "          <li><b>Các sản phẩm áp dụng khuyến mãi:</b>\n"
                + "            <ul>";

        List<ProductPromotionDetail> details = productPromotionDetailDAL.findAllByPromotion(promotion);

        for (ProductPromotionDetail detail : details) {
            html += "<li><b>" + detail.getProduct().getName() + "</b></li>";
        }

        html += "</ul>\n"
                + "          </li>\n"
                + "          <li><b>Thời gian áp dụng:</b> Từ ngày ";

        html += FormatDate.formatLocalDate(promotion.getStartedDate()) + " đến hết ngày "
                + FormatDate.formatLocalDate(promotion.getEndedDate()) + "</li>";

        html += "</ul>\n"
                + "        <p>Đừng bỏ lỡ cơ hội sở hữu sản phẩm yêu thích với mức giá ưu đãi nhất! <br>\n"
                + "          Mọi thắc mắc xin vui lòng liên hệ với chúng tôi qua số hotline hoặc email</p>\n"
                + "        <p>Xin chân thành cảm ơn và hân hạnh được phục vụ Quý Khách!</p>\n"
                + "        <p>Thân trọng, <br>\n"
                + "          Nhà Thuốc Tây Á</p>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </div>";
        return html;
    }
}
