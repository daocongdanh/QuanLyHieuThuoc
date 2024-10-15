/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.EmployeeDAL;
import jakarta.persistence.EntityManager;
import entity.Employee;
import jakarta.persistence.EntityTransaction;
/**
 *
 * @author daoducdanh
 */
public class EmployeeBUS {
    private EmployeeDAL employeeDAL;
    private EntityTransaction transaction;
    
    public EmployeeBUS(EntityManager entityManager){
        this.employeeDAL = new EmployeeDAL(entityManager);
        this.transaction = entityManager.getTransaction();
    }
    
    public boolean createEmployee(Employee employee){
        try{
            transaction.begin();
            employeeDAL.insert(employee);
            transaction.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }
    }
    
    public Employee getEmployeeId(String id){
        return employeeDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
    }
}
