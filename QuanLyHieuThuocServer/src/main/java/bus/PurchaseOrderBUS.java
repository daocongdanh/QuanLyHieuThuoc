/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dto.PurchaseOrderDTO;
import dto.StatsPriceAndQuantityDTO;
import entity.Employee;
import entity.PurchaseOrder;
import entity.Supplier;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseOrderBUS extends Remote {
    boolean createPurchaseOrder(Employee employee, Supplier supplier, List<PurchaseOrderDTO> purchaseOrderDTOs) throws RemoteException;

    List<PurchaseOrder> getAllPurchaseOrders() throws RemoteException;

    List<PurchaseOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee) throws RemoteException;

    StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;

    List<PurchaseOrder> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) throws RemoteException;

    PurchaseOrder getByID(String id) throws RemoteException;
}
