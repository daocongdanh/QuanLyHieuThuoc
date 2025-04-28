package dal;

import entity.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierDAL extends BaseDAL<Supplier, String> {
    Supplier findByName(String name);
    List<Supplier> searchSuppliersByText(String text);
    Supplier findByPhone(String txtTim);
}
