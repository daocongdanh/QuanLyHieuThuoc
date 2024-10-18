/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.common;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Color;
import java.awt.Insets;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import util.ResizeImage;

/**
 *
 * @author Hoang
 */
public class MenuChoice extends javax.swing.JPanel {

    private boolean isActive;

    public MenuChoice() {
        initComponents();
        isActive = false;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setTitleMenu(String title) {
        titleMenu.setText(title);
    }

    public void setIconMenu(ImageIcon image) {
        iconMenu.setIcon(ResizeImage.resizeImage(image, 35, 35));
    }

    public void setDefault() {
        Box.setBorder(null);
        Box.setBackground(new Color(211, 237, 187));
        titleMenu.setForeground(new Color(46, 46, 46));
        this.isActive = false;
    }

    public void setActive() {
        Box.setBorder(new FlatLineBorder(new Insets(1, 1, 1, 1), new Color(162, 195, 139), 1, 30));
        Box.setBackground(new Color(162, 195, 139));
        titleMenu.setForeground(new Color(255, 255, 255));
        this.isActive = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Box = new javax.swing.JPanel();
        iconMenu = new javax.swing.JLabel();
        titleMenu = new javax.swing.JLabel();

        setBackground(new java.awt.Color(162, 195, 139));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        Box.setBackground(new java.awt.Color(204, 204, 204));
        Box.setPreferredSize(new java.awt.Dimension(288, 60));

        iconMenu.setBackground(new java.awt.Color(244, 250, 255));

        titleMenu.setBackground(new java.awt.Color(244, 250, 255));
        titleMenu.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N

        javax.swing.GroupLayout BoxLayout = new javax.swing.GroupLayout(Box);
        Box.setLayout(BoxLayout);
        BoxLayout.setHorizontalGroup(
            BoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoxLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(iconMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        BoxLayout.setVerticalGroup(
            BoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoxLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BoxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(iconMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Box, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        // TODO add your handling code here:
        if (isActive == false) {
            Box.setBackground(new Color(162, 195, 139));
            titleMenu.setForeground(new Color(255, 255, 255));
        }

    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
        if (isActive != true) {
            Box.setBackground(new Color(211, 237, 187));
            titleMenu.setForeground(new Color(46, 46, 46));
        }
    }//GEN-LAST:event_formMouseExited

    public static void menuSwitch(JPanel panelMoi, MenuChoice menuChoice, JPanel mainContent,
            List<MenuChoice> menuList, JPanel currentPanel) {
        if ( panelMoi.equals(currentPanel)){
            return;
        }
        
        for (MenuChoice menu : menuList) {
            menu.setDefault();
        }
        menuChoice.setActive();

        mainContent.removeAll();
        mainContent.add(panelMoi).setVisible(true);
        mainContent.repaint();
        mainContent.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Box;
    private javax.swing.JLabel iconMenu;
    private javax.swing.JLabel titleMenu;
    // End of variables declaration//GEN-END:variables
}
