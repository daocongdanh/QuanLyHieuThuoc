/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.BatchDAL;
import dal.EmployeeDAL;
import dal.ProductDAL;
import dal.ProductTransactionHistoryDAL;
import dal.PurchaseOrderDAL;
import dal.SupplierDAL;
import dal.UnitDAL;
import dal.UnitDetailDAL;
import dto.PurchaseOrderDTO;
import jakarta.persistence.EntityManager;
import entity.*;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
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
    private UnitDetailDAL unitDetailDAL;
    private ProductDAL productDAL;
    private EmployeeDAL employeeDAL;
    private SupplierDAL supplierDAL;
    private ProductTransactionHistoryDAL productTransactionHistoryDAL;

    public PurchaseOrderBUS(EntityManager entityManager) {
        this.purchaseOrderDAL = new PurchaseOrderDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.batchDAL = new BatchDAL(entityManager);
        this.unitDAL = new UnitDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
        this.productDAL = new ProductDAL(entityManager);
        this.employeeDAL = new EmployeeDAL(entityManager);
        this.supplierDAL = new SupplierDAL(entityManager);
        this.productTransactionHistoryDAL = new ProductTransactionHistoryDAL(entityManager);
    }

    public boolean createPurchaseOrder(String employeeId, String supplierId,
            List<PurchaseOrderDTO> purchaseOrderDTOs) {
        try {
            transaction.begin();

            Employee employee = employeeDAL.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
            Supplier supplier = supplierDAL.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy nhà cung cấp"));
            List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
            for (PurchaseOrderDTO purchaseOrderDTO : purchaseOrderDTOs) {
                Unit unit = unitDAL.findByName(purchaseOrderDTO.getUnitName());
                UnitDetail unitDetail
                        = unitDetailDAL.findByProductAndUnit(purchaseOrderDTO.getProductId(), unit.getUnitId());
                Batch batch = batchDAL.findByNameAndProduct(purchaseOrderDTO.getBatchName(), purchaseOrderDTO.getProductId());
                Product product = productDAL.findById(purchaseOrderDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
                
                PurchaseOrderDetail purchaseOrderDetail;
                if (batch == null) {
                    Batch newBatch = new Batch(null,
                            purchaseOrderDTO.getBatchName(), purchaseOrderDTO.getExpirationDate(),
                            purchaseOrderDTO.getQuantity() * unitDetail.getConversionRate(), product);
                    batchDAL.insert(newBatch);
                    purchaseOrderDetail
                            = new PurchaseOrderDetail(purchaseOrderDTO.getQuantity(),
                                    product.getPurchasePrice() * unitDetail.getConversionRate(),
                                    newBatch, unitDetail);
                } else {
                    purchaseOrderDetail
                            = new PurchaseOrderDetail(purchaseOrderDTO.getQuantity(),
                                    product.getPurchasePrice() * unitDetail.getConversionRate(),
                                    batch, unitDetail);
                    batch.setStock(batch.getStock() + purchaseOrderDTO.getQuantity() * unitDetail.getConversionRate());
                    batchDAL.update(batch);
                }
                purchaseOrderDetails.add(purchaseOrderDetail);
            }
            PurchaseOrder purchaseOrder = new PurchaseOrder(null, LocalDate.now(), employee, supplier, purchaseOrderDetails);
            purchaseOrderDAL.insert(purchaseOrder);
            
            for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails) {
                UnitDetail unitDetail = purchaseOrderDetail.getUnitDetail();
                Product product = unitDetail.getProduct();
                
                double costPrice = product.getPurchasePrice() * purchaseOrderDetail.getQuantity();
                int finalStock = batchDAL.getFinalStockByProduct(product.getProductId());
                
                ProductTransactionHistory productTransactionHistory
                        = new ProductTransactionHistory(purchaseOrder.getPurchaseOrderId(), purchaseOrder.getOrderDate(),
                                "Nhập hàng", purchaseOrder.getSupplier().getName(),
                                purchaseOrderDetail.getLineTotal(), costPrice,
                                purchaseOrderDetail.getQuantity() * unitDetail.getConversionRate(), finalStock, product);
                productTransactionHistoryDAL.insert(productTransactionHistory);
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    
    public List<PurchaseOrder> getAllPurchaseOrders(){
        return purchaseOrderDAL.findAll();
    }

    public List<PurchaseOrder> search(LocalDate start, LocalDate end, String txtEmployee) {
        return purchaseOrderDAL.search(start, end, txtEmployee);
    }
}
