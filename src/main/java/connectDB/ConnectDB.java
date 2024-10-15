/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author daoducdanh
 */
public class ConnectDB {

    private static ConnectDB instance = new ConnectDB();
    private static EntityManager em = null;

    public static void connect() {
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyHieuThuoc");
            em = emf.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public static EntityManager getEntityManager() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        return em;
    }

}
