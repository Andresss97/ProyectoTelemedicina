package Interfaz;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import conexion.ClienteCon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojos.Patient;

public class MenuPrincipalCliente extends Application{
	
	private ClienteCon con;
	private Stage window;
    @FXML
    private BorderPane contenedor;
    
    @FXML
    private PasswordField contrasenha;

    @FXML
    private TextField usuario;
    private Socket socket;

    @FXML
    private Hyperlink registrarse;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		
		this.window = primaryStage;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.window.initStyle(StageStyle.DECORATED);
		this.window.setResizable(true);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
	
	
	@FXML
	public void accionIngresar(ActionEvent event) {
		String user = this.usuario.getText();
		String password = this.contrasenha.getText();
		
		Patient p = new Patient();
		p.setUsername(user);
		p.setPassword(password);
		
		try {
			this.socket = new Socket("localhost",9000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.con = new ClienteCon(socket);
		
	}
	
    @FXML
    void accionRegistrarse(ActionEvent event) throws IOException {

    }
    
	private void controlDeErrores() {

	}
}
