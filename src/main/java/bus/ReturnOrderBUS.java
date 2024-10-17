/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import connectDB.ConnectDB;
import dal.BatchDAL;
import dal.ReturnOrderDAL;
import dal.UnitDetailDAL;
import dto.ReturnOrderDetailDTO;
import entity.Batch;
import entity.Customer;
import entity.Employee;
import entity.Order;
import entity.ReturnOrder;
import entity.ReturnOrderDetail;
import entity.UnitDetail;
import enums.ReturnOrderDetailStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang
 */
public class ReturnOrderBUS {

    private BatchDAL batchDAL;
    private UnitDetailDAL unitDetailDAL;
    private ReturnOrderDAL returnOrderDAL;
    private EntityTransaction transaction;

    public ReturnOrderBUS(EntityManager entityManager) {
        this.returnOrderDAL = new ReturnOrderDAL(ConnectDB.getEntityManager());
        this.unitDetailDAL = new UnitDetailDAL(ConnectDB.getEntityManager());
        this.batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        this.transaction = entityManager.getTransaction();
    }

    public boolean createReturnOrderBUS(Employee employee, Customer customer, Order order, List<ReturnOrderDetailDTO> returnOrderDetailDTOs) {
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
                ReturnOrderDetail returnOrderDetail = new ReturnOrderDetail();

                Batch batch = batchDAL.findByNameAndProduct(detailDTO.getBatchName(), detailDTO.getProduct().getProductId());
                UnitDetail unitDetail = detailDTO.getUnitDetail();
                returnOrderDetail.setBatch(batch);
                returnOrderDetail.setQuantity(detailDTO.getQuantityReturn());
                returnOrderDetail.setUnitDetail(unitDetail);
                returnOrderDetail.setPrice(detailDTO.getProduct().getPrice());
                returnOrderDetail.setReturnOrderDetailStatus(ReturnOrderDetailStatus.PENDING);
                returnOrderDetail.setLineTotal();
            }
            
            ReturnOrder returnOrder = new ReturnOrder(null, LocalDate.now(), null, employee, order, returnOrderDetails, false);
            returnOrderDAL.insert(returnOrder);
            transaction.commit();
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

//    public List<ReturnOrder> search(LocalDate start, LocalDate end, String txtEmployee) {
//        return returnOrderDAL.search(start, end, txtEmployee);
//    }
}
