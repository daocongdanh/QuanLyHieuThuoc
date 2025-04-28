package dal;

import entity.Product;
import entity.Promotion;
import enums.ProductType;
import java.util.List;
import java.util.Optional;

public interface ProductDAL extends BaseDAL<Product, String> {
    List<Product> searchProduct(String keyword, String type, String productType, boolean active);
    Product searchProductBySDKOrId(String keyword);
    Product findBySDK(String SDK);
    boolean checkExistSDK(String registrationNumber);
    List<Product> searchProductsBy4Field(String name, String registrationNumber, ProductType productType, Boolean active);
    List<Product> findByUnitId(String unitId);
    Product searchBySDKAndUnitId(String sdk, String unitId);
}
