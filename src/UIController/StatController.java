/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbClientAgent;
import static BackEnd.DbAgents.DbClientAgent.getTotaldebt;
import BackEnd.DbAgents.DbFactureAgent;
import static BackEnd.DbAgents.DbFactureAgent.getPrsenterDate;
import BackEnd.DbAgents.DbFactureSaleAgent;
import BackEnd.DbAgents.DbGoodsAgent;
import BackEnd.models.Client;
import BackEnd.models.Facture;
import BackEnd.models.FactureSale;
import BackEnd.models.Goods;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import static BackEnd.DbAgents.DbFactureSaleAgent.getSalesenterDate;
import static BackEnd.DbAgents.DbGoodsAgent.getTotalproduct;
import static BackEnd.DbAgents.DbSaleAgent.getBenefitenterDate;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class StatController implements Initializable {

    @FXML
    private Label nbrPrdts;

    private BorderPane Pane;

    public static String date;
    @FXML
    private Pane statbordPrd;
    @FXML
    private Pane statbordSale;
    @FXML
    private Pane statbordPurchase;
    @FXML
    private Pane statbordDette;
    @FXML
    private JFXDatePicker dateStat;
    @FXML
    private TableColumn<?, ?> productPC;
    @FXML
    private TableColumn<?, ?> quantityPC;
    @FXML
    private TableView<?> ProductTable;
    @FXML
    private TableColumn<?, ?> ClientSle;
    @FXML
    private TableColumn<?, ?> MontentSle;
    @FXML
    private TableColumn<?, ?> DateSle;
    @FXML
    private TableColumn<?, ?> ProviderPrs;
    @FXML
    private TableColumn<?, ?> MontentPrs;
    @FXML
    private TableColumn<?, ?> DatePrs;
    @FXML
    private TableColumn<?, ?> Client;
    @FXML
    private TableColumn<?, ?> Debt;
    @FXML
    private TableColumn<?, ?> Adr;
    @FXML
    private TableView<?> SleTable;
    @FXML
    private TableView<?> PrsTable;
    @FXML
    private TableView<?> ClientTable;
    @FXML
    private Label Sale30day;
    @FXML
    private Label Sale1day;
    @FXML
    private Label Prs30day;
    @FXML
    private Label Prs1day;
    @FXML
    private Label DebtTotal;
    @FXML
    private Label Benefit30day;
    @FXML
    private Label Benefit1day;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date = "";
        dateStat.setValue(LocalDate.now());
        LocalDate x = LocalDate.now().minusDays(30);
        FactureSale FS = new FactureSale();
        FS.setDate(x.toString());
        Facture F = new Facture();
        F.setDate(x.toString());
        Sale1day.setText(new BigDecimal(getSalesenterDate(LocalDate.now().toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Sale30day.setText(new BigDecimal(getSalesenterDate(LocalDate.now().minusDays(30).toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        Prs1day.setText(new BigDecimal(getPrsenterDate(LocalDate.now().toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Prs30day.setText(new BigDecimal(getPrsenterDate(LocalDate.now().minusDays(30).toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());

        DebtTotal.setText(new BigDecimal(getTotaldebt()).setScale(2, RoundingMode.HALF_UP).toPlainString());

        nbrPrdts.setText(getTotalproduct() + "");

        Benefit1day.setText(new BigDecimal(getBenefitenterDate(LocalDate.now().toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Benefit30day.setText(new BigDecimal(getBenefitenterDate(LocalDate.now().minusDays(30).toString(), LocalDate.now().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        try {
            refrechGoods(ProductTable, productPC, quantityPC, new Goods(), "minquantity");
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            refrechFactureSale(SleTable, ClientSle, MontentSle, DateSle, FS, "statSle");
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            refrechFacturePrs(PrsTable, ProviderPrs, MontentPrs, DatePrs, F, "statPrs");
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            refrechClient(ClientTable, Client, Debt, Adr, new Client());
        } catch (SQLException ex) {
            Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refrechClient(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, Client Cl)
            throws SQLException {
        ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(Cl, "list_noir");
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("totaleDebtS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("address")
        );
        table.setItems(cl);
    }

    private void refrechGoods(TableView table, TableColumn Column1, TableColumn Column2, Goods Gd, String s)
            throws SQLException {
        ObservableList<Goods> pr = (ObservableList<Goods>) DbGoodsAgent.getGoods(Gd, s);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        table.setItems(pr);
    }

    private void refrechFactureSale(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, FactureSale Fc, String s)
            throws SQLException {
        ObservableList<FactureSale> pr = (ObservableList<FactureSale>) DbFactureSaleAgent.getFactures(Fc, s);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("client")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("TotalAmountS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        table.setItems(pr);
    }

    public void refrechFacturePrs(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, Facture Fc, String f)
            throws SQLException {
        ObservableList<Facture> pr = (ObservableList<Facture>) DbFactureAgent.getFactures(Fc, f);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("provider")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("TotalAmountS")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        table.setItems(pr);
    }

    @FXML
    private void dateStat(ActionEvent event) {
        Benefit1day.setText(new BigDecimal(getBenefitenterDate(dateStat.getValue().toString(), dateStat.getValue().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Sale1day.setText(new BigDecimal(getSalesenterDate(dateStat.getValue().toString(), dateStat.getValue().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
        Prs1day.setText(new BigDecimal(getPrsenterDate(dateStat.getValue().toString(), dateStat.getValue().plusDays(1).toString())).setScale(2, RoundingMode.HALF_UP).toPlainString());
    }

    private void refrechFacture(TableView<?> SleTable, TableColumn<?, ?> ClientSle, TableColumn<?, ?> MontentSle, TableColumn<?, ?> DateSle, FactureSale factureSale, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void statDate(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/statDate.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("statiques enter deux date");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void capital(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontEnd/capital.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("capital");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
