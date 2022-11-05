package Interfaz;

import java.sql.SQLException;

import creacion.QuerysSelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RecuperarContra {

    @FXML
    private Button buttonSend;

    @FXML
    private TextField user;

    @FXML
    void onActionSend(ActionEvent event) {
    	QuerysSelect qs = new QuerysSelect();
    	String password = "";
		try {
			password = qs.selectAdminPass(user.getText());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Your password is: " + password);
		alert.setHeaderText("Recovery");
		alert.setTitle("Information");
		alert.showAndWait();
    }

}