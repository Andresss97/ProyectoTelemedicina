package Interfaz;

import creacion.QuerysInsert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class RecuperarCredenciales {

    @FXML
    private PasswordField password;

    @FXML
    private Button saveButton;

    @FXML
    void onActionSave(ActionEvent event) {
    	QuerysInsert qs = new QuerysInsert();
    	
    }

}