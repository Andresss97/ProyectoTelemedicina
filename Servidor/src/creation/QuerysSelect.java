/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package creation;

import Interfaz.HomeServer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class QuerysSelect {
    private Conector conn = (Conector) HomeServer.conector;
    
    public int selectUser(String user, String psw) throws SQLException {
        String[] data = new String[2];
        String query = "SELECT ID from mappinglogin where username = '" + user + "' and password = '"
                + psw + "'";
        PreparedStatement st = conn.getConnect().prepareStatement(query);
        ResultSet set = st.executeQuery();
        int id = set.getInt("ID");

        st.close();
        set.close();

        return id;
    }
}
