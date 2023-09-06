/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbProviderAgent;
import BackEnd.Results;
import BackEnd.models.Provider;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXButton;
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
public class providerController implements Initializable {

    @FXML
    private TableView<?> providerTable;
    @FXML
    private TextField phone;
    @FXML
    private TextField adr;
    @FXML
    private TableColumn<?, ?> nameC;
    @FXML
    private TableColumn<?, ?> phoneC;
    @FXML
    private TableColumn<?, ?> adrC;
    @FXML
    private TextField name;
    @FXML
    private Label name_err;
    @FXML
    private Label phone_err;
    @FXML
    private Label adr_err;
    @FXML
    private TableColumn<?, ?> debtC;
    @FXML
    private TextField debt;
    @FXML
    private Label debt_err;

    private Provider P = new Provider();
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
            delete.setVisible(false);
        }
        try {
            refrechProvider(providerTable, nameC, phoneC, adrC, debtC, new Provider());
        } catch (SQLException ex) {
            Logger.getLogger(providerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void refrechProvider(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, Provider Pr)
            throws SQLException {
        ObservableList<Provider> pr = (ObservableList<Provider>) DbProviderAgent.getProvider(Pr, "");
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
                new PropertyValueFactory<>("debtS")
        );
        table.setItems(pr);
    }

    public boolean testdouble(TextField t) {
        boolean var = false;
        if (t.getText().matches("\\d+\\.\\d+") || t.getText().matches("\\d+") || t.getText().matches("\\d+\\.")) {
            var = true;
        }
        return var;
    }

    public boolean testint(TextField t) {
        boolean var = false;
        if (t.getText().matches("\\d+")) {
            var = true;
        }
        return var;
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
        if (adr.getText().isEmpty()) {
            adr_err.setText("L'adresse est vide");
            adr_err.setVisible(true);
        } else {
            adr_err.setVisible(false);
            Badr = true;
        }
        if (debt.getText().isEmpty()) {
            debt_err.setText("la Dette est vide");
            debt_err.setVisible(true);
        } else {
            if (testdouble(debt)) {
                debt_err.setVisible(false);
                Bdebt = true;
            } else {
                debt_err.setText("Entrer des nombres");
                debt_err.setVisible(true);
            }
        }

        return Bname && Bphone && Badr && Bdebt;

    }

    @FXML
    private void add(ActionEvent event) {
        if (testinput()) {
            Provider provider = new Provider();
            provider.setName(name.getText());
            provider.setPhone(phone.getText());
            provider.setAddress(adr.getText());
            provider.setDebt(Double.parseDouble(debt.getText()));
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbProviderAgent.addProvider(provider);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            }
            try {
                refrechProvider(providerTable, nameC, phoneC, adrC, debtC, new Provider());
            } catch (SQLException ex) {
                Logger.getLogger(providerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (P.getId() != 0 && testinput()) {
            Provider provider = new Provider();
            provider.setId(P.getId());
            provider.setName(name.getText());
            provider.setPhone(phone.getText());
            provider.setAddress(adr.getText());
            provider.setDebt(Double.parseDouble(debt.getText()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Modifier confirmation");
            alert.setContentText("Vérifier avant de modifier");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbProviderAgent.updateProvider(provider);
                if (r == Results.Rstls.DATA_NOT_UPDATED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    P = new Provider();
                    name.setText("");
                    adr.setText("");
                    phone.setText("");
                    debt.setText("");
                    try {
                        refrechProvider(providerTable, nameC, phoneC, adrC, debtC, new Provider());
                    } catch (SQLException ex) {
                        Logger.getLogger(providerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        if (P.getId() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suprimer");
            alert.setHeaderText("suprimer confirmation");
            alert.setContentText("Vérifier avant de supprimer");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbProviderAgent.deleteProvider(new Provider(P.getId()));
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    P = new Provider();
                    name.setText("");
                    adr.setText("");
                    phone.setText("");
                    debt.setText("");
                    try {
                        refrechProvider(providerTable, nameC, phoneC, adrC, debtC, new Provider());
                    } catch (SQLException ex) {
                        Logger.getLogger(providerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @FXML
    private void select_provider(MouseEvent event) {
        P = (Provider) providerTable.getSelectionModel().getSelectedItem();
        if (P != null) {
            name.setText(P.getName());
            adr.setText(P.getAddress());
            phone.setText(P.getPhone());
            debt.setText(P.getDebtS());
        }
    }

}
