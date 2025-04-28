package bus;

import com.itextpdf.text.DocumentException;
import entity.Order;
import entity.PurchaseOrder;
import entity.ReturnOrder;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PDFBUS extends Remote {
    byte[] generatePDF(Order order) throws RemoteException, DocumentException, IOException;
    byte[] generatePDFReturn(ReturnOrder returnOrder) throws IOException, DocumentException,RemoteException;
    byte[] generatePDFPurchase(PurchaseOrder purchaseOrder) throws IOException, DocumentException, RemoteException;
    void deletePDF(String path) throws RemoteException;
}
