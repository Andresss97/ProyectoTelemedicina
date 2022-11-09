/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;
import Interfaz.HomeServer;
import java.sql.PreparedStatement;
import pojos.*;

/**
 *
 * @author andre
 */
public class QuerysUpdate {
    
    private Conector conn = (Conector) HomeServer.conector;
    
    public void modifyMH(MedicalHistory MH) 
	{
            try 
            {
                String sql = "UPDATE medicalHistory SET ID=?, Name=?, DOB=?, Diseases=?, Allergies=?, Surgeries=?,"
                                + "WeightKg=?, HeightCm=? WHERE ID=?";
                PreparedStatement s = conn.getConnect().prepareStatement(sql);
                s.setInt(1, MH.getID());
                s.setString(2, MH.getName());
                s.setDate(3, MH.getDOB());
                s.setString(4, MH.getDiseases());
                s.setString(5, MH.getAllergies());
                s.setString(6,MH.getSurgeries());
                s.setFloat(7, MH.getWeightKg());
                s.setInt(8, MH.getHeightCm());
                s.setInt(9, MH.getID());
                s.executeUpdate();
                s.close();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }
	}
}
