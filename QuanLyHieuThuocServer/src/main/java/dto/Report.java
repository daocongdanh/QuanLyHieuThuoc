/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daoducdanh
 */
public class Report implements Serializable {
    private List<Object> reports;
    private Map<String, Map<Integer, Double>> orderType;
    private double profit;

    public Report() {
    }

    public Report(List<Object> reports, Map<String, Map<Integer, Double>> orderType, double profit) {
        this.reports = reports;
        this.orderType = orderType;
        this.profit = profit;
    }

    public List<Object> getReports() {
        return reports;
    }

    public void setReports(List<Object> reports) {
        this.reports = reports;
    }

    public Map<String, Map<Integer, Double>> getOrderType() {
        return orderType;
    }

    public void setOrderType(Map<String, Map<Integer, Double>> orderType) {
        this.orderType = orderType;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    
    
    
}
