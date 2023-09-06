/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbUserAgent;
import BackEnd.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.input.MouseEvent;
import static stock.Stock.lang;
import static stock.Stock.loginstage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LoginController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    public static User loginUser;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        User user = new User(username.getText(), password.getText());

        if (user.getUserName().isEmpty()) {
            error.setText("Le nom d'utilisateur est vide");
            error.setVisible(true);
        } else if (user.getPassword().isEmpty()) {
            error.setText("Le mot de passe est vide");
            error.setVisible(true);
        } else {
            loginUser = DbUserAgent.isauthentificated(user);
            if (loginUser.getId() == 0) {
                error.setText("échec de la connexion");
                error.setVisible(true);
            } else {

                try {
                    Parent home;
                    if (lang == 1) {
                        home = FXMLLoader.load(getClass().getResource("/FrontEnd/Main.fxml"));
                    } else {
                         home = FXMLLoader.load(getClass().getResource("/FrontEnd/Main_ar.fxml"));
                    }
                    if (screenSize.getWidth() <= 1366) {
                        screenSize.width = 1340;
                        screenSize.height = 650;
                    } else {
                        screenSize.width = 1570;
                        screenSize.height = 820;
                    }
                    Scene home_scene = new Scene(home, (screenSize.getWidth()), screenSize.getHeight());
                    loginstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    loginstage.hide();
                    loginstage.setScene(home_scene);
                    loginstage.setTitle("gestion de stock");
                    //loginstage.setResizable(false);
                    loginstage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void close(MouseEvent event) {
        loginstage.close();
    }
}
