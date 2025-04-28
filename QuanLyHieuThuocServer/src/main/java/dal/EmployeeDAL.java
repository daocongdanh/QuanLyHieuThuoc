package dal;

import entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeDAL extends BaseDAL<Employee, String> {
    List<Employee> findByKeyword(String keyword);
}
