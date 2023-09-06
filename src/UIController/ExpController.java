/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbExpAgent;
import BackEnd.Results;
import BackEnd.models.Exp;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ExpController implements Initializable {

    @FXML
    private JFXComboBox<String> expcombo;
    @FXML
    private JFXTextField montent;
    @FXML
    private TableView<Exp> expTable;
    @FXML
    private TableColumn<?, ?> typeC, montentC, dateC;

    private static Exp expSelect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        expcombo.setItems((ObservableList<String>) DbExpAgent.getAlimony());
        try {
            refrechExp(expTable, typeC, montentC, dateC, new Exp(), "");
        } catch (SQLException ex) {
            Logger.getLogger(ExpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void refrechExp(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3, Exp ex, String s)
            throws SQLException {
        ObservableList<Exp> ul = (ObservableList<Exp>) DbExpAgent.getExps(ex, s);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("montentS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        table.setItems(ul);
    }

    @FXML
    private void selectexp(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
        Exp exp = new Exp();
        LocalDateTime lDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        String formattedS = lDate.format(formatter);
        exp.setDate(formattedS);
        exp.setType(expcombo.getSelectionModel().getSelectedItem());
        exp.setMontent(Double.parseDouble(montent.getText()));
        System.out.println("UIController.ExpController.add()" + exp.getDate() + " , " + exp.getMontent() + " , " + exp.getType());
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(null);
        Results.Rstls r = DbExpAgent.addExp(exp);
        if (r == Results.Rstls.DATA_NOT_INSERTED) {
            a.setContentText(r + "");
            a.showAndWait();
        } else {
            montent.setText("0.00");
            try {
                refrechExp(expTable, typeC, montentC, dateC, new Exp(), "");
            } catch (SQLException ex) {
                Logger.getLogger(ExpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        if (expSelect.getId() != 0) {
            Exp ex = new Exp();
            ex.setId(expSelect.getId());
            ex.setType(expcombo.getValue());
            ex.setDate(expSelect.getDate());
            ex.setMontent(Double.parseDouble(montent.getText()));
            Results.Rstls rs = DbExpAgent.updateExp(ex);
            if (rs == Results.Rstls.DATA_NOT_UPDATED) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Information Dialog");
                a.setHeaderText(rs + "");
                a.showAndWait();
            } else {
                expSelect = new Exp();
                refrechExp(expTable, typeC, montentC, dateC, new Exp(), "");
            }

        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (expSelect.getId() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("حذف");
            alert.setHeaderText("تأكيد الحذف");
            alert.setContentText("تأكد قبل الحذف");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls rs = DbExpAgent.deleteExp(expSelect);
                if (rs == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(rs + "");
                    a.showAndWait();
                } else {
                    expSelect = new Exp();
                    refrechExp(expTable, typeC, montentC, dateC, new Exp(), "");
                }
            }
        }
    }

    @FXML
    private void addExp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/alimony.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("إضافة نفقة");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        expcombo.setItems((ObservableList<String>) DbExpAgent.getAlimony());
    }

    @FXML
    private void select_exp(MouseEvent event) {
        Exp e = expTable.getSelectionModel().getSelectedItem();
        if (e != null) {
            expSelect = e;
            expcombo.setValue(expSelect.getType());
            montent.setText(expSelect.getMontentS());
        }
    }

}
