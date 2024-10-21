/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import entity.Prescription;
import entity.UnitDetail;
import jakarta.persistence.EntityManager;
import entity.PrescriptionDetail;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author daoducdanh
 */
public class PrescriptionDetailDAL{

    private EntityManager entityManager;
    
    public PrescriptionDetailDAL(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public boolean insert(PrescriptionDetail prescriptionDetail) {
        entityManager.persist(prescriptionDetail);
        return true;
    }
    
    public boolean remove( PrescriptionDetail prescriptionDetail ){
        entityManager.remove(prescriptionDetail);
        return true;
    }
    
    public List<PrescriptionDetail> findAllByPrescription(String prescriptionId){
        String jpql = "select pd from PrescriptionDetail pd where pd.prescription.prescriptionId = ?1";
        TypedQuery<PrescriptionDetail> query = entityManager.createQuery(jpql, PrescriptionDetail.class);
        query.setParameter(1, prescriptionId);
        return query.getResultList();
    }

    public Optional<PrescriptionDetail> findByPrescriptionAndUnitDetail(String prescriptionId, int unitDetailId) {
        String jpql = "select pd from PrescriptionDetail pd where pd.prescription.prescriptionId = ?1 and pd.unitDetail.id = ?2";
        TypedQuery<PrescriptionDetail> query = entityManager.createQuery(jpql, PrescriptionDetail.class);
        query.setParameter(1, prescriptionId);
        query.setParameter(2, unitDetailId);
        try {
            PrescriptionDetail result = query.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
