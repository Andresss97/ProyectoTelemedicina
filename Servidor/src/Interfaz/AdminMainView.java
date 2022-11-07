/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

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

        jPanel1 = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        lateral = new javax.swing.JPanel();
        patientsCount = new javax.swing.JLabel();
        doctorsCount = new javax.swing.JLabel();
        central = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(561, 371));
        setLayout(new java.awt.BorderLayout());

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(openButton))
                .addGap(25, 25, 25))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);

        patientsCount.setText("patients");

        doctorsCount.setText("doctors");

        javax.swing.GroupLayout lateralLayout = new javax.swing.GroupLayout(lateral);
        lateral.setLayout(lateralLayout);
        lateralLayout.setHorizontalGroup(
            lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doctorsCount)
                    .addComponent(patientsCount))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        lateralLayout.setVerticalGroup(
            lateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lateralLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(patientsCount)
                .addGap(18, 18, 18)
                .addComponent(doctorsCount)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        add(lateral, java.awt.BorderLayout.LINE_START);

        central.setPreferredSize(new java.awt.Dimension(561, 371));

        javax.swing.GroupLayout centralLayout = new javax.swing.GroupLayout(central);
        central.setLayout(centralLayout);
        centralLayout.setHorizontalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );
        centralLayout.setVerticalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        add(central, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel central;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel doctorsCount;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel lateral;
    private javax.swing.JButton openButton;
    private javax.swing.JLabel patientsCount;
    // End of variables declaration//GEN-END:variables
}
