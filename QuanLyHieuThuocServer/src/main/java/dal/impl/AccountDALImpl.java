package dal.impl;

import dal.AccountDAL;
import jakarta.persistence.EntityManager;
import entity.Account;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import util.GenerateId;

public class AccountDALImpl extends BaseDALImpl<Account, String> implements AccountDAL {

    private EntityManager entityManager;

    public AccountDALImpl(EntityManager entityManager) {
        super(entityManager, Account.class);
        this.entityManager = entityManager;
    }

    @Override
    public boolean insert(Account account) {
        GenerateId generateId = new GenerateId(entityManager);
        account.setAccountId(generateId.generateOrtherId(Account.class, "TK"));
        entityManager.persist(account);
        return true;
    }

    @Override
    public Account login(String username) {
        String jpql = "select a from Account a where a.employee.employeeId = ?1 ";
        TypedQuery<Account> query = entityManager.createQuery(jpql, Account.class);
        query.setParameter(1, username);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<Account> findByEmployeeId(String employeeId) {
        try {
            Account account = entityManager.createQuery(
                "select a from Account a where a.employee.employeeId = ?1", Account.class)
                .setParameter(1, employeeId)
                .getSingleResult();
            return Optional.ofNullable(account);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
