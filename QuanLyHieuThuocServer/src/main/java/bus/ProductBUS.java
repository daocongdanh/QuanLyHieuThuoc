package bus;

import dto.BatchDTO;
import entity.Product;
import enums.ProductType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface ProductBUS extends Remote {

    boolean createProduct(Product product) throws RemoteException;

    List<Product> searchProductByKeyword(String keyword, String type, String productType, boolean active) throws RemoteException;

    Product searchProductBySDKOrId(String keyword) throws RemoteException;

    Optional<Product> searchProductById(String id) throws RemoteException;

    List<BatchDTO> getListBatchEnableProduct(Product product) throws RemoteException;

    List<Product> getAllProducts() throws RemoteException;

    Product getProductById(String id) throws RemoteException;

    boolean updateProduct(Product product) throws RemoteException;

    Product getProductBySDK(String SDK) throws RemoteException;

    boolean checkExistSDK(String registrationNumber) throws RemoteException;

    Product searchBySDKAndUnitId(String sdk, String unitId) throws RemoteException;

    List<Product> searchProductsBy4Field(String name, String registrationNumber, ProductType productType, Boolean active) throws RemoteException;
    byte[] getImageForProduct(String imageLink) throws RemoteException;
}
