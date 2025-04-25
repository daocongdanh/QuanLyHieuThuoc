package bus;

import entity.Promotion;
import entity.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionBUS extends Remote {

    boolean createPromotion(Promotion promotion, List<String> productIds) throws RemoteException;

    Promotion getPromotionByProduct(Product product) throws RemoteException;

    Promotion getPromotionByOrder() throws RemoteException;

    List<Promotion> getAllPromotions() throws RemoteException;

    List<Promotion> search(LocalDate date, String promotionType) throws RemoteException;

    Optional<Promotion> getPromotionById(String promotionId) throws RemoteException;

    boolean updatePromotion(Promotion promotion, List<String> productIds) throws RemoteException;

    List<Product> getAllByPromotion(Promotion promotion) throws RemoteException;

    void sendMail(String promotionId) throws RemoteException;
}
