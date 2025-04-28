package bus.impl;

import bus.ProductBUS;
import dal.ProductDAL;
import dal.UnitDAL;
import dal.impl.ProductDALImpl;
import dal.impl.UnitDALImpl;
import dto.BatchDTO;
import jakarta.persistence.EntityManager;
import entity.*;
import enums.ProductType;
import jakarta.persistence.EntityTransaction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductBUSImpl extends UnicastRemoteObject implements ProductBUS {

    private final ProductDAL productDAL;
    private final EntityTransaction transaction;
    private final UnitDAL unitDAL;

    public ProductBUSImpl(EntityManager entityManager) throws RemoteException {
        this.productDAL = new ProductDALImpl(entityManager);
        this.transaction = entityManager.getTransaction();
        this.unitDAL = new UnitDALImpl(entityManager);
    }

    @Override
    public boolean createProduct(Product product) {
        try {
            transaction.begin();
            productDAL.insert(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<Product> searchProductByKeyword(String keyword, String type, String productType, boolean active)  throws RemoteException {
        return productDAL.searchProduct(keyword, type, productType, active);
    }

    @Override
    public Product searchProductBySDKOrId(String keyword) throws RemoteException {
        return productDAL.searchProductBySDKOrId(keyword);
    }

    @Override
    public Optional<Product> searchProductById(String id) throws RemoteException {
        return productDAL.findById(id);
    }

    @Override
    public List<BatchDTO> getListBatchEnableProduct(Product product) throws RemoteException {
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

    @Override
    public List<Product> getAllProducts() throws RemoteException {
        List<Product> p = productDAL.findAll();

        return p;
    }

    @Override
    public Product getProductById(String id) throws RemoteException {
        return productDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id = " + id));
    }

    @Override
    public boolean updateProduct(Product product) throws RemoteException {
        try {
            transaction.begin();
            productDAL.update(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Product getProductBySDK(String SDK) throws RemoteException {
        return productDAL.findBySDK(SDK);
    }

    @Override
    public boolean checkExistSDK(String registrationNumber) throws RemoteException {
        return productDAL.checkExistSDK(registrationNumber);
    }

    @Override
    public Product searchBySDKAndUnitId(String sdk, String unitId) throws RemoteException {
        return productDAL.searchBySDKAndUnitId(sdk, unitId);
    }

    @Override
    public List<Product> searchProductsBy4Field(String name, String registrationNumber, ProductType productType, Boolean active) throws RemoteException {
        return productDAL.searchProductsBy4Field(name, registrationNumber, productType, active);
    }

    @Override
    public byte[] getImageForProduct(String imageLink) throws RemoteException {
        try (InputStream is = getClass().getResourceAsStream("/img/"+imageLink)) {
            if (is == null) {
                throw new FileNotFoundException("Không tìm thấy ảnh: " + imageLink);
            }
            return is.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveImageForProduct(String imageLink, byte[] imageData) throws RemoteException {
        String path = "src/main/resources/img/" + imageLink + ".jpg";

        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
