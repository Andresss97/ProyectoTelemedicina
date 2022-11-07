/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author andre
 */
public class AdminMainView extends javax.swing.JPanel {
    
    /**
     * Creates new form AdminMainView
     */
    public AdminMainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        panelDown = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        lateral = new javax.swing.JPanel();
        patientsCount = new javax.swing.JLabel();
        doctorsCount = new javax.swing.JLabel();
        homeButton = new javax.swing.JButton();
        panelLateralDer = new javax.swing.JPanel();
        createDoctorButton = new javax.swing.JButton();
        refeshButton = new javax.swing.JButton();
        central = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(561, 371));
        setLayout(new java.awt.BorderLayout());

        container.setLayout(new java.awt.BorderLayout());

        closeButton.setText("Close socket");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        openButton.setText("Open socket");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDownLayout = new javax.swing.GroupLayout(panelDown);
        panelDown.setLayout(panelDownLayout);
        panelDownLayout.setHorizontalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDownLayout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDownLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(openButton))
                .addGap(25, 25, 25))
        );

        container.add(panelDown, java.awt.BorderLayout.PAGE_END);

        patientsCount.setText("patients");

        doctorsCount.setText("doctors");

        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lateralLayout = new javax.swing.GroupLayout(lateral);
        lateral.setLayout(lateralLayout);
        lateralLayout.setHorizontalGroup(
            lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorsCount)
                    .addComponent(patientsCount))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
        );
        lateralLayout.setVerticalGroup(
            lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(homeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientsCount)
                .addGap(18, 18, 18)
                .addComponent(doctorsCount)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        container.add(lateral, java.awt.BorderLayout.LINE_START);

        createDoctorButton.setText("Create Doctor");
        createDoctorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDoctorButtonActionPerformed(evt);
            }
        });

        refeshButton.setText("Refresh");
        refeshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refeshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLateralDerLayout = new javax.swing.GroupLayout(panelLateralDer);
        panelLateralDer.setLayout(panelLateralDerLayout);
        panelLateralDerLayout.setHorizontalGroup(
            panelLateralDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLateralDerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLateralDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createDoctorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refeshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLateralDerLayout.setVerticalGroup(
            panelLateralDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLateralDerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(refeshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createDoctorButton)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        container.add(panelLateralDer, java.awt.BorderLayout.LINE_END);

        central.setLayout(new java.awt.BorderLayout());
        container.add(central, java.awt.BorderLayout.CENTER);

        add(container, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed

    private void refeshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refeshButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refeshButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed

    }//GEN-LAST:event_homeButtonActionPerformed

    private void createDoctorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDoctorButtonActionPerformed
        // TODO add your handling code here:
        this.central.removeAll();
        this.central.repaint();
        
        JPanel panel = new CreateDoctorView();
        this.central.add(panel,BorderLayout.CENTER);
        panel.setVisible(true);
       
    }//GEN-LAST:event_createDoctorButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel central;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel container;
    private javax.swing.JButton createDoctorButton;
    private javax.swing.JLabel doctorsCount;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel lateral;
    private javax.swing.JButton openButton;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelLateralDer;
    private javax.swing.JLabel patientsCount;
    private javax.swing.JButton refeshButton;
    // End of variables declaration//GEN-END:variables
}
