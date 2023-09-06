/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbClientAgent;
import static BackEnd.DbAgents.DbSaleAgent.getbenefitClient;
import static BackEnd.Functions.*;
import BackEnd.Results;
import BackEnd.models.Client;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class ClientController implements Initializable {

    @FXML
    private TableView<?> clientTable;
    @FXML
    private TextField phone;
    @FXML
    private TextField adr;
    @FXML
    private TextField name;
    @FXML
    private Label name_err;
    @FXML
    private TableColumn<?, ?> nameC;
    @FXML
    private TableColumn<?, ?> phoneC;
    @FXML
    private TableColumn<?, ?> adrC;
    @FXML
    private TableColumn<?, ?> deptC;
    @FXML
    private Label phone_err;
    @FXML
    private Label adr_err;
    @FXML
    private JFXButton delete;

    public static Client FactureSaleClient = new Client();
    private static Client C = new Client();
    @FXML
    private Label debt_err;
    @FXML
    private TextField debt;
    @FXML
    private TableColumn<?, ?> deptmaxC;
    @FXML
    private JFXTextField maxDebt;
    @FXML
    private JFXButton update;
    @FXML
    private Label benefitClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
        }
        try {
            refrechClient(clientTable, nameC, phoneC, adrC, deptC, deptmaxC, new Client());
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void refrechClient(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Client Cl)
            throws SQLException {
        ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(Cl, "");
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("address")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("totaleDebtS")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("maxDebtS")
        );
        table.setItems(cl);
    }

    public boolean testinput() {
        boolean Bname = false, Bphone = false, Bdebt = false, Badr = false;
        if (name.getText().isEmpty()) {
            name_err.setText("le nom est vide");
            name_err.setVisible(true);
        } else {
            name_err.setVisible(false);
            Bname = true;
        }
        if (phone.getText().isEmpty()) {
            phone_err.setText("le numiro de Telephone est vide");
            phone_err.setVisible(true);
        } else {
            if (testint(phone)) {
                phone_err.setVisible(false);
                Bphone = true;
            } else {
                phone_err.setText("Entrer des nombres");
                phone_err.setVisible(true);
            }
        }
        if (debt.getText().isEmpty() || maxDebt.getText().isEmpty()) {
            debt_err.setText("la Dette ou max Dette est vide");
            debt_err.setVisible(true);
        } else {
            if (testdouble(debt) && testdouble(maxDebt)) {
                debt_err.setVisible(false);
                Bdebt = true;
            } else {
                debt_err.setText("Entrer des nombres");
                debt_err.setVisible(true);
            }
        }
        if (adr.getText().isEmpty()) {
            adr_err.setText("L'adresse est vide");
            adr_err.setVisible(true);
        } else {
            adr_err.setVisible(false);
            Badr = true;
        }

        return Bname && Bphone && Badr && Bdebt;

    }

    @FXML
    private void add(ActionEvent event) {

        if (testinput()) {
            Client client = new Client();
            client.setName(name.getText());
            client.setPhone(phone.getText());
            client.setTotaleDebt(Double.parseDouble(debt.getText()));
            client.setMaxDebt(Double.parseDouble(maxDebt.getText()));
            client.setAddress(adr.getText());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbClientAgent.addClient(client);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            } else {
                try {
                    refrechClient(clientTable, nameC, phoneC, adrC, deptC, deptmaxC, new Client());
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        if (C.getId() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suprimer");
            alert.setHeaderText("suprimer confirmation");
            alert.setContentText("Vérifier avant de supprimer");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbClientAgent.deleteClient(new Client(C.getId()));
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    C = new Client();
                    name.setText("");
                    adr.setText("");
                    phone.setText("");
                    debt.setText("");
                    try {
                        refrechClient(clientTable, nameC, phoneC, adrC, deptC, deptmaxC, new Client());
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (C.getId() != 0 && testinput()) {
            Client client = new Client();
            client.setId(C.getId());
            client.setName(name.getText());
            client.setPhone(phone.getText());
            client.setTotaleDebt(Double.parseDouble(debt.getText()));
            client.setMaxDebt(Double.parseDouble(maxDebt.getText()));
            client.setAddress(adr.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Modifier confirmation");
            alert.setContentText("Vérifier avant de modifier");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbClientAgent.updateClient(client);
                if (r == Results.Rstls.DATA_NOT_UPDATED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    C = new Client();
                    name.setText("");
                    adr.setText("");
                    phone.setText("");
                    debt.setText("");
                    try {
                        refrechClient(clientTable, nameC, phoneC, adrC, deptC, deptmaxC, new Client());
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    @FXML
    private void select_client(MouseEvent event) throws IOException {
        C = (Client) clientTable.getSelectionModel().getSelectedItem();
        if (C != null) {
            if (C.getId() != 1) {
                update.setVisible(true);
            } else {
                update.setVisible(false);
            }
            benefitClient.setText(new BigDecimal(getbenefitClient(C.getId())).setScale(2, RoundingMode.HALF_UP).toPlainString());
            name.setText(C.getName());
            adr.setText(C.getAddress());
            phone.setText(C.getPhone());
            debt.setText(C.getTotaleDebtS());
            maxDebt.setText(C.getMaxDebtS());
        }

    }

}
