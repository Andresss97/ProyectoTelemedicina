/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class Conector implements ConnInterface {
    private Connection connect;
    private String url;
    
    public Conector() {
    	this.connect = null;
    	this.url = null;
    }
    
    public Connection getConnect() {
        return connect;
    }
    
    private void setConnect(Connection connect) {
        this.connect = connect;
    }
    
    public String getUrl() {
    	return url;
    }
    
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String bUrl = "jdbc:sqlite:" + ".//Database//DBproject.db";
            connect = DriverManager.getConnection(bUrl);
            this.url = ".//Database//DBproject.db";
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void killConnection() {
        try {
            connect.close();
        }
        catch(SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
