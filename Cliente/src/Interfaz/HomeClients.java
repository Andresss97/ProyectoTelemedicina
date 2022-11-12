/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import conexion.Client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import pojos.Doctor;
import pojos.Patient;

/**
 *
 * @author andre
 */
public class HomeClients extends javax.swing.JFrame {

    /**
     * Creates new form HomeClients
     */
    public static Client client;
    public static boolean doctorsView;
    public static boolean patientsView;
    public static Patient p;
    public static Doctor d;
    
    public HomeClients() {
        initComponents();
        this.setTitle("Telemedicine - Clients");
        this.bar.setVisible(false);
        this.doctorsView = false;
        this.patientsView = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new JPanel();
        central = new JPanel();
        userLabel = new JLabel();
        username = new JTextField();
        passwordLabel = new JLabel();
        password = new JPasswordField();
        signInButton = new JButton();
        recoverPasswordLabel = new JLabel();
        registerButton = new JButton();
        bar = new JMenuBar();
        log = new JMenu();
        homeButton = new JMenuItem();
        signOffButton = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.white);
        setName("Telemnedicine Client"); // NOI18N
        setResizable(false);

        container.setLayout(new BorderLayout());

        userLabel.setText("Username:");

        username.setName("user"); // NOI18N

        passwordLabel.setText("Password:");

        signInButton.setText("Sign in");
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        recoverPasswordLabel.setText("Forgot your password?");

        registerButton.setText("New Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        GroupLayout centralLayout = new GroupLayout(central);
        central.setLayout(centralLayout);
        centralLayout.setHorizontalGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(centralLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(signInButton)
                    .addComponent(recoverPasswordLabel)
                    .addGroup(centralLayout.createSequentialGroup()
                        .addGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(userLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(username, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                            .addComponent(password, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, centralLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registerButton)
                .addGap(30, 30, 30))
        );
        centralLayout.setVerticalGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, centralLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(registerButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(userLabel)
                    .addComponent(username, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(centralLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recoverPasswordLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(signInButton)
                .addGap(146, 146, 146))
        );

        username.getAccessibleContext().setAccessibleName("username");
        username.getAccessibleContext().setAccessibleDescription("");
        password.getAccessibleContext().setAccessibleName("password");

        container.add(central, BorderLayout.CENTER);

        getContentPane().add(container, BorderLayout.PAGE_START);

        log.setText("Log");

        homeButton.setText("Home");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        log.add(homeButton);

        signOffButton.setText("Sign off");
        signOffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signOffButtonActionPerformed(evt);
            }
        });
        log.add(signOffButton);

        bar.add(log);

        setJMenuBar(bar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signInButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        //Cogemos los datos
        String uName = this.username.getText();
        String pWord = this.password.getText();
        /*
        MessageDigest md=null;
        try 
        {
            md = MessageDigest.getInstance("SHA-512");
        } 
        catch (NoSuchAlgorithmException e) 
        {			
            e.printStackTrace();
        }
        md.update(pWord.getBytes());
        byte [] pWordEncripted = md.digest();    
        String passEncripted = new String(pWordEncripted);
        */
        try {
            //Conexión al servidor
            Socket socket = new Socket("localhost", 6000);
            client = new Client(socket);
            String[] data = new String[2];
            data[0] = uName;
            data[1] = pWord;//Contraseña
            //Envio de datos al server
            client.registerUser(data);
            client.listenForMessage();
            
            if(patientsView == true) {
                this.container.removeAll();
                this.container.repaint();
                
                JPanel panel =  new PatientsView();
                this.container.add(panel, BorderLayout.CENTER);
                this.bar.setVisible(true);
                panel.setVisible(true);
                pack();
            }
            else if(doctorsView == true) {
                this.container.removeAll();
                this.container.repaint();
                
                JPanel panel =  new DoctorsView();
                this.container.add(panel, BorderLayout.CENTER);
                this.bar.setVisible(true);
                panel.setVisible(true);
                pack();
            }
        } catch (IOException ex) {
            Logger.getLogger(HomeClients.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_signInButtonActionPerformed

    private void registerButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        this.container.removeAll();
        this.container.repaint();
        
        JPanel registerView = new Register(this);
        this.container.add(registerView, BorderLayout.CENTER);
        registerView.setVisible(true);
        
        pack();
    }//GEN-LAST:event_registerButtonActionPerformed

    private void homeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_homeButtonActionPerformed

    private void signOffButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_signOffButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signOffButtonActionPerformed
    
    public void setPatient() {
         this.container.removeAll();
         this.container.repaint();
                
         JPanel panel =  new PatientsView();
         this.container.add(panel, BorderLayout.CENTER);
         this.bar.setVisible(true);
         panel.setVisible(true);
         pack();
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
            java.util.logging.Logger.getLogger(HomeClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeClients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeClients().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JMenuBar bar;
    private JPanel central;
    private JPanel container;
    private JMenuItem homeButton;
    private JMenu log;
    private JPasswordField password;
    private JLabel passwordLabel;
    private JLabel recoverPasswordLabel;
    private JButton registerButton;
    private JButton signInButton;
    private JMenuItem signOffButton;
    private JLabel userLabel;
    private JTextField username;
    // End of variables declaration//GEN-END:variables
}
