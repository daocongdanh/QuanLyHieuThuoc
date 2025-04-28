package bus.impl;

import bus.PDFBUS;
import bus.ReturnOrderBUS;
import connectDB.ConnectDB;
import dal.BatchDAL;
import dal.ReturnOrderDAL;
import dto.ReturnOrderDetailDTO;
import dto.StatsPriceAndQuantityDTO;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReturnOrderBUSImpl extends UnicastRemoteObject implements ReturnOrderBUS {

    private BatchDAL batchDAL;
    private ReturnOrderDAL returnOrderDAL;
    private EntityTransaction transaction;
    private final PDFBUS pdfBUS = new PDFBUSImpl();

    public ReturnOrderBUSImpl(EntityManager entityManager) throws RemoteException {
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public synchronized ReturnOrder createReturnOrder(Employee employee, Customer customer, Order order,
                                     List<ReturnOrderDetailDTO> returnOrderDetailDTOs)  throws RemoteException {
        try {
            transaction.begin();

            if (employee == null) {
                throw new RuntimeException("Nhân viên không được rỗng");
            }
            if (order == null) {
                throw new RuntimeException("Hóa đơn không được rỗng");
            }

            List<ReturnOrderDetail> returnOrderDetails = new ArrayList<>();
            for (ReturnOrderDetailDTO detailDTO : returnOrderDetailDTOs) {
                if (detailDTO.getQuantityReturn() != 0) {
                    ReturnOrderDetail returnOrderDetail = new ReturnOrderDetail();
                    returnOrderDetail.setProduct(detailDTO.getProduct());
                    returnOrderDetail.setQuantity(detailDTO.getQuantityReturn());
                    returnOrderDetail.setPrice(detailDTO.getProduct().getSellingPrice());
                    returnOrderDetail.setReturnOrderDetailStatus(ReturnOrderDetailStatus.PENDING);
                    returnOrderDetail.setLineTotal();
                    returnOrderDetail.setReason(detailDTO.getReason());

                    returnOrderDetails.add(returnOrderDetail);
                }
            }

            if (returnOrderDetails.isEmpty()) {
                return null;
            }

            ReturnOrder returnOrder = new ReturnOrder(null, LocalDateTime.now(), employee, order, returnOrderDetails, false);
            returnOrderDAL.insert(returnOrder);
            transaction.commit();
//            pdfBUS.generatePDFReturn(returnOrder);

            return returnOrder;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return null;
        }
    }

    @Override
    public List<ReturnOrder> getAllReturnOrders() throws RemoteException {
        return returnOrderDAL.findAll();
    }

    @Override
    public List<ReturnOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee, String orderId, Boolean status) throws RemoteException {
        return returnOrderDAL.search(start, end, txtEmployee, orderId, status);
    }

    @Override
    public ReturnOrder findById(String id) {
        return returnOrderDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay don tra hang"));
    }

    @Override
    public boolean checkOrderIsReturned(String orderId) throws RemoteException {
        return returnOrderDAL.checkOrderIsReturned(orderId);
    }

    @Override
    public List<ReturnOrder> getListReturnOrdersByStatus(boolean status)  throws RemoteException{
        return returnOrderDAL.getListReturnOrdersByStatus(status);
    }

    @Override
    public StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
        List<ReturnOrder> returnOrders = returnOrderDAL.searchByDate(start, end);
        Integer quantity = returnOrders.size();
        double sumPrice = 0.0;
        for (ReturnOrder order : returnOrders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsPriceAndQuantityDTO(quantity, sumPrice);
    }

    @Override
    public List<ReturnOrder> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID)  throws RemoteException{
        return returnOrderDAL.searchByDateAndEmp(start, end, empID);
    }
}
