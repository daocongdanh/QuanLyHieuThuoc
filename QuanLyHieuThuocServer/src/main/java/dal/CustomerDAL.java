package dal;

import entity.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerDAL extends BaseDAL<Customer, String> {
    Customer findByPhone(String phone);
    List<Customer> findByKeyword(String keyword);
}
