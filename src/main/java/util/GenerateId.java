/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import entity.DamageItem;
import entity.Order;
import entity.PurchaseOrder;
import entity.ReturnOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author daoducdanh
 */
public class GenerateId {
    private EntityManager entityManager;
    
    public GenerateId(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    
    public String generateOrderId(String phoneNumber, Class<?> clazz){
        // 1. Lấy tiền tố (HD hoặc HDT)
        String prefix = getPrefix(clazz);
        
        // 2. Lấy ngày hiện tại (yyyyMMdd)
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        
        // 3. Lấy 4 số cuối của số điện thoại
        String phoneLast4Digits = phoneNumber.substring(phoneNumber.length() - 4);

        // 4. Lấy mã tự động tăng (yyyy)
        String autoIncrementPart = String.format("%04d", getNextAutoIncrement(clazz));

        // Kết hợp lại tạo thành ID
        return prefix + datePart + phoneLast4Digits + autoIncrementPart;
    }

    private String getPrefix(Class<?> clazz) {
        if (clazz.equals(Order.class)) {
            return "HD";
        } else if (clazz.equals(ReturnOrder.class)) {
            return "HDT";
        } else if (clazz.equals(PurchaseOrder.class)){
            return "PNH";
        } else if (clazz.equals(DamageItem.class)){
            return "PXH";
        }
        else {
            throw new RuntimeException("Unsupported class type");
        }
    }

    private int getNextAutoIncrement(Class<?> clazz) {
        String sql = "select count(obj) from " + clazz.getSimpleName() + " obj where obj.orderDate = ?1";
        TypedQuery<Long> query = entityManager.createQuery(sql, Long.class);
        query.setParameter(1, LocalDate.now());
        
        return query.getSingleResult().intValue() + 1;
    }
    
    public String generateOrtherId(Class<?> clazz, String prefix){
        String id = "";
        String className = clazz.getSimpleName();
        String objectId = className.substring(0,1).toLowerCase() + className.substring(1) + "Id";
        try {
            String sql = "select max(obj." + objectId + ") from "+ className + " obj";
            
            String maxId = (String) entityManager.createQuery(sql).getSingleResult();
        
            if (maxId == null) {
                return String.format("%s00001", prefix);
            } else {
                int num = Integer.parseInt(maxId.substring(prefix.length())) + 1;
                id = String.format("%s%05d", prefix, num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
