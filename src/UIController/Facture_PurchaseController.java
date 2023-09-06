/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbFactureAgent;
import static BackEnd.DbAgents.DbFactureAgent.deleteFacture;
import static BackEnd.DbAgents.DbFactureAgent.getbenefitFacturePurchase;
import BackEnd.DbAgents.DbProviderAgent;
import static BackEnd.DbAgents.DbProviderAgent.getdebtProvider;
import BackEnd.DbAgents.DbPurchaseAgent;
import BackEnd.models.Facture;
import BackEnd.models.Provider;
import BackEnd.models.Purchase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class Facture_PurchaseController implements Initializable {

    @FXML
    private TableColumn<?, ?> priceT_C;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TextField code_facture;
    @FXML
    private JFXComboBox<Provider> provider;
    @FXML
    private TableView<Facture> FactureTable;
    public static boolean ex = true;

    private static boolean select = true;
    public static Facture selectFacturePurchase = new Facture();
    public static Facture selectedFactureP = new Facture();
    public static Provider FctrProvider = new Provider();
    @FXML
    private TableColumn<?, ?> codeC;
    @FXML
    private TableColumn<?, ?> nameProviderC;
    @FXML
    private TableColumn<?, ?> PaymentC;
    @FXML
    private TableView<Purchase> PurchaseTable;
    @FXML
    private TableColumn<?, ?> productC;
    @FXML
    private TableColumn<?, ?> quantityC;
    @FXML
    private TableColumn<?, ?> price_purchaseC;
    @FXML
    private TableColumn<?, ?> price_totelC;
    @FXML
    private TableColumn<?, ?> price_saleC;
    @FXML
    private JFXButton delete;
    @FXML
    private TableColumn<?, ?> quantityRC;
    @FXML
    private TableColumn<?, ?> barcodeC;
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
    private Label DebtProviderLabel;
    @FXML
    private TableColumn<?, ?> NbrFactureC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Provider> listprovider = (ObservableList<Provider>) DbProviderAgent.getProvider(new Provider(), "");
        Provider allp=new Provider();
        allp.setAddress("");
        allp.setName("جميع الموردين");
        provider.getItems().add(allp);
        provider.getItems().addAll(listprovider);
        provider.getSelectionModel().selectFirst();
        try {
            refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, new Facture(), "");
        } catch (SQLException ex) {
            Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refrechFacture(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5,
            TableColumn Column6, Facture Fc, String f)
            throws SQLException {
        ObservableList<Facture> pr = (ObservableList<Facture>) DbFactureAgent.getFactures(Fc, f);
        if (!pr.isEmpty()) {
            selectFacturePurchase = pr.get(0);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(pr.get(0).getDate(), formatter);
            date.setValue(dateTime.toLocalDate());
            benefitFacture.setText(new BigDecimal(getbenefitFacturePurchase(pr.get(0).getId())).setScale(2, RoundingMode.HALF_UP).toPlainString());
            DebtProviderLabel.setText(new BigDecimal(getdebtProvider(pr.get(0).getIdProvider())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        } else {
            selectFacturePurchase = new Facture();
            benefitFacture.setText("0.00");
            DebtProviderLabel.setText("0.00");
        }
        refrechSum(SommeTable, SumMontentC, SumRemiseC, SumMontent_RemiseC, SumpaymentC, NbrFactureC, Fc, f);
        Purchase newP = new Purchase();
        newP.setIdFacture(selectFacturePurchase.getId());
        try {
            refrechPurchase();
        } catch (SQLException ex) {
            Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("provider")
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
                new PropertyValueFactory<>("remiseS")
        );
        table.setItems(pr);
    }

    public void refrechPurchase() throws SQLException {
        Purchase newP = new Purchase();
        newP.setIdFacture(selectFacturePurchase.getId());
        ObservableList<Purchase> pr = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(newP, "facture");
        productC.setCellValueFactory(
                new PropertyValueFactory<>("Product")
        );
        barcodeC.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );
        quantityC.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        quantityRC.setCellValueFactory(
                new PropertyValueFactory<>("quantityRest")
        );
        price_purchaseC.setCellValueFactory(
                new PropertyValueFactory<>("PricePurchaseS")
        );
        price_totelC.setCellValueFactory(
                new PropertyValueFactory<>("PriceTotalS")
        );
        price_saleC.setCellValueFactory(
                new PropertyValueFactory<>("priceSaleS")
        );
        PurchaseTable.setItems(pr);
    }

    public void refrechSum(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, Facture Fc, String s)
            throws SQLException {
        ObservableList<Facture> pr = (ObservableList<Facture>) DbFactureAgent.getSum(Fc, s);
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

    @FXML
    private void selectFacture(MouseEvent event) throws IOException, SQLException {
        select = false;
        selectFacturePurchase = (Facture) FactureTable.getSelectionModel().getSelectedItem();
        if (selectFacturePurchase != null) {
            if (event.getClickCount() == 2) {
                selectedFactureP = selectFacturePurchase;
                Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/Purchase_ar.fxml"));
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
                selectedFactureP = new Facture();
                select = true;
                try {
                    refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, new Facture(), "");
                } catch (SQLException ex1) {
                    Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } else {
                if (selectFacturePurchase.getPayment() == 0 && selectFacturePurchase.getTotalAmount() == 0) {
                    delete.setDisable(false);
                } else {
                    delete.setDisable(true);
                }
                benefitFacture.setText(new BigDecimal(getbenefitFacturePurchase(selectFacturePurchase.getId())).setScale(2, RoundingMode.HALF_UP).toPlainString());
                DebtProviderLabel.setText(new BigDecimal(getdebtProvider(selectFacturePurchase.getIdProvider())).setScale(2, RoundingMode.HALF_UP).toPlainString());
                code_facture.setText(selectFacturePurchase.getCode());
                //provider.setText(selectFacturePurchase.getProvider());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(selectFacturePurchase.getDate(), formatter);
                date.setValue(dateTime.toLocalDate());
                select = true;
                refrechPurchase();
            }
        }
    }

    @FXML
    private void code(KeyEvent event) {
        provider.setValue(new Provider());
        Facture Fs = new Facture();
        String s = "";
        if (!code_facture.getText().isEmpty()) {
            Fs.setCode(code_facture.getText());
            s = "code";
        }
        try {
            refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, Fs, s);
        } catch (SQLException ex) {
            Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectPurchase(MouseEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
        if (selectFacturePurchase.getId() != 0) {
            deleteFacture(selectFacturePurchase);
            selectFacturePurchase = new Facture();
            provider.setValue(new Provider());
            code_facture.setText("");
            try {
                refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, new Facture(), "");
            } catch (SQLException ex1) {
                Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        delete.setDisable(true);
    }

    @FXML
    private void date(ActionEvent event) {
        if (select) {
            selectFacturePurchase = new Facture();
            selectFacturePurchase.setDate(date.getValue().toString());
            try {
                refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, selectFacturePurchase, "date");
            } catch (SQLException ex1) {
                Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @FXML
    private void select_provider(ActionEvent event) {
        Provider p = provider.getSelectionModel().getSelectedItem();
        if (p != null) {
            Facture F = new Facture();
            String s = "";
            if (p.getId() != 0) {
                F.setIdProvider(p.getId());
                s = "provider";
            }
            try {
                refrechFacture(FactureTable, codeC, nameProviderC, priceT_C, PaymentC, dateC, RemiseC, F, s);
            } catch (SQLException ex) {
                Logger.getLogger(Facture_PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
