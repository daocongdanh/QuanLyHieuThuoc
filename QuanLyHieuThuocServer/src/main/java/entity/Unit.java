/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "units")
public class Unit implements Serializable {
    
    @Id
    @Column(name = "unit_id")
    private String unitId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    public Unit(){
        
    }

    public Unit(String unitId, String name) {
        this.unitId = unitId;
        this.name = name;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null)
            throw new RuntimeException("Tên đơn vị tính không hợp lệ");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Unit{" + "unitId=" + unitId + ", name=" + name + '}';
    }
    
    
}
