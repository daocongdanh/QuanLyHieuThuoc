

import bus.*;
import bus.impl.*;
import connectDB.ConnectDB;
import jakarta.persistence.EntityManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        ConnectDB.connect();
        EntityManager em = ConnectDB.getEntityManager();
        AccountBUS accountBUS = new AccountBUSImpl(em);
        BatchBUS batchBUS = new BatchBUSImpl(em);
        CustomerBUS customerBUS = new CustomerBUSImpl(em);
        DamageItemBUS damageItemBUS = new DamageItemBUSImpl(em);
        DamageItemDetailBUS damageItemDetailBUS = new DamageItemDetailBUSImpl(em);
        EmployeeBUS employeeBUS = new EmployeeBUSImpl(em);
        OrderBUS orderBUS = new OrderBUSImpl(em);
        OrderDetailBUS orderDetailBUS = new OrderDetailBUSImpl(em);
        ProductBUS productBUS = new ProductBUSImpl(em);
        PromotionBUS promotionBUS = new PromotionBUSImpl(em);
        PurchaseOrderBUS purchaseOrderBUS = new PurchaseOrderBUSImpl(em);
        PurchaseOrderDetailBUS purchaseOrderDetailBUS = new PurchaseOrderDetailBUSImpl(em);
        ReportBUS reportBUS = new ReportBUSImpl(em);
        ReturnOrderBUS returnOrderBUS = new ReturnOrderBUSImpl(em);
        ReturnOrderDetailBUS returnOrderDetailBUS = new ReturnOrderDetailBUSImpl(em);
        SupplierBUS supplierBUS = new SupplierBUSImpl(em);
        UnitBUS unitBUS = new UnitBUSImpl(em);

        LocateRegistry.createRegistry(9090);
        context.bind("rmi://CYBER:9090/accountService", accountBUS);
        context.bind("rmi://CYBER:9090/batchService", batchBUS);
        context.bind("rmi://CYBER:9090/customerService", customerBUS);
        context.bind("rmi://CYBER:9090/damageItemService", damageItemBUS);
        context.bind("rmi://CYBER:9090/damageItemDetailService", damageItemDetailBUS);
        context.bind("rmi://CYBER:9090/employeeService", employeeBUS);
        context.bind("rmi://CYBER:9090/orderService", orderBUS);
        context.bind("rmi://CYBER:9090/orderDetailService", orderDetailBUS);
        context.bind("rmi://CYBER:9090/productService", productBUS);
        context.bind("rmi://CYBER:9090/promotionService", promotionBUS);
        context.bind("rmi://CYBER:9090/purchaseOrderService", purchaseOrderBUS);
        context.bind("rmi://CYBER:9090/purchaseOrderDetailService", purchaseOrderDetailBUS);
        context.bind("rmi://CYBER:9090/reportService", reportBUS);
        context.bind("rmi://CYBER:9090/returnOrderService", returnOrderBUS);
        context.bind("rmi://CYBER:9090/returnOrderDetailService", returnOrderDetailBUS);
        context.bind("rmi://CYBER:9090/supplierService", supplierBUS);
        context.bind("rmi://CYBER:9090/unitService", unitBUS);



        System.out.println("Server is ready !!");
    }
}
