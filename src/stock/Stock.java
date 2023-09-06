/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

/**
 *
 * @author kadri
 */
import BackEnd.DbAgents.DbFactureSaleAgent;
import BackEnd.Results;
import static BackEnd.alerts.confirmFactureAlert;
import static BackEnd.alerts.confirmFactureAlert_ar;
import BackEnd.models.FactureSale;
import BackEnd.models.User;
import static UIController.SaleController.SaleFacture;
import static UIController.SaleController.SaleFacture2;
import static UIController.SaleController.SaleFacture3;
import static UIController.SaleController.ex;
import static UIController.SaleController.ex2;
import static UIController.SaleController.ex3;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Stock extends Application {

    public static Stage loginstage = new Stage();
    public static Stage clientstage = new Stage();
    public static Stage productstage = new Stage();
    public static Stage providerstage = new Stage();
    public static Stage userstage = new Stage();
    public static Stage expstage = new Stage();

    public static Connection con;
    public static int lang;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double offset_x;
    private double offset_y;

    public Stock() {
        try {
            String url = "jdbc:sqlite:DbStock\\stock.db";
            Class.forName("org.sqlite.JDBC");
            con = (Connection) DriverManager.getConnection(url);
            System.out.println("Connection successfly");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.getStackTrace();
            System.out.println("Connection not succesfly");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat de programme");
            alert.setHeaderText("Pour obtenir une copie complète, envoyez-nous ce code");
            alert.setContentText("Connection not succesfly");
            alert.showAndWait();
        }
    }

    public static String getSerialNumber() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("wmic", "baseboard",
                "get", "serialnumber");
        Process process = pb.start();
        process.waitFor();
        String serialNumber = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                process.getInputStream()))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.length() < 1 || line.startsWith("SerialNumber")) {
                    continue;
                }
                serialNumber = line.trim();
                break;
            }
        }
        return serialNumber;
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginstage = stage;
        LocalDate ld = LocalDate.parse("2023-10-12");
        /*if ("G316137760".equals(getSerialNumber()) || "MBV5M0P0012303C8D37200".equals(getSerialNumber())) {*/
 /*if (LocalDate.now().isBefore(ld)) {*/
        Parent root1;
        getLang();
        if (lang == 1) {
            root1 = FXMLLoader.load(getClass().getResource("/FrontEnd/login.fxml"));
        } else {
            root1 = FXMLLoader.load(getClass().getResource("/FrontEnd/login_ar.fxml"));
        }

        Scene scene = new Scene(root1);
        loginstage.setScene(scene);
        loginstage.setOnCloseRequest(event -> {
            event.consume();
            if (ex && ex2 && ex3) {
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
                if (productstage.isShowing()) {
                    productstage.close();
                }
                if (clientstage.isShowing()) {
                    clientstage.close();
                }
                if (providerstage.isShowing()) {
                    providerstage.close();
                }
                if (userstage.isShowing()) {
                    userstage.close();
                }
                loginstage.close();
            } else {
                if (lang == 1) {
                    confirmFactureAlert.showAndWait();
                } else {
                    confirmFactureAlert_ar.showAndWait();
                }
            }

        });
        loginstage.setTitle("Stock");
        loginstage.show();
        /*} else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Achat de programme");
            alert.setHeaderText("Pour obtenir une copie complète, envoyez-nous ce code");
            alert.setContentText(getSerialNumber());
            alert.showAndWait();
        }*/
    }

    public static void getLang() {
        String query = "SELECT * FROM lang";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lang = rs.getInt("fr");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        System.out.println("void lang = " + lang);
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
