package dal;

import entity.Promotion;
import enums.PromotionType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionDAL extends BaseDAL<Promotion, String> {
    List<Promotion> findAllByCurrentDate(PromotionType promotionType);
    List<Promotion> search(LocalDate date, String promotionType);
}
