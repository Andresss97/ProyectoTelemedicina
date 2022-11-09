/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import Interfaz.HomeServer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class QuerysDelete {
    private Conector conn = (Conector) HomeServer.conector;
    
    public void fireDoctor (int id)
    {
        String sql = "DELETE FROM DOCTOR WHERE ID = ?";
        try {
            PreparedStatement ps = conn.getConnect().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuerysDelete.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void deletePatient (int id)
    {
        String sql = "DELETE FROM PATIENT WHERE ID = ?";
        try {
            PreparedStatement ps = conn.getConnect().prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuerysDelete.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
