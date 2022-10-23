package Interfaz;

import java.io.File;

import creacion.Conector;
import creacion.ConnInterface;
import creacion.QuerysInsert;
import creacion.DBCreation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojos.Doctor;
import pojos.Patient;

public class MenuPrincipalServidor extends Application{
	private Stage window;
	public static ConnInterface conector;
	public static Patient patient;
	public static Doctor doctor;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		QuerysInsert qi = new QuerysInsert();
	
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
		this.window.setResizable(false);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
	
}
