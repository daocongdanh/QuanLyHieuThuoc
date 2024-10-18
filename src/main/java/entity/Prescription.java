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

/**
 *
 * @author daoducdanh
 */

@Entity
@Table(name = "prescriptions")
public class Prescription {
    
    @Id
    @Column(name = "prescription_id")
    private String prescriptionId;
    
    @Column(name = "name")
    @Nationalized
    private String name;
    
    public Prescription(){
        
    }

    public Prescription(String prescriptionId, String name) {
        this.prescriptionId = prescriptionId;
        setName(name);
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new RuntimeException("Tên đơn thuốc mẫu không được rỗng!");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "Prescription{" + "prescriptionId=" + prescriptionId + ", name=" + name + '}';
    }
    
    
    
}
