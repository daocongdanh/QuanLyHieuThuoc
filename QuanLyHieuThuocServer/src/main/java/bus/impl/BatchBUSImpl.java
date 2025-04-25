package bus.impl;

import bus.BatchBUS;
import dal.BatchDAL;
import entity.Batch;
import entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.*;

public class BatchBUSImpl extends UnicastRemoteObject implements BatchBUS {

    private final BatchDAL batchDAL;
    private final EntityTransaction transaction;

    public BatchBUSImpl(EntityManager entityManager) throws  RemoteException{
        this.batchDAL = new BatchDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public List<Batch> getListBatchEnable(Product product) throws RemoteException {
        return batchDAL.findByProduct(product)
                .stream()
                .filter(batch -> batch.getExpirationDate().isAfter(LocalDate.now())
                        && batch.getStock() > 0 && batch.isStatus())
                .sorted(Comparator.comparing(Batch::getExpirationDate))
                .toList();
    }

    @Override
    public int getFinalStockByProduct(String productId) throws RemoteException {
        return batchDAL.getFinalStockByProduct(productId);
    }

    @Override
    public Batch getBatchByNameAndProduct(String batchName, String productId) throws RemoteException {
        return batchDAL.findByNameAndProduct(batchName, productId);
    }

    @Override
    public List<Batch> getAllBatch() throws RemoteException {
        return batchDAL.findAll1();
    }

    @Override
    public List<Batch> getListBatchByProductEnableAndOnDay(String productId) throws RemoteException {
        List<Batch> batches = batchDAL.findByProductId(productId);
        return batches.stream()
                .filter(batch -> batch.getExpirationDate().isAfter(LocalDate.now()) && batch.isStatus())
                .sorted(Comparator.comparing(Batch::getExpirationDate))
                .toList();
    }

    @Override
    public Batch getBatchByName(String batchName) throws RemoteException {
        return batchDAL.findByName(batchName);
    }

    @Override
    public Map<Product, List<Batch>> getListBatchExpiration() throws RemoteException {
        List<Batch> batches = batchDAL.getAllBatchExpiration();
        Map<Product, List<Batch>> map = new LinkedHashMap<>();

        for (Batch batch : batches) {
            Product product = batch.getProduct();
            map.computeIfAbsent(product, k -> new ArrayList<>()).add(batch);
        }
        return map;
    }

    @Override
    public Batch getFirstByProduct(String productId) throws RemoteException {
        return batchDAL.findByProductId(productId).get(0);
    }
}
