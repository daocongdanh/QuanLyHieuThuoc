/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.BatchDAL;
import dal.ReturnOrderDAL;
import dto.ReturnOrderDetailDTO;
import dto.StatsPriceAndQuantityDTO;
import entity.Batch;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.GeneratePDF;

/**
 *
 * @author Hoang
 */
public class ReturnOrderBUS {

    private BatchDAL batchDAL;
    private ReturnOrderDAL returnOrderDAL;
    private EntityTransaction transaction;
    private final GeneratePDF generatePDF = new GeneratePDF();

    public ReturnOrderBUS(EntityManager entityManager) {
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();
    }

    public boolean createReturnOrder(Employee employee, Customer customer, Order order,
            List<ReturnOrderDetailDTO> returnOrderDetailDTOs) {
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
                return false;
            }

            ReturnOrder returnOrder = new ReturnOrder(null, LocalDateTime.now(), employee, order, returnOrderDetails, false);
            returnOrderDAL.insert(returnOrder);
            transaction.commit();
            generatePDF.GeneratePDFReturn(returnOrder);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    public List<ReturnOrder> getAllReturnOrders() {
        return returnOrderDAL.findAll();
    }

    public List<ReturnOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee, String orderId, Boolean status) {
        return returnOrderDAL.search(start, end, txtEmployee, orderId, status);
    }

    public ReturnOrder findById(String id) {
        return returnOrderDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay don tra hang"));
    }

    public boolean checkOrderIsReturned(String orderId) {
        return returnOrderDAL.checkOrderIsReturned(orderId);
    }

    public List<ReturnOrder> getListReturnOrdersByStatus(boolean status) {
        return returnOrderDAL.getListReturnOrdersByStatus(status);
    }

    public StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) {
        List<ReturnOrder> returnOrders = returnOrderDAL.searchByDate(start, end);
        Integer quantity = returnOrders.size();
        double sumPrice = 0.0;
        for (ReturnOrder order : returnOrders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsPriceAndQuantityDTO(quantity, sumPrice);
    }

    public List<ReturnOrder> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) {
        return returnOrderDAL.searchByDateAndEmp(start, end, empID);
    }

}
