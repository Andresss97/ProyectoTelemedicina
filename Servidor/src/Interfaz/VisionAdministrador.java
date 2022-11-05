package Interfaz;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import conexion.ClientHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pojos.Patient;

public class VisionAdministrador implements Initializable {

    @FXML
    private MenuItem changeCredentialsButton;

    @FXML
    private Button closeSocketButton;

    @FXML
    private Label doctors;

    @FXML
    private Menu homeButton;

    @FXML
    private Label patients;

    @FXML
    private Button refreshButton;
    
    @FXML
    private Menu botonLogOff;
    
    @FXML
    private BorderPane container;
    
    @FXML
    private BorderPane cContainer;
    
    @FXML
    void onActionCloseSocket(ActionEvent event) {

    }

    @FXML
    void onActionHome(ActionEvent event) {
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("VisionAdministrador.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	window.setScene(new Scene(root));
    	window.show();
    }

    @FXML
    void onActionRefresh(ActionEvent event) {
    	
    }

    @FXML
    void onActionhangeCredentials(ActionEvent event) {
    	BorderPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("RecuperarCredenciales.fxml"));
			
			this.cContainer.getChildren().clear();
			root.prefHeightProperty().bind(container.heightProperty());
			root.prefWidthProperty().bind(container.widthProperty());
			this.cContainer.setCenter(root);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void onActionLogOff(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	int patients = 0;
    	int doctors = 0;
    	
    	if(ClientHandler.clientes != null) {
			for(int i = 0; i < ClientHandler.clientes.size(); i++) {
				if(ClientHandler.clientes.elements().nextElement().getPatient() != null) {
    				patients++;
    			}
    			else if(ClientHandler.clientes.elements().nextElement().getDoctor() != null) {
    				doctors++;
    			}
			}
		}
    	
    	this.patients.setText("The number of patiens connected to the server are: " + patients);
    	this.doctors.setText("The number of doctors connected to the server are: " + doctors);
	}
}

