/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbFactureSaleAgent;
import BackEnd.Results;
import static BackEnd.alerts.aboutAlert;
import static BackEnd.alerts.aboutAlert_ar;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import static UIController.SaleController.ex;
import static UIController.SaleController.ex2;
import static UIController.SaleController.ex3;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static stock.Stock.clientstage;
import static stock.Stock.con;
import static stock.Stock.lang;
import static stock.Stock.loginstage;
import static stock.Stock.productstage;
import static stock.Stock.providerstage;
import static stock.Stock.userstage;
import static BackEnd.alerts.confirmFactureAlert;
import static BackEnd.alerts.confirmFactureAlert_ar;
import BackEnd.models.FactureSale;
import static UIController.SaleController.SaleFacture;
import static UIController.SaleController.SaleFacture2;
import static UIController.SaleController.SaleFacture3;
import static stock.Stock.expstage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MainController implements Initializable {

    @FXML
    private BorderPane main;
    @FXML
    private JFXButton stat;
    @FXML
    private JFXButton sale;
    @FXML
    private JFXButton purchase;

    private double offset_x1;
    private double offset_y1;
    @FXML
    private JFXButton Facture_Sale;
    @FXML
    private JFXButton Facture_Purchase;
    @FXML
    private MenuItem user, exp;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String loginbtn = "stat";
        switch (loginUser.getRole()) {
            case "user":
                sale.setStyle("-fx-background-color :  linear-gradient(to right,#0d1a27 20%,#203243 80%, #0077cc 100%);"
                        + "-fx-padding: 0");
                user.setVisible(false);
                loginbtn = "sale";
                break;
            case "simple":
                sale.setStyle("-fx-background-color :  linear-gradient(to right,#0d1a27 20%,#203243 80%, #0077cc 100%);"
                        + "-fx-padding: 0");
                loginbtn = "Sale";
                user.setVisible(false);
                stat.setVisible(false);
                Facture_Sale.setVisible(false);
                Facture_Purchase.setVisible(false);
                break;
            default:
                break;
        }
        productstage = new Stage();
        productstage.setResizable(false);
        clientstage = new Stage();
        clientstage.setResizable(false);
        providerstage = new Stage();
        providerstage.setResizable(false);
        userstage = new Stage();
        userstage.setResizable(false);

        stat.setStyle("-fx-background-color :  linear-gradient(to right, #0d1a27 10%,#203243 90%, #0077cc 100%);");
        stat.setAlignment(Pos.CENTER);

        Parent root;
        try {
            if (lang == 1) {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/" + loginbtn + ".fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/" + loginbtn + "_ar.fxml"));
            }

            main.setCenter(null);
            if (root != null) {
                main.setCenter(root);
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changePane(JFXButton btn1, JFXButton btn2, JFXButton btn3, JFXButton btn4, JFXButton btn5, String rsc) throws IOException {
        if (SaleFacture.getId() != 0) {
            DbFactureSaleAgent.deleteFactureSale(SaleFacture);
            SaleFacture = new FactureSale();
        }
        if (SaleFacture2.getId() != 0) {
            DbFactureSaleAgent.deleteFactureSale(SaleFacture2);
            SaleFacture2 = new FactureSale();
        }
        if (SaleFacture3.getId() != 0) {
            DbFactureSaleAgent.deleteFactureSale(SaleFacture3);
            SaleFacture3 = new FactureSale();
        }
        btn1.setStyle("-fx-background-color :  linear-gradient(to right,#0d1a27 10%,#203243 90%, #0077cc 100%);");
        btn1.setAlignment(Pos.CENTER);
        btn2.setStyle("-fx-background-color : transparent;");
        btn2.setAlignment(Pos.BASELINE_LEFT);
        btn3.setStyle("-fx-background-color : transparent;");
        btn3.setAlignment(Pos.BASELINE_LEFT);
        btn4.setStyle("-fx-background-color : transparent;");
        btn4.setAlignment(Pos.BASELINE_LEFT);
        btn5.setStyle("-fx-background-color : transparent;");
        btn5.setAlignment(Pos.BASELINE_LEFT);
        Parent root;
        if (lang == 1) {
            root = FXMLLoader.load(getClass().getResource("/FrontEnd/" + rsc + ".fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("/FrontEnd/" + rsc + "_ar.fxml"));
        }

        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void btn1(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            changePane(stat, sale, purchase, Facture_Sale, Facture_Purchase, "stat");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }

    }

    @FXML
    private void btn2(ActionEvent event) throws IOException {

        if (ex && ex2 && ex3) {
            changePane(sale, stat, purchase, Facture_Sale, Facture_Purchase, "Sale");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    @FXML
    private void btn3(ActionEvent event) throws IOException {

        if (ex && ex2 && ex3) {
            changePane(purchase, stat, sale, Facture_Sale, Facture_Purchase, "Purchase");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    private void btn4(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            changePane(purchase, stat, sale, Facture_Sale, Facture_Purchase, "Retour");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    @FXML
    private void Product(ActionEvent event) throws IOException {
        Parent root;
        if (lang == 1) {
            root = FXMLLoader.load(getClass().getResource("/FrontEnd/Goods.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("/FrontEnd/Goods_ar.fxml"));
        }
        Scene scene = new Scene(root);
        if (!productstage.isShowing()) {
            productstage.setScene(scene);
            productstage.setTitle("gestion des produits");
            productstage.showAndWait();
        } else {
            productstage.setAlwaysOnTop(true);
            productstage.setAlwaysOnTop(false);
        }

    }

    @FXML
    private void Client(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Parent root;
            if (lang == 1) {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/client.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/client_ar.fxml"));
            }
            Scene scene = new Scene(root);
            if (!clientstage.isShowing()) {
                clientstage.setScene(scene);
                clientstage.setTitle("gestion des client");
                clientstage.showAndWait();
            } else {
                clientstage.setAlwaysOnTop(true);
                clientstage.setAlwaysOnTop(false);
            }

        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }

    }

    @FXML
    private void Provider(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Parent root;
            if (lang == 1) {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/provider.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/provider_ar.fxml"));
            }
            Scene scene = new Scene(root);
            if (!providerstage.isShowing()) {
                providerstage.setScene(scene);
                providerstage.setTitle("gestion des fournisseur");
                providerstage.showAndWait();
            } else {
                providerstage.setAlwaysOnTop(true);
                providerstage.setAlwaysOnTop(false);
            }

        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    @FXML
    private void About(ActionEvent event) {
        if (lang == 1) {
            aboutAlert.showAndWait();
        } else {
            aboutAlert_ar.showAndWait();
        }

    }

    @FXML
    private void Facture_Purchase(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            changePane(Facture_Purchase, purchase, stat, sale, Facture_Sale, "Facture_Purchase");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    @FXML
    private void Facture_Sale(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            changePane(Facture_Sale, purchase, stat, sale, Facture_Purchase, "Facture_sale");
        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    private void close(MouseEvent event) {
        if (providerstage.isShowing()) {
            providerstage.close();
        }
        if (clientstage.isShowing()) {
            clientstage.close();
        }
        if (productstage.isShowing()) {
            productstage.close();
        }
        loginstage.close();
    }

    @FXML
    private void drag(MouseEvent event) {
        /*loginstage.setX(event.getScreenX() - offset_x1);
        loginstage.setY(event.getScreenY() - offset_y1);*/
    }

    @FXML
    private void press(MouseEvent event) {
        /*offset_x1 = event.getSceneX();
        offset_y1 = event.getSceneY();*/
    }

    @FXML
    private void User(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Parent root;
            if (lang == 1) {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/user.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/FrontEnd/user_ar.fxml"));
            }
            Scene scene = new Scene(root);
            if (!userstage.isShowing()) {
                userstage.setScene(scene);
                userstage.setTitle("gestion des Utilisateur");
                userstage.showAndWait();
            } else {
                userstage.setAlwaysOnTop(true);
                userstage.setAlwaysOnTop(false);
            }

        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

    private static Results.Rstls updatelang(int lang) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update  lang set fr=?");
            stm.setInt(1, lang);
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    @FXML
    private void ar(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("changer la langue");
            alert.setHeaderText("confirmation");
            alert.setContentText("Êtes-vous sûr de changer la langue");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                updatelang(0);
                lang = 0;
                Parent home = FXMLLoader.load(getClass().getResource("/FrontEnd/Main_ar.fxml"));
                if (screenSize.getWidth() <= 1366) {
                    screenSize.width = 1340;
                    screenSize.height = 650;
                } else {
                    screenSize.width = 1570;
                    screenSize.height = 820;
                }
                Scene home_scene = new Scene(home, (screenSize.getWidth()), screenSize.getHeight());
                //loginstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginstage.close();
                loginstage.setScene(home_scene);
                loginstage.setTitle("gestion de stock");
                //loginstage.setResizable(false);
                loginstage.show();
            }
        } else {
            confirmFactureAlert_ar.showAndWait();
        }

    }

    @FXML
    private void fr(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("تغيير اللغة");
            alert.setHeaderText("تأكيد");
            alert.setContentText("هل أنت متأكد من تغيير اللغة");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                updatelang(1);
                lang = 1;
                Parent home = FXMLLoader.load(getClass().getResource("/FrontEnd/Main.fxml"));
                if (screenSize.getWidth() <= 1366) {
                    screenSize.width = 1340;
                    screenSize.height = 650;
                } else {
                    screenSize.width = 1570;
                    screenSize.height = 820;
                }
                Scene home_scene = new Scene(home, (screenSize.getWidth()), screenSize.getHeight());
                //loginstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                loginstage.close();
                loginstage.setScene(home_scene);
                loginstage.setTitle("gestion de stock");
                //loginstage.setResizable(false);
                loginstage.show();
            }
        } else {
            confirmFactureAlert.showAndWait();
        }
    }

    @FXML
    private void Exp(ActionEvent event) throws IOException {
        if (ex && ex2 && ex3) {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/FrontEnd/exp.fxml"));
            Scene scene = new Scene(root);
            if (!expstage.isShowing()) {
                expstage.setScene(scene);
                expstage.setTitle("تسيير النفقات");
                expstage.showAndWait();
            } else {
                userstage.setAlwaysOnTop(true);
                userstage.setAlwaysOnTop(false);
            }

        } else {
            if (lang == 1) {
                confirmFactureAlert.showAndWait();
            } else {
                confirmFactureAlert_ar.showAndWait();
            }
        }
    }

}
