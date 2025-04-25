package bus;

import entity.Batch;
import entity.Product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface BatchBUS extends Remote {

    List<Batch> getListBatchEnable(Product product) throws RemoteException;

    int getFinalStockByProduct(String productId) throws RemoteException;

    Batch getBatchByNameAndProduct(String batchName, String productId) throws RemoteException;

    List<Batch> getAllBatch() throws RemoteException;

    List<Batch> getListBatchByProductEnableAndOnDay(String productId) throws RemoteException;

    Batch getBatchByName(String batchName) throws RemoteException;

    Map<Product, List<Batch>> getListBatchExpiration() throws RemoteException;

    Batch getFirstByProduct(String productId) throws RemoteException;
}
