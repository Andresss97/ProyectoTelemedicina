package Interfaz;

import creacion.Conector;
import creacion.ConnInterface;
import javafx.application.Application;
import javafx.stage.Stage;
import pojos.Doctor;
import pojos.Patient;

public class MenuPrincipal extends Application{
	public static ConnInterface conector;
	public static ConnInterface jpaConector;
	public static Patient patient;
	public static Doctor doctor;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
