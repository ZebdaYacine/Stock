/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbClientAgent;
import static BackEnd.DbAgents.DbClientAgent.getdebt;
import BackEnd.DbAgents.DbFactureSaleAgent;
import static BackEnd.DbAgents.DbFactureSaleAgent.deleteFactureSale;
import BackEnd.DbAgents.DbSaleAgent;
import BackEnd.models.Client;
import BackEnd.models.FactureSale;
import BackEnd.models.Sale;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static BackEnd.DbAgents.DbSaleAgent.getbenefitFacture;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static stock.Stock.loginstage;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class Facture_saleController implements Initializable {

    @FXML
    private TextField code_facture;
    @FXML
    private TableView<?> FactureTable;
    @FXML
    private TableColumn<?, ?> codeC;
    @FXML
    private TableColumn<?, ?> priceT_C;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TableColumn<?, ?> clientC;

    private static boolean select = true;
    public static boolean factureSaleB = false;
    public static FactureSale selectFactureSale = new FactureSale();
    public static FactureSale selectedFSale = new FactureSale();
    public static Client FactureSaleClient = new Client();
    @FXML
    private JFXComboBox<Client> client;
    @FXML
    private TableColumn<?, ?> paymentC;
    @FXML
    private TableView<?> SaleTable;
    @FXML
    private TableColumn<?, ?> productC;
    @FXML
    private TableColumn<?, ?> price_purchaseC;
    @FXML
    private TableColumn<?, ?> quantityC;
    @FXML
    private TableColumn<?, ?> price_saleC;
    @FXML
    private TableColumn<?, ?> price_totelC;
    @FXML
    private TableColumn<?, ?> benefitC;
    @FXML
    private TableColumn<?, ?> quantityRC;
    @FXML
    private JFXButton delete;
    @FXML
    private TableColumn<?, ?> userC;
    @FXML
    private TableColumn<?, ?> RemiseC;
    @FXML
    private Label benefitFacture;
    @FXML
    private JFXDatePicker date;
    @FXML
    private TableView<?> SommeTable;
    @FXML
    private TableColumn<?, ?> SumMontentC;
    @FXML
    private TableColumn<?, ?> SumRemiseC;
    @FXML
    private TableColumn<?, ?> SumMontent_RemiseC;
    @FXML
    private TableColumn<?, ?> SumpaymentC;
    @FXML
    private Label DebtClientLabel;
    @FXML
    private TableColumn<?, ?> NbrFactureC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Client> listclient = (ObservableList<Client>) DbClientAgent.getClient(new Client(), "");
        Client allc=new Client();
        allc.setAddress("");
        allc.setName("جميع الزبائن");
        client.getItems().add(allc);
        client.getItems().addAll(listclient);
        client.getSelectionModel().selectFirst();
        try {
            refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, new FactureSale(), "");
        } catch (SQLException ex) {
            Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refrechFacture(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5,
            TableColumn Column6, TableColumn Column7, FactureSale Fc, String s)
            throws SQLException {
        ObservableList<FactureSale> pr = (ObservableList<FactureSale>) DbFactureSaleAgent.getFactures(Fc, s);
        Sale fs = new Sale();
        if (!pr.isEmpty()) {
            fs.setIdFacture(pr.get(0).getId());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(pr.get(0).getDate(), formatter);
            date.setValue(dateTime.toLocalDate());
            benefitFacture.setText(new BigDecimal(getbenefitFacture(pr.get(0).getId())).setScale(2, RoundingMode.HALF_UP).toPlainString());
            DebtClientLabel.setText(new BigDecimal(getdebt(pr.get(0).getIdClient())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        } else {
            benefitFacture.setText("0.00");
            DebtClientLabel.setText("0.00");
        }
        refrechSum(SommeTable, SumMontentC, SumRemiseC, SumMontent_RemiseC, SumpaymentC, NbrFactureC, Fc, s);
        try {
            refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, fs, "facture");
        } catch (SQLException ex) {
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("client")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("TotalAmountS")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("paymentS")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("user")
        );
        Column7.setCellValueFactory(
                new PropertyValueFactory<>("remiseS")
        );
        table.setItems(pr);
    }

    public void refrechSale(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, TableColumn Column6, TableColumn Column7,
            Sale pt, String q)
            throws SQLException {
        ObservableList<Sale> sl = (ObservableList<Sale>) DbSaleAgent.getSales(pt, q);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("Product")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("PricePurchaseS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("price_saleS")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("PriceTotalS")
        );
        Column6.setCellValueFactory(
                new PropertyValueFactory<>("benefitSaleS")
        );
        Column7.setCellValueFactory(
                new PropertyValueFactory<>("quantityRetour")
        );
        table.setItems(sl);
    }

    public void refrechSum(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, FactureSale Fc, String s)
            throws SQLException {
        ObservableList<FactureSale> pr = (ObservableList<FactureSale>) DbFactureSaleAgent.getSum(Fc, s);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("TotalAmountS")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("remiseS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("paymentS")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );
        table.setItems(pr);
    }

    public void clearinputs() {
        code_facture.setText("");
        client.setValue(new Client());
    }

    @FXML
    private void delete(ActionEvent event) {
        if (selectFactureSale.getId() != 0) {
            deleteFactureSale(selectFactureSale);
            selectFactureSale = new FactureSale();
            clearinputs();
            String s = "";
            try {
                refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, selectFactureSale, s);
            } catch (SQLException ex) {
                Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        delete.setDisable(true);
    }

    @FXML
    private void selectFacture(MouseEvent event) throws IOException {
        select = false;
        selectFactureSale = (FactureSale) FactureTable.getSelectionModel().getSelectedItem();
        if (selectFactureSale != null) {
            if (event.getClickCount() == 2) {
                if (selectFactureSale.getIdUser() == loginUser.getId()) {
                    selectedFSale = selectFactureSale;
                    Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/Sale_ar.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.getScene().getRoot().setStyle("-fx-border-color: #ffffff;");
                    stage.setTitle("afficher facture");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();
                    delete.setDisable(true);
                    clearinputs();
                    selectedFSale = new FactureSale();
                    String s = "";
                    select = true;
                    try {
                        refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, selectFactureSale, s);
                    } catch (SQLException ex) {
                        Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                if (selectFactureSale.getPayment() == 0 && selectFactureSale.getTotalAmount() == 0) {
                    delete.setDisable(false);
                } else {
                    delete.setDisable(true);
                }
                code_facture.setText(selectFactureSale.getCode());
                //client.setValue(selectFactureSale.getClient());
                Sale fs = new Sale();
                fs.setIdFacture(selectFactureSale.getId());
                benefitFacture.setText(new BigDecimal(getbenefitFacture(selectFactureSale.getId())).setScale(2, RoundingMode.HALF_UP).toPlainString());
                DebtClientLabel.setText(new BigDecimal(getdebt(selectFactureSale.getIdClient())).setScale(2, RoundingMode.HALF_UP).toPlainString());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(selectFactureSale.getDate(), formatter);
                date.setValue(dateTime.toLocalDate());
                select = true;
                try {
                    refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, fs, "facture");
                } catch (SQLException ex) {
                    Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @FXML
    private void code(KeyEvent event) {
        client.setValue(new Client());
        FactureSale Fs = new FactureSale();
        String s = "";
        if (!code_facture.getText().isEmpty()) {
            Fs.setCode(code_facture.getText());
            s = "code";
        }
        try {
            refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, Fs, s);
        } catch (SQLException ex) {
            Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void date(ActionEvent event) {
        if (select) {
            selectFactureSale = new FactureSale();
            selectFactureSale.setDate(date.getValue().toString());
            String s = "date";
            try {
                refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, selectFactureSale, s);
            } catch (SQLException ex) {
                Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void select_client(ActionEvent event) {
        Client c = client.getSelectionModel().getSelectedItem();
        if (c != null) {
            FactureSale Fs = new FactureSale();
            String s = "";
            if (c.getId() != 0) {
                Fs.setIdClient(c.getId());
                //client.setText(FactureSaleClient.getName());
                s = "client";
            }

            try {
                refrechFacture(FactureTable, codeC, clientC, priceT_C, paymentC, dateC, userC, RemiseC, Fs, s);
            } catch (SQLException ex) {
                Logger.getLogger(Facture_saleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
