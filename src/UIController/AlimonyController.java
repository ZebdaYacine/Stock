/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbExpAgent;
import BackEnd.Results;
import BackEnd.models.Exp;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AlimonyController implements Initializable {

    @FXML
    private JFXTextField type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void add(ActionEvent event) {
        if (!type.getText().isEmpty()) {
            Exp almony = new Exp();
            almony.setType(type.getText());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbExpAgent.addAlmony(almony);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            } else {
                type.setText("");
            }
        }
    }

}
