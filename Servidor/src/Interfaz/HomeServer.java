/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import conexion.Server;
import creation.Conector;
import creation.ConnInterface;
import creation.DBCreation;
import creation.QuerysSelect;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class HomeServer extends javax.swing.JFrame {

    public static ConnInterface conector;
    private ServerSocket socket;
    private JPanel panelCentral;
    public static Server server;

    /**
     * Creates new form HomeServer
     */
    public HomeServer() {
        this.conector = new Conector();
        File url = new File(".//Database//DBproject.db");

        if (!url.exists()) {
            this.conector.connect();
            DBCreation.createDB(conector);
        } else {
            this.conector.connect();
        }

        new Thread(new Runnable() {
            public void run() {
                initSocket();
            }
        }).start();

        initComponents();
        this.bar.setVisible(false);
        this.panelCentral = central;
        this.setTitle("Telemedicine - Server");
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
        central = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        bar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        homeButton = new javax.swing.JMenuItem();
        signOffButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("User:");

        jLabel2.setText("Password:");

        jLabel3.setText("Forgot your password?");

        signInButton.setText("Sign in");
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout centralLayout = new javax.swing.GroupLayout(central);
        central.setLayout(centralLayout);
        centralLayout.setHorizontalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centralLayout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addGroup(centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(signInButton)
                    .addComponent(jLabel3)
                    .addGroup(centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(centralLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(password))
                        .addGroup(centralLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(34, 34, 34)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(167, 167, 167))
        );
        centralLayout.setVerticalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addGroup(centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signInButton)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        container.add(central, java.awt.BorderLayout.CENTER);

        getContentPane().add(container, java.awt.BorderLayout.PAGE_START);

        jMenu1.setText("Log");

        homeButton.setText("Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        jMenu1.add(homeButton);

        signOffButton.setText("SignOff");
        signOffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOffButtonActionPerformed(evt);
            }
        });
        jMenu1.add(signOffButton);

        bar.add(jMenu1);

        setJMenuBar(bar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        // TODO add your handling code here:
        String user = this.username.getText();
        String pass = this.password.getText();
        //tenemos que desencriptar la contraseña
        MessageDigest md=null;
        try 
        {
            md = MessageDigest.getInstance("SHA-512");
        } 
        catch (NoSuchAlgorithmException e) 
        {			
            e.printStackTrace();
        }
        md.update(pass.getBytes());
        byte [] pWordEncripted = md.digest();    
        String passEncripted = new String(pWordEncripted);
        QuerysSelect qs = new QuerysSelect();
        try {
            int id = qs.selectUser(user, passEncripted);
            System.out.println(id);
            if (id != 0) {
                this.container.removeAll();
                this.container.repaint();

                this.username.setText("");
                this.password.setText("");
                JPanel panelView = new AdminMainView();
                this.container.add(panelView, BorderLayout.CENTER);
                panelView.setVisible(true);
                this.bar.setVisible(true);
                pack();
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_signInButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        this.container.removeAll();
        this.container.repaint();

        JPanel panelView = new AdminMainView();
        this.container.add(panelView, BorderLayout.CENTER);
        panelView.setVisible(true);
        pack();
    }//GEN-LAST:event_homeButtonActionPerformed

    private void signOffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOffButtonActionPerformed
        // TODO add your handling code here:
        this.container.removeAll();
        this.container.repaint();

        this.container.add(this.panelCentral, BorderLayout.CENTER);
        this.panelCentral.setVisible(true);
        this.bar.setVisible(false);
        pack();
    }//GEN-LAST:event_signOffButtonActionPerformed

    private void initSocket() {
        try {
            this.socket = new ServerSocket(6000);
            Server server = new Server(this.socket);
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(HomeServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public JPanel getCentral() {
        return container;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar bar;
    private javax.swing.JPanel central;
    private javax.swing.JPanel container;
    private javax.swing.JMenuItem homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton signInButton;
    private javax.swing.JMenuItem signOffButton;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
