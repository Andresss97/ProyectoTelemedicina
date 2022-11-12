/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Teresa Romero
 */
public class MeasuredData implements Serializable{

    private static final long serialVersionUID = 10L;
    public ArrayList<Integer> EMGData = new ArrayList<Integer>();
    public ArrayList<Integer> ECGData = new ArrayList<Integer>();
    public final String userName;
    public final Date dateTime;

    public MeasuredData(String userName, Date dateTime, ArrayList<Integer> EMGData, ArrayList<Integer> ECGData) {
        this.userName = userName;
        this.dateTime = dateTime;
        this.EMGData = EMGData;
        this.ECGData = ECGData;
    }
    //TODO, MOVER ESTO A POJOS?

    @Override
    public String toString() {
        return ("User Name: " + userName + "\n Date & Time: " + dateTime
                + "\nEMG Data : " + EMGData + "\nECG Data: " + ECGData);
    }
}
