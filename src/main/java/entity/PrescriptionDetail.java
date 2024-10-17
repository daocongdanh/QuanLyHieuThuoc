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
        setUnitDetail(unitDetail);
        setPrescription(prescription);
        setQuantity(quantity);
        setDescription(description);
    }

    public UnitDetail getUnitDetail() {
        return unitDetail;
    }

    public void setUnitDetail(UnitDetail unitDetail) {
        if (unitDetail == null) {
            throw new RuntimeException("Chi tiết đơn vị tính không được rỗng!");
        }
        this.unitDetail = unitDetail;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        if (prescription == null) {
            throw new RuntimeException("Đơn thuốc mẫu không được rỗng!");
        }
        this.prescription = prescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("số lượng sản phẩm phải > 0!");
        }
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.equals("")) {
            throw new RuntimeException("Mô tả không được rỗng!");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return "PrescriptionDetail{" + "unitDetail=" + unitDetail + ", prescription=" + prescription + ", quantity=" + quantity + ", description=" + description + '}';
    }

        
    
}
