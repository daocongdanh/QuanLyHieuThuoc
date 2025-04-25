/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dto.OrderDTO;
import dto.StatsPriceAndQuantityDTO;
import dto.StatsOrderByDayDTO;
import dto.StatsOrderByYearDTO;
import dto.StatsProductDTO;
import entity.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.*;



public interface OrderBUS extends Remote {
    boolean createOrder(Employee employee, Customer customer, Promotion promotion, List<OrderDTO> orderDTOs) throws RemoteException;

    List<Order> getAllOrders() throws RemoteException;

    List<Order> search(LocalDateTime start, LocalDateTime end, String txtCustomer, String txtEmployee) throws RemoteException;

    Order findById(String orderId) throws RemoteException;

    StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;

    List<StatsOrderByDayDTO> getStatisticByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;

    List<StatsOrderByDayDTO> getStatisticInDay(LocalDateTime now) throws RemoteException;

    List<StatsOrderByDayDTO> searchStats(LocalDateTime start, LocalDateTime end, String productType, String paymentType, String promotion) throws RemoteException;

    List<StatsOrderByYearDTO> searchStatsByYear(LocalDateTime start, LocalDateTime end, String productType, String paymentType, String promotion) throws RemoteException;

    List<StatsProductDTO> getStatisticProductByDateAndType(LocalDateTime start, LocalDateTime end, String productType) throws RemoteException;

    List<StatsProductDTO> getStatisticProductByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;

}
