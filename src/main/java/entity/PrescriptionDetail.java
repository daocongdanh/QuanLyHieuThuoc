/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author daoducdanh
 */
@Entity
@Table(name = "prescription_details")
public class PrescriptionDetail {
    
    @ManyToOne
    @JoinColumn(name = "unit_detail_id")
    @Id
    private UnitDetail unitDetail;
    
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    @Id
    private Prescription prescription;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "description")
    @Nationalized
    private String description;
    
    public PrescriptionDetail(){
        
    }

    public PrescriptionDetail(UnitDetail unitDetail, Prescription prescription, int quantity, String description) {
        this.unitDetail = unitDetail;
        this.prescription = prescription;
        this.quantity = quantity;
        this.description = description;
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        this.unitDetail = unitDetail;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PrescriptionDetail{" + "unitDetail=" + unitDetail + ", prescription=" + prescription + ", quantity=" + quantity + ", description=" + description + '}';
    }

        
    
}
