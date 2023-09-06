/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbUserAgent;
import BackEnd.Results;
import BackEnd.models.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class UserController implements Initializable {

    @FXML
    private TableView<?> userTable;
    @FXML
    private ComboBox<String> role;

    private static User selectedUser = new User();
    ObservableList<String> list = FXCollections.observableArrayList("admin", "user", "simple");
    @FXML
    private TableColumn<?, ?> roleC;
    @FXML
    private Label name_err;
    @FXML
    private Label pass_err;
    @FXML
    private TableColumn<?, ?> usernameC;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXPasswordField newPass;
    @FXML
    private JFXPasswordField confirmPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        role.setValue("admin");
        role.setItems(list);
        try {
            refrechClient(userTable, usernameC, roleC, new User());
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void refrechClient(TableView table, TableColumn Column1, TableColumn Column2, User Ul)
            throws SQLException {
        ObservableList<User> ul = (ObservableList<User>) DbUserAgent.getUsers(Ul, "");
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("userName")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("role")
        );
        table.setItems(ul);
    }

    public boolean testinput() {
        boolean Bname = false, Bpass = false;
        if (name.getText().isEmpty()) {
            name_err.setText("Le nom d'utilisateur est vide");
            name_err.setVisible(true);
        } else {
            name_err.setVisible(false);
            Bname = true;
        }
        if (newPass.getText().isEmpty() || confirmPass.getText().isEmpty()) {
            pass_err.setText("le mot de pass est vide");
            pass_err.setVisible(true);
        } else {
            if (newPass.getText().equals(confirmPass.getText())) {
                pass_err.setVisible(false);
                Bpass = true;
            } else {
                pass_err.setText("Nauveau mot de pass != confirm mot de pass");
                pass_err.setVisible(true);
            }

        }

        return Bname && Bpass;

    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        if (testinput()) {
            User user = new User();
            user.setUserName(name.getText());
            user.setPassword(confirmPass.getText());
            user.setRole(role.getValue());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbUserAgent.addUser(user);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            } else {
                name.setText("");
                newPass.setText("");
                confirmPass.setText("");
            }
            try {
                refrechClient(userTable, usernameC, roleC, new User());
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void delete(ActionEvent event) {
        if (selectedUser.getId() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suprimer");
            alert.setHeaderText("suprimer confirmation");
            alert.setContentText("Vérifier avant de supprimer");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbUserAgent.deleteUser(selectedUser);
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    selectedUser = new User();
                    name.setText("");
                    newPass.setText("");
                    confirmPass.setText("");
                    try {
                        refrechClient(userTable, usernameC, roleC, new User());
                    } catch (SQLException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    @FXML
    private void update(ActionEvent event) {
        if (selectedUser.getId() != 0 && testinput()) {
            User user = new User();
            user.setId(selectedUser.getId());
            user.setUserName(name.getText());
            user.setPassword(confirmPass.getText());
            user.setRole(role.getValue());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Modifier confirmation");
            alert.setContentText("Vérifier avant de modifier");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbUserAgent.updateUser(user);
                if (r == Results.Rstls.DATA_NOT_UPDATED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    selectedUser = new User();
                    name.setText("");
                    newPass.setText("");
                    confirmPass.setText("");
                    try {
                        refrechClient(userTable, usernameC, roleC, new User());
                    } catch (SQLException ex) {
                        Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    @FXML
    private void selectUser(MouseEvent event) {
        selectedUser = (User) userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            name.setText(selectedUser.getUserName());
            role.setValue(selectedUser.getRole());

        }
    }

    @FXML
    private void select_provider(MouseEvent event) {
    }

}
