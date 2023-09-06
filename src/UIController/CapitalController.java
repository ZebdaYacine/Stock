/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import static BackEnd.DbAgents.DbClientAgent.getTotaldebt;
import static BackEnd.DbAgents.DbFactureAgent.getPrsQrest;
import static BackEnd.DbAgents.DbProviderAgent.getdebtProviders;
import static BackEnd.Functions.testdouble;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class CapitalController implements Initializable {

    @FXML
    private Label Purchases;
    @FXML
    private Label DettesProvider;
    @FXML
    private Label DetteClient;
    @FXML
    private Label Total;
    @FXML
    private JFXTextField ValueAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DetteClient.setText(new BigDecimal(getTotaldebt()).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Purchases.setText(new BigDecimal(getPrsQrest()).setScale(2, RoundingMode.HALF_UP).toPlainString());
        DettesProvider.setText(new BigDecimal(getdebtProviders()).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Total.setText(new BigDecimal(getTotaldebt() + getPrsQrest() - getdebtProviders()).setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    

    private void calculateT() {
        if (testdouble(ValueAdd)) {
            double total = getTotaldebt() + getPrsQrest() - getdebtProviders() + Double.parseDouble(ValueAdd.getText());
            Total.setText(new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).toPlainString());
        }
    }

    @FXML
    private void calculate(MouseEvent event) {
        calculateT();
    }

    @FXML
    private void calculateTotal(KeyEvent event) {
        calculateT();
    }

}
