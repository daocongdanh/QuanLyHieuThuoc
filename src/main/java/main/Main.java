/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import bus.CustomerBUS;
import bus.EmployeeBUS;
import bus.OrderBUS;
import bus.PrescriptionBUS;
import bus.ProductBUS;
import bus.PromotionBUS;
import bus.PurchaseOrderBUS;
import bus.SupplierBUS;
import bus.UnitBUS;
import connectDB.ConnectDB;
import dal.BatchDAL;
import dal.OrderDAL;
import dal.PromotionDAL;
import dto.OrderDTO;
import dto.PrescriptionDTO;
import dto.PurchaseOrderDTO;
import dto.ReturnPurchaseDTO;
import dto.UnitDTO;
import entity.*;
import enums.PaymentMethod;
import enums.ProductType;
import enums.PromotionType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author daoducdanh
 */
public class Main {
    public static void main(String[] args) {
        ConnectDB.connect();
        UnitBUS unitBUS = new UnitBUS(ConnectDB.getEntityManager());
        ProductBUS productBUS = new ProductBUS(ConnectDB.getEntityManager());
        PrescriptionBUS prescriptionBUS = new PrescriptionBUS(ConnectDB.getEntityManager());
        BatchDAL batchDAL = new BatchDAL(ConnectDB.getEntityManager());
        PurchaseOrderBUS purchaseOrderBUS = new PurchaseOrderBUS(ConnectDB.getEntityManager());
        SupplierBUS supplierBUS = new SupplierBUS(ConnectDB.getEntityManager());
        EmployeeBUS employeeBUS = new EmployeeBUS(ConnectDB.getEntityManager());
        OrderDAL orderDAL = new OrderDAL(ConnectDB.getEntityManager());
        PromotionBUS promotionBUS = new PromotionBUS(ConnectDB.getEntityManager());
        PromotionDAL promotionDAL = new PromotionDAL(ConnectDB.getEntityManager());
        OrderBUS orderBUS = new OrderBUS(ConnectDB.getEntityManager());
        CustomerBUS customerBUS = new CustomerBUS(ConnectDB.getEntityManager());
//        Product product = new Product("SP001", "A", "B", "C", "D", "C", 
//                "E", "F", 0, 0, true, 0, ProductType.MEDICINE);
//        List<UnitDTO> unitDTOs = List.of(
//                new UnitDTO("Hộp", 1, true),
//                new UnitDTO("Vĩ", 10, false),
//                new UnitDTO("Viên", 100, false)
//        );
//        
//        productBUS.createProduct(product, unitDTOs);
//        for(Product p : productBUS.searchProductByKeyword("B", "Số đăng ký", "Tất cả", true)){
//            System.out.println(p);
//        }
//        Prescription prescription = new Prescription("DTM001", "Thuốc ho");
//        List<PrescriptionDTO> prescriptionDTOs = List.of(
//                new PrescriptionDTO("SP001", "Hộp", 10, "Ngày dùng 2 lần")
//        );
//        prescriptionBUS.createPrescription(prescription, prescriptionDTOs);
    
//        Batch batch = batchDAL.findByNameAndProduct("oo", "SP001");

//            List<PurchaseOrderDTO> purchaseOrderDTOs = List.of(
//                    new PurchaseOrderDTO("SP001", "Viên", 10000, 20, "LO003", LocalDate.now())
//            );
//            Supplier supplier = supplierBUS.getSupplierById("NCC001");
//            Employee employee = employeeBUS.getEmployeeId("NV00001");
//            purchaseOrderBUS.createPurchaseOrder(employee, supplier, purchaseOrderDTOs);
//        Supplier supplier = new Supplier(null, "Danh", "Kon Tum", "0392406660", "danh@gmail.com", "0222");
//        supplierBUS.createrSupplier(supplier);
//        
//        for(int i = 1 ; i <= 6 ; i++){
//            Employee employee = new Employee(null, "Danh123", "03924050550", "KonTum", "damh@gmail.com", "NhanVien");
//            employeeBUS.createEmployee(employee);
//        }
//        for(int i=1;i<=6;i++){
//            Employee employee = employeeBUS.getEmployeeId("NV00001");
//            Order order = new Order(null, LocalDate.now(), 0, 0, PaymentMethod.CASH, employee, null, null);
//            orderDAL.insert(order);
//        }

//        List<ReturnPurchaseDTO> returnPurchaseDTOs = List.of(
//                new ReturnPurchaseDTO("SP001", "Viên", 10, "LO003")
//        );
//        returnPurchaseBUS.createReturnPurchase("NV00001", "PNH2024092105500002", returnPurchaseDTOs);
//        Promotion promotion = 
//                new Promotion(null, LocalDate.of(2024, 9, 26), 
//                        LocalDate.of(2024, 9, 29), 0.1, PromotionType.PRODUCT);
//        promotionBUS.createPromotion(promotion, List.of("SP001"));
//        List<Promotion> promotions = promotionDAL.findAllByCurrentDate(PromotionType.PRODUCT);
//        for(Promotion promotion : promotions){
//            System.out.println(promotion);
//        }

//        List<OrderDTO> orderDTOs = List.of(
//                new OrderDTO("SP001", "Vĩ", 15, "LO002"),
//                new OrderDTO("SP001", "Viên", 50, "LO003"),
//                new OrderDTO("SP002", "Viên", 70, "LO005")
//        );
//        orderBUS.createOrder("NV00001", null, null, orderDTOs);
//        for(int i = 1 ; i <= 5 ; i++){
//            employeeBUS.createEmployee(new Employee(null, "Nhân viên thứ "+i, 
//                    "0392401233", "Kon Tum", "nhanvien@gmail.com" , "Nhân viên"));
//        }
        
//        for(int i = 1 ; i <= 5 ; i++){
//            customerBUS.createCustomer(new Customer(null, "Khách hàng thứ " + i,
//                    "039240123123", "Gò Vấp", "khachhang@gmail.com"));
//        }
        
//        for(int i = 1 ; i <= 5 ; i++){
////            customerBUS.createCustomer(new Customer(null, "Khách hàng thứ " + i,
////                    "039240123123", "Gò Vấp", "khachhang@gmail.com"));
////            supplierBUS.createrSupplier(new Supplier(null, "Công ty TNHH thứ " + i, 
////                    "Hồ Chí Minh", "19001221", "NCC@gmail.com", "0123123"));
//        }
//            Product product = new Product(null, name, registrationNumber, 
//                    activeIngredient, dosage, packaging, countryOfOrigin, 
//                    manufacturer, 0, 0, true, ProductType.MEDICINE);
//        productBUS.createProduct(new P, unitDTOs)
//        List<String> units = List.of("Viên", "Ống", "Lọ", "Hộp", "Vỉ", "Gói", "ML", "Gam", "Kg");
//        units.forEach(unit -> {
//            unitBUS.createUnit(new Unit(null, unit));
//        });
//        Product product = new Product(null, "Maxidom", "VD-9957-10", "Domperidon maleat 10mg", 
//                null, "hộp 10 gói x 4,220g", "Vương quốc Anh", "Glaxo Operations UK Limited", 20000, 25000, 
//                true, ProductType.MEDICINE);
//        List<UnitDTO> unitDTOs = List.of(
//                new UnitDTO("Gói", 1, true),
//                new UnitDTO("Hộp", 10, false)
//        );
//        productBUS.createProduct(product, unitDTOs);
//        Product product = new Product(null, "Septax", "VN-20467-17", "Ceftazidim (dưới dạng bột hỗn hợp vô khuẩn Ceftazidim pentahydrat và natri carbonat) 1g", 
//                "1g", "hộp 1 lọ", "Hy Lạp", null, 20000, 22000, 
//                true, ProductType.MEDICINE);
//        List<UnitDTO> unitDTOs = List.of(
//                new UnitDTO("Lọ", 1, true)
//        );
//        productBUS.createProduct(product, unitDTOs);

//        List<PurchaseOrderDTO> purchaseOrderDTOs = List.of(
//                    new PurchaseOrderDTO("SP00001", "Gói", 100, "SP0001-Lo002", LocalDate.now())
//        );
//        purchaseOrderBUS.createPurchaseOrder("NV00001", "NCC00001", purchaseOrderDTOs);

//            List<OrderDTO> orderDTOs = List.of(
//                new OrderDTO("SP00001", "Gói", 15, "SP0001-Lo001"),
//                new OrderDTO("SP00002", "Viên", 50, "SP0002-Lo001")
//        );
//        orderBUS.createOrder("NV00001", "KH00001", null, orderDTOs);
//        try{
//           Account account = new Account("!23", "@", "Asd", null);
//        }
//        catch(Exception e){
//            System.out.println("hehe :"  + e.getMessage());
//        }
//        System.out.println(orderDAL.findById("HD2024092312330001").get());
    }
}
