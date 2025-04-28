package dal;

import entity.Account;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public interface AccountDAL extends BaseDAL<Account, String> {
    Account login(String username);
    Optional<Account> findByEmployeeId(String employeeId);
}