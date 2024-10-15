/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import entity.Employee;

/**
 *
 * @author daoducdanh
 */
public class CurrentEmployee {
    private static Employee employee;
    
    public static Employee getEmployee(){
        return employee;
    }
    
    public static void setEmployee(Employee employee){
        CurrentEmployee.employee = employee;
    }
}
