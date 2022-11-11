/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Interfaz;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;
import pojos.DailyRegister;
import server.BITalino;
import server.Client;
import server.Frame;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
/**
 *
 * @author andre
 */
public class PatientsView extends javax.swing.JPanel {

    /**
     * Creates new form PatientsView
     */
    
    private final DefaultListModel modelo = new DefaultListModel();
    public PatientsView() {
        initComponents();
        this.usernameLabel.setText(HomeClients.p.getUsername());
        this.listRegister.setModel(modelo);
        this.inicializeList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listRegister = new javax.swing.JList();
        central = new javax.swing.JPanel();
        measureDataButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        usernameLabel.setText("jLabel1");

        listRegister.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listRegisterMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listRegister);

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(usernameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        add(rightPanel, java.awt.BorderLayout.LINE_START);

        measureDataButton.setText("New measure");
        measureDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                measureDataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout centralLayout = new javax.swing.GroupLayout(central);
        central.setLayout(centralLayout);
        centralLayout.setHorizontalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralLayout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(measureDataButton)
                .addGap(20, 20, 20))
        );
        centralLayout.setVerticalGroup(
            centralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centralLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(measureDataButton)
                .addContainerGap(329, Short.MAX_VALUE))
        );

        add(central, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void measureDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_measureDataButtonActionPerformed
        try {
            // TODO add your handling code here:
            ArrayList<ArrayList<Integer>> analogData = readData();
            DailyRegister register = new DailyRegister();
            String emg = analogData.get(0).toString();
            System.out.println(emg);
            String ecg = analogData.get(1).toString();
            System.out.println(ecg);
            register.setECG(ecg);
            register.setEMG(emg);
            Date date = Date.valueOf(LocalDate.now());
            
            register.setDay(date);
            register.setSymptoms("null");
            register.setIdPatient(HomeClients.p.getId());
            
            HomeClients.client.registerData(register);
            Iterator<DailyRegister> it = (Iterator<DailyRegister>) HomeClients.p.getDailyRegisters();
            
        } catch (Throwable ex) {
            Logger.getLogger(PatientsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_measureDataButtonActionPerformed

    private void listRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listRegisterMouseClicked
        // TODO add your handling code here:
        System.out.println("Entro en el grafico");
        int value = this.listRegister.getMaxSelectionIndex();
        System.out.println(value);
        DailyRegister found = null;
        
        for(int i = 0; i <HomeClients.p.getDailyRegisters().size(); i++) {
            if(i == value){
                found = HomeClients.p.getDailyRegisters().get(i);

                break;
            }
        }
     
        JFreeChart lineChart1 = ChartFactory.createLineChart("EMG data", "Time","Data", this.createDatasetEMG(found),PlotOrientation.VERTICAL, true, true,true );
        JFreeChart lineChart2 = ChartFactory.createLineChart("ECG data", "Time","Data", this.createDatasetECG(found),PlotOrientation.VERTICAL, true, true,true );

        ChartFrame frame1 = new ChartFrame("EMG", lineChart1);
        ChartFrame frame2 = new ChartFrame("ECG", lineChart2);
        
        frame1.pack();
        frame2.pack();
        
        frame1.setVisible(true);
        frame2.setVisible(true);
    }//GEN-LAST:event_listRegisterMouseClicked
    
    private DefaultCategoryDataset createDatasetEMG(DailyRegister d){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] separatedStrings = d.getEMG().replaceAll("\\[", "")
            .replaceAll("]", "").split(",");
        int[] data = new int[separatedStrings.length];
        
        for(int i = 0; i < data.length; i++) {
            dataset.addValue(data[i],"EMG", String.valueOf(i));
        }
        
        return dataset;
    }
    
    private DefaultCategoryDataset createDatasetECG(DailyRegister d) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] separatedStrings = d.getECG().replaceAll("\\[", "")
                .replaceAll("]", "").split(",");
        int[] data = new int[separatedStrings.length];

        for (int i = 0; i < data.length; i++) {
            dataset.addValue(data[i], "ECG", String.valueOf(i));
        }

        return dataset;
    }
    
    public ArrayList<ArrayList<Integer>> readData() throws Throwable {
        Frame[] frame = null;
        BITalino bitalino = null;
        ArrayList<Integer> analogECGData = new ArrayList<Integer>();
        ArrayList<Integer> analogEMGData = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> analogData = new ArrayList<ArrayList<Integer>>();

        try {
            bitalino = new server.BITalino();
            // find devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            String macAddress = "98:D3:A1:FD:5C:27";
            int SamplingRate = 10;
            bitalino.open(macAddress, SamplingRate);

            // start acquisition on analog channels A2 and A6
            //If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {0, 1};
            bitalino.start(channelsToAcquire);

            //read 10000 samples
            for (int j = 0; j < 5; j++) {

                //Read a block of 100 samples
                frame = bitalino.read(10);

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    analogEMGData.add(frame[i].analog[0]);
                    analogECGData.add(frame[i].analog[1]);
                }
            }
            //stop acquisition
            bitalino.stop();
        } catch (server.BITalinoException ex) {
            Logger.getLogger(server.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (server.BITalinoException ex) {
                Logger.getLogger(server.BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        analogData.add(analogEMGData);
        analogData.add(analogECGData);
        return analogData;
    }
    
    public String select() {
        System.out.println("Read data? Y/N");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String confirm = null;
        try {
            confirm = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return confirm;
    }
    
    public void inicializeList() {
        modelo.clear();
        for(DailyRegister d : HomeClients.p.getDailyRegisters()) {
            modelo.addElement(d);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel central;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listRegister;
    private javax.swing.JButton measureDataButton;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
