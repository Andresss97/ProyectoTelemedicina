package Interfaz;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import conexion.ServidorCon;
import creacion.Conector;
import creacion.ConnInterface;
import creacion.QuerysInsert;
import creacion.QuerysSelect;
import creacion.DBCreation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import pojos.Administrator;
import pojos.Doctor;
import pojos.Patient;

public class MenuPrincipalServidor extends Application implements Initializable {
	private Stage window;
	public static ConnInterface conector;
	public static Patient patient;
	public static Doctor doctor;
	
	public static Administrator admin;
	
    @FXML
    private BorderPane contenedor;
    
    @FXML
    private PasswordField contrasenha;

    @FXML
    private TextField usuario;
    
    public static ServidorCon con;
    @FXML
    private Hyperlink registrarse;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
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
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("MenuPrincipalServidor.fxml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.window.initStyle(StageStyle.DECORATED);
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent e) {
				MenuPrincipalServidor.conector.killConnection();
				System.out.println("Se cerro la conexión");
				Platform.exit();
				System.exit(0);
			}
		});
		
		this.window.setResizable(true);
		this.window.setScene(new Scene(root));
		this.window.show();
	}
	
	private void controlDeErrores() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Please introduce a valir username.");
		alert.setHeaderText("Recovery");
		alert.setTitle("Information");
		alert.showAndWait();
	}
	
	@FXML
	public void accionIngresar(ActionEvent event) {
		QuerysSelect qs = new QuerysSelect();
		
		try {
			String usuario = this.usuario.getText();
			String contra = this.contrasenha.getText();
			
			System.out.println(usuario);
			System.out.println(contra);
			
			String[] data = qs.selectUser(this.usuario.getText(), this.contrasenha.getText());
			
			if((data == null) || (data[0] == null) && (data[1] == null)) {
				this.controlDeErrores();
				return;
			}
			
			if(data[0].equals(this.usuario.getText()) && data[1].equals(this.contrasenha.getText())) {
				BorderPane root = FXMLLoader.load(getClass().getResource("VisionAdministrador.fxml"));
				contenedor.getChildren().clear();
				
				root.prefHeightProperty().bind(contenedor.heightProperty());
				root.prefWidthProperty().bind(contenedor.widthProperty());
				
				contenedor.setCenter(root);
			}
			else {
				this.controlDeErrores();
				return;
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

    @FXML
    void accionRegistrarse(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("RecuperarContraServidor.fxml"));
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Stage modal = new Stage();
    	modal.setTitle("Telemedicine - Recover Password");
    	modal.setScene(new Scene(root));
    	modal.initOwner(window);
    	modal.setResizable(false);
    	modal.initModality(Modality.APPLICATION_MODAL);
    	modal.show();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ServerSocket socket;
				try {
					socket = new ServerSocket(9000);
					con = new ServidorCon(socket);
					con.startServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}).start();
	}
	
}
