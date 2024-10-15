/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;

import dal.PrescriptionDAL;
import dal.PrescriptionDetailDAL;
import dal.UnitDAL;
import dal.UnitDetailDAL;
import dto.PrescriptionDTO;
import jakarta.persistence.EntityManager;
import java.util.List;
import entity.*;
import jakarta.persistence.EntityTransaction;

/**
 *
 * @author daoducdanh
 */
public class PrescriptionBUS {

    private PrescriptionDAL prescriptionDAL;
    private EntityTransaction transaction;
    private UnitDAL unitDAL;
    private UnitDetailDAL unitDetailDAL;
    private PrescriptionDetailDAL prescriptionDetailDAL;

    public PrescriptionBUS(EntityManager entityManager) {
        this.prescriptionDAL = new PrescriptionDAL(entityManager);
        this.transaction = entityManager.getTransaction();
        this.unitDAL = new UnitDAL(entityManager);
        this.unitDetailDAL = new UnitDetailDAL(entityManager);
        this.prescriptionDetailDAL = new PrescriptionDetailDAL(entityManager);
    }

    public boolean createPrescription(Prescription prescription, List<PrescriptionDTO> prescriptionDTOs) {
        try {
            transaction.begin();
            prescriptionDAL.insert(prescription);
            for (PrescriptionDTO prescriptionDTO : prescriptionDTOs) {
                Unit unit = unitDAL.findByName(prescriptionDTO.getUnitName());
                UnitDetail unitDetail
                        = unitDetailDAL.findByProductAndUnit(prescriptionDTO.getProductId(), unit.getUnitId());

                PrescriptionDetail prescriptionDetail
                        = new PrescriptionDetail(unitDetail, prescription, prescriptionDTO.getQuantity(), prescriptionDTO.getDescription());
                prescriptionDetailDAL.insert(prescriptionDetail);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
            return false;
        }

    }

    public Prescription getPrescriptionById(String id) {
        return prescriptionDAL.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn thuốc mẫu với id = " + id));
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDAL.findAll();
    }
    
    public List<PrescriptionDetail> getAllPrescripDetailsByPrescription(String prescriptionId){
        return prescriptionDetailDAL.findAllByPrescription(prescriptionId);
    }
}
