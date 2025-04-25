package bus.impl;

import bus.EmployeeBUS;
import dal.EmployeeDAL;
import entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EmployeeBUSImpl extends UnicastRemoteObject implements EmployeeBUS {
    private final EmployeeDAL employeeDAL;
    private final EntityTransaction transaction;

    public EmployeeBUSImpl(EntityManager entityManager) throws RemoteException {
        this.employeeDAL = new EmployeeDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public boolean createEmployee(Employee employee) throws RemoteException{
        try {
            transaction.begin();
            employeeDAL.insert(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) throws RemoteException{
        try {
            transaction.begin();
            employeeDAL.update(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }

    @Override
    public Employee getEmployeeId(String id) throws RemoteException{
        return employeeDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
    }

    @Override
    public List<Employee> getAllEmployee() throws RemoteException {
        return employeeDAL.findAll();
    }

    @Override
    public List<Employee> getEmployeeByKeyword(String keyword) throws RemoteException {
        return employeeDAL.findByKeyword(keyword);
    }
}
