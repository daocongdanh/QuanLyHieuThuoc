package gui.manager;

import javax.swing.UIManager;
import gui.manager.stats.PnOverrall;
import gui.manager.stats.PnProductStats;
import gui.manager.stats.PnStatsByTime;

import java.rmi.RemoteException;

/**
 *
 * @author Hoang
 */
public class TABStats extends javax.swing.JPanel {

    public TABStats() throws RemoteException {
        UIManager.put("TabbedPane.tabHeight", 40);
        initComponents();
        tabbedStats.add("Tổng quan", new PnOverrall());
        tabbedStats.add("Thống kê theo thời gian", new PnStatsByTime());
        tabbedStats.add("Thống kê sản phẩm", new PnProductStats());
        tabbedStats.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedStats = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 6, new java.awt.Color(255, 255, 255)));
        setMinimumSize(new java.awt.Dimension(1130, 800));
        setPreferredSize(new java.awt.Dimension(1130, 800));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        tabbedStats.setBackground(new java.awt.Color(255, 255, 255));
        tabbedStats.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        add(tabbedStats);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabbedStats;
    // End of variables declaration//GEN-END:variables
}
