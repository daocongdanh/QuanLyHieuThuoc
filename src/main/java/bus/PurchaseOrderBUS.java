/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.EmployeeDAL;
import dal.ProductDAL;
import dal.PurchaseOrderDAL;
import dal.SupplierDAL;
import dal.UnitDAL;
import dto.PurchaseOrderDTO;
import dto.StatsPriceAndQuantityDTO;
import jakarta.persistence.EntityManager;
import entity.*;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author daoducdanh
 */
public class PurchaseOrderBUS {

    private PurchaseOrderDAL purchaseOrderDAL;
    private EntityTransaction transaction;
    private BatchDAL batchDAL;
    private UnitDAL unitDAL;
    private ProductDAL productDAL;
    private EmployeeDAL employeeDAL;
    private SupplierDAL supplierDAL;

    public PurchaseOrderBUS(EntityManager entityManager) {
        this.purchaseOrderDAL = new PurchaseOrderDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.batchDAL = new BatchDAL(entityManager);
        this.unitDAL = new UnitDAL(entityManager);
        this.productDAL = new ProductDAL(entityManager);
        this.employeeDAL = new EmployeeDAL(entityManager);
        this.supplierDAL = new SupplierDAL(entityManager);
    }

    public boolean createPurchaseOrder(Employee employee, Supplier supplier,
        List<PurchaseOrderDTO> purchaseOrderDTOs) {
        try {
//            transaction.begin();
//            
//            if (employee == null) {
//                throw new RuntimeException("Nhân viên không được rỗng");
//            }
//            if (supplier == null) {
//                throw new RuntimeException("Nhà cung cấp không được rỗng");
//            }
//
//            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
//
//            for (PurchaseOrderDTO purchaseOrderDTO : purchaseOrderDTOs) {
//                Unit unit = unitDAL.findByName(purchaseOrderDTO.getUnitName());
//                if (unit == null) {
//                    throw new RuntimeException("Không tìm thấy đơn vị: " + purchaseOrderDTO.getUnitName());
//                }
//
//                UnitDetail unitDetail = unitDetailDAL.findByProductAndUnit(purchaseOrderDTO.getProductId(), unit.getUnitId());
//                if (unitDetail == null) {
//                    throw new RuntimeException("Không tìm thấy chi tiết đơn vị cho sản phẩm: " + purchaseOrderDTO.getProductId());
//                }
//
//                Product product = productDAL.findById(purchaseOrderDTO.getProductId())
//                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
//
//                Batch batch = batchDAL.findByNameAndProduct(purchaseOrderDTO.getBatchName(), purchaseOrderDTO.getProductId());
//                
//                PurchaseOrderDetail purchaseOrderDetail;
//                if (batch == null) {
//                    Batch newBatch = new Batch(null,
//                            purchaseOrderDTO.getBatchName(), purchaseOrderDTO.getExpirationDate(),
//                            purchaseOrderDTO.getQuantity() * unitDetail.getConversionRate(), product, true);
//                    batchDAL.insert(newBatch);
//
//                    purchaseOrderDetail = new PurchaseOrderDetail(purchaseOrderDTO.getQuantity(),
//                            product.getPurchasePrice() * unitDetail.getConversionRate(),
//                            newBatch, unitDetail);
//                } else {
//                    batch.setStock(batch.getStock() + purchaseOrderDTO.getQuantity() * unitDetail.getConversionRate());
//                    batchDAL.update(batch);
//
//                    purchaseOrderDetail = new PurchaseOrderDetail(purchaseOrderDTO.getQuantity(),
//                            product.getPurchasePrice() * unitDetail.getConversionRate(),
//                            batch, unitDetail);
//                }
//                purchaseOrderDetails.add(purchaseOrderDetail);
//            }
//
//            PurchaseOrder purchaseOrder = new PurchaseOrder(null, LocalDateTime.now(), employee, supplier, purchaseOrderDetails);
//            purchaseOrderDAL.insert(purchaseOrder);
//
//            Map<String, List<PurchaseOrderDetail>> map = purchaseOrderDetails.stream()
//                    .collect(Collectors.groupingBy(
//                            purchaseOrderDetail -> purchaseOrderDetail.getUnitDetail().getProduct().getProductId(),
//                            LinkedHashMap::new,
//                            Collectors.toList()
//                    ));
//            map.forEach((key, value) -> {
//                PurchaseOrderDetail purchaseOrderDetail = value.get(0);
//                UnitDetail unitDetail = purchaseOrderDetail.getUnitDetail();
//                Product product = unitDetail.getProduct();
//                int totalQuantity = value.stream()
//                        .mapToInt(x -> x.getQuantity() * x.getUnitDetail().getConversionRate())
//                        .sum();
//
//                double totalTransactionPrice = value.stream()
//                        .mapToDouble(PurchaseOrderDetail::getLineTotal)
//                        .sum();
//
//                double costPrice = product.getPurchasePrice() * totalQuantity;
//
//                int finalStock = batchDAL.getFinalStockByProduct(product.getProductId());
//
//                ProductTransactionHistory productTransactionHistory = new ProductTransactionHistory(
//                        purchaseOrder.getPurchaseOrderId(), // Mã giao dịch
//                        purchaseOrder.getOrderDate(),       // Ngày giao dịch
//                        "Nhập hàng",                        // Loại giao dịch
//                        purchaseOrder.getSupplier().getName(), // Tên nhà cung cấp
//                        totalTransactionPrice,              // Tổng giá trị giao dịch
//                        costPrice,                          // Giá vốn
//                        totalQuantity,                      // Tổng số lượng
//                        finalStock,                         // Số lượng tồn kho cuối cùng
//                        product                             // Sản phẩm
//                );
//
//                productTransactionHistoryDAL.insert(productTransactionHistory);
//            });
//
//            transaction.commit();
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }

    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderDAL.findAll();
    }

    public List<PurchaseOrder> search(LocalDateTime start, LocalDateTime end, String txtEmployee) {
        return purchaseOrderDAL.search(start, end, txtEmployee);
    }

    public StatsPriceAndQuantityDTO getQuantityAndSumPriceByDate(LocalDateTime start, LocalDateTime end) {
        List<PurchaseOrder> purchaseOrders = purchaseOrderDAL.searchByDate(start, end);
        Integer quantity = purchaseOrders.size();
        double sumPrice = 0.0;
        for (PurchaseOrder order : purchaseOrders) {
            sumPrice += order.getTotalPrice();
        }
        return new StatsPriceAndQuantityDTO(quantity, sumPrice);
    }
    
    public List<PurchaseOrder> getByDateAndEmp(LocalDateTime start, LocalDateTime end, String empID) {
        return purchaseOrderDAL.searchByDateAndEmp(start, end, empID);
    }
}
