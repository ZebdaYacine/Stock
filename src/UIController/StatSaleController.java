/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import static BackEnd.DbAgents.DbFactureAgent.getPrsenterDate;
import static BackEnd.DbAgents.DbFactureSaleAgent.getSalesenterDate;
import static BackEnd.DbAgents.DbSaleAgent.getBenefitenterDate;
import com.jfoenix.controls.JFXDatePicker;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class StatSaleController implements Initializable {

    @FXML
    private JFXDatePicker date1;
    @FXML
    private JFXDatePicker date2;
    @FXML
    private Label Purchases;
    @FXML
    private Label Benefits;
    @FXML
    private Label Sales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date1.setValue(LocalDate.now());
        date2.setValue(LocalDate.now().plusDays(1));
        Sales.setText(new BigDecimal(getSalesenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        Purchases.setText(new BigDecimal(getPrsenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        Benefits.setText(new BigDecimal(getBenefitenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    @FXML
    private void dateStat(ActionEvent event) {
        Sales.setText(new BigDecimal(getSalesenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        Purchases.setText(new BigDecimal(getPrsenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        Benefits.setText(new BigDecimal(getBenefitenterDate(date1.getValue().toString(), date2.getValue().toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

}
