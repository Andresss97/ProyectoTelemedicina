package Interfaz;

import java.io.File;
import java.sql.SQLException;

import creacion.Conector;
import creacion.ConnInterface;
import creacion.QuerysInsert;
import creacion.QuerysSelect;
import creacion.DBCreation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojos.Administrator;
import pojos.Doctor;
import pojos.Patient;

public class MenuPrincipalServidor extends Application{
	private Stage window;
	public static ConnInterface conector;
	public static Patient patient;
	public static Doctor doctor;
	
	private Administrator admin;
	
    @FXML
    private BorderPane contenedor;
    
    @FXML
    private PasswordField contrasenha;

    @FXML
    private TextField usuario;
    

    @FXML
    private Hyperlink registrarse;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		QuerysInsert qi = new QuerysInsert();
		QuerysSelect qs = new QuerysSelect();
		
		this.conector = new Conector();
		
		File url = new File(".//Database//DBproject.db");
		
		if(!url.exists()) {
			this.conector.connect();
			DBCreation.createDB(this.conector);
		}
		else {
			this.conector.connect();
		}
		
		this.window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipalServidor.fxml"));
		this.window.initStyle(StageStyle.DECORATED);
		this.window.setResizable(true);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
	
	@FXML
	public void accionIngresar(ActionEvent event) {
		String username;
		String password;
		
		username = this.usuario.getSelectedText();
		password = this.contrasenha.getSelectedText();
		
		Administrator admin = new Administrator();
		admin.setUsername(username);
		admin.setPassword(password);
		
		QuerysSelect qs = new QuerysSelect();
		
		try {
			boolean comprobar = qs.selectAdminLogIn(admin);
			
			if(comprobar == true) {
				
			}
			else {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    @FXML
    void accionRegistrarse(ActionEvent event) {
    	QuerysInsert qi = new QuerysInsert();
    	QuerysSelect qs = new QuerysSelect();
    	
    	this.admin = new Administrator();
    	try {
			qi.insertAdmin(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
