package Interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MenuPrincipalCliente extends Application{

	private Stage window;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		this.window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
		this.window.initStyle(StageStyle.DECORATED);
		this.window.setResizable(false);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
	
	

}
