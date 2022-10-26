package Interfaz;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
		QuerysSelect qs = new QuerysSelect();
		
		try {
			String[] data = qs.selectUser(this.usuario.getText(), this.contrasenha.getText());
			if(data[0].equals(this.usuario.getText()) && data[1].equals(this.contrasenha.getText())) {
				BorderPane root = FXMLLoader.load(getClass().getResource("VisionAdministrador.fxml"));
				contenedor.getChildren().clear();
				
				root.prefHeightProperty().bind(contenedor.heightProperty());
				root.prefWidthProperty().bind(contenedor.widthProperty());
				
				contenedor.setCenter(root);
			}
			else {
		    	Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Please introduce your username.");
				alert.setHeaderText("Recovery");
				alert.setTitle("Information");
				alert.showAndWait();
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    @FXML
    void accionRegistrarse(ActionEvent event) {
    	QuerysSelect qs = new QuerysSelect();
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setContentText("Please introduce your username.");
		alert.setHeaderText("Recovery");
		alert.setTitle("Information");
		alert.showAndWait();
		
    	return;
    }
	
}
