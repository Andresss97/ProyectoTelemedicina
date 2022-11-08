/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Interfaz.HomeServer;
/**
 *
 * @author andre
 */
public class QuerysInsert {
    private Conector conn = (Conector) HomeServer.conector;
    
    public void insertAdmin(String user, String password) throws SQLException {
        String query;
        query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
        PreparedStatement st;
        st = conn.getConnect().prepareStatement(query);

        st.setString(1, user);
        st.setString(2, password);
        st.setInt(3, 3);
        st.executeUpdate();
        st.close();
    }
    
    public void insertDoctor(String user, String password) throws SQLException {
        String query = "INSERT into doctor (username, password) values (?,?)";
        PreparedStatement st;
        
        st = this.conn.getConnect().prepareStatement(query);
        st.setString(1,user);
        st.setString(2,password);
        st.executeUpdate();
        st.close();
    }
    
    public void insertUserType(String user, String password, int type) throws SQLException {
        String query;
        query = "INSERT into mappinglogin (username, password,usertype) values (?,?,?)";
        PreparedStatement st;
        st = conn.getConnect().prepareStatement(query);

        st.setString(1, user);
        st.setString(2, password);
        st.setInt(3, type);
        st.executeUpdate();
        st.close();
    }
}