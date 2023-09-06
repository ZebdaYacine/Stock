
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbClientAgent;
import BackEnd.DbAgents.DbFactureSaleAgent;
import static BackEnd.DbAgents.DbFactureSaleAgent.generatedCodeFactureSale;
import BackEnd.DbAgents.DbGoodsAgent;
import BackEnd.DbAgents.DbPurchaseAgent;
import BackEnd.DbAgents.DbSaleAgent;
import static BackEnd.Functions.*;
import BackEnd.Results;
import static BackEnd.alerts.alertDelete;
import static BackEnd.alerts.alertDelete_ar;
import BackEnd.models.Client;
import BackEnd.models.FactureSale;
import BackEnd.models.Goods;
import BackEnd.models.Purchase;
import BackEnd.models.Sale;
import static UIController.Facture_saleController.selectedFSale;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static stock.Stock.lang;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SaleController implements Initializable {

    @FXML
    private TableView<Sale> SaleTable;
    @FXML
    private TableColumn quantityC;

    @FXML
    private TextField quantity, quantityRest, quantityRetour;
    @FXML
    private Pane quantity_err;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;
    @FXML
    private TableColumn<?, ?> price_totelC;
    @FXML
    private TableColumn<?, ?> productC;
    @FXML
    private TableColumn<?, ?> price_purchaseC;
    @FXML
    private TableColumn<?, ?> price_saleC;
    @FXML
    private Label DebtR;
    @FXML
    private TextField price_sale;
    @FXML
    private JFXComboBox<Purchase> price_purchase;
    @FXML
    private Pane product_err;
    @FXML
    private TextField productT;
    @FXML
    private Label priceTotal;
    @FXML
    private Label LastDebt;
    @FXML
    private JFXTextField Payment;
    @FXML
    private Label MontentTotal;
    @FXML
    private JFXButton applyFacture;
    @FXML
    private Label Debt_Montent;
    @FXML
    private Pane remise_err;
    @FXML
    private JFXTextField remiseValue;
    @FXML
    private Label MontentTotal_remise;
    @FXML
    private Pane payment_err;
    @FXML
    private Pane price_sale_err;
    @FXML
    private Pane price_purchase_err;
    @FXML
    private TableColumn<?, ?> benefitC;
    @FXML
    private Label quantityRlabel;
    @FXML
    private TableColumn<?, ?> quantityRC;
    @FXML
    private HBox barcode_err;
    @FXML
    private JFXTextField barcode;
    @FXML
    private JFXCheckBox speedSale;
    @FXML
    private TableView<Goods> goodsTable;
    @FXML
    private TableColumn<?, ?> productSelectC;
    @FXML
    private TableColumn<?, ?> quantitySelectC;
    @FXML
    private HBox barcode_err2;
    @FXML
    private JFXTextField barcode2;
    @FXML
    private Pane price_purchase_err2;
    @FXML
    private JFXComboBox<Purchase> price_purchase2;
    @FXML
    private Pane quantity_err2;
    @FXML
    private TextField quantity2;
    @FXML
    private TextField quantityRest2;
    @FXML
    private Label quantityRlabel2;
    @FXML
    private TextField quantityRetour2;
    @FXML
    private Pane price_sale_err2;
    @FXML
    private TextField price_sale2;
    @FXML
    private JFXCheckBox speedSale2;
    @FXML
    private Label priceTotal2;
    @FXML
    private JFXButton add2;
    @FXML
    private JFXButton update2;
    @FXML
    private JFXButton delete2;
    @FXML
    private Label LastDebt2;
    @FXML
    private TableView<?> SaleTable2;
    @FXML
    private TableColumn<?, ?> productC2;
    @FXML
    private TableColumn<?, ?> price_purchaseC2;
    @FXML
    private TableColumn<?, ?> quantityC2;
    @FXML
    private TableColumn<?, ?> quantityRC2;
    @FXML
    private TableColumn<?, ?> price_saleC2;
    @FXML
    private TableColumn<?, ?> price_totelC2;
    @FXML
    private TableColumn<?, ?> benefitC2;
    @FXML
    private Label MontentTotal2;
    @FXML
    private HBox remise_err2;
    @FXML
    private JFXTextField remiseValue2;
    @FXML
    private Label MontentTotal_remise2;
    @FXML
    private Label Debt_Montent2;
    @FXML
    private Pane payment_err2;
    @FXML
    private JFXTextField Payment2;
    @FXML
    private Label DebtR2;
    @FXML
    private JFXButton applyFacture2;
    @FXML
    private VBox product_err2;
    @FXML
    private JFXTextField productT2;
    @FXML
    private TableView<?> goodsTable2;
    @FXML
    private TableColumn<?, ?> productSelectC2;
    @FXML
    private TableColumn<?, ?> quantitySelectC2;
    @FXML
    private HBox barcode_err3;
    @FXML
    private JFXTextField barcode3;
    @FXML
    private Pane price_purchase_err3;
    @FXML
    private JFXComboBox<Purchase> price_purchase3;
    @FXML
    private Pane quantity_err3;
    @FXML
    private TextField quantity3;
    @FXML
    private TextField quantityRest3;
    @FXML
    private Label quantityRlabel3;
    @FXML
    private TextField quantityRetour3;
    @FXML
    private Pane price_sale_err3;
    @FXML
    private TextField price_sale3;
    @FXML
    private JFXCheckBox speedSale3;
    @FXML
    private Label priceTotal3;
    @FXML
    private JFXButton add3;
    @FXML
    private JFXButton update3;
    @FXML
    private JFXButton delete3;
    @FXML
    private Label LastDebt3;
    @FXML
    private TableView<?> SaleTable3;
    @FXML
    private TableColumn<?, ?> productC3;
    @FXML
    private TableColumn<?, ?> price_purchaseC3;
    @FXML
    private TableColumn<?, ?> quantityC3;
    @FXML
    private TableColumn<?, ?> quantityRC3;
    @FXML
    private TableColumn<?, ?> price_saleC3;
    @FXML
    private TableColumn<?, ?> price_totelC3;
    @FXML
    private TableColumn<?, ?> benefitC3;
    @FXML
    private Label MontentTotal3;
    @FXML
    private HBox remise_err3;
    @FXML
    private JFXTextField remiseValue3;
    @FXML
    private Label MontentTotal_remise3;
    @FXML
    private Label Debt_Montent3;
    @FXML
    private Pane payment_err3;
    @FXML
    private JFXTextField Payment3;
    @FXML
    private Label DebtR3;
    @FXML
    private JFXButton applyFacture3;
    @FXML
    private VBox product_err3;
    @FXML
    private JFXTextField productT3;
    @FXML
    private TableView<?> goodsTable3;
    @FXML
    private TableColumn<?, ?> productSelectC3;
    @FXML
    private TableColumn<?, ?> quantitySelectC3;
    @FXML
    private Tab client2;
    @FXML
    private Tab client3;
    @FXML
    private AnchorPane prod2;
    @FXML
    private AnchorPane prod3;
    @FXML
    private AnchorPane prod;
    @FXML
    private JFXTabPane fav;
    @FXML
    private JFXListView<Label> Fav1_listImage1;
    @FXML
    private JFXListView<Label> Fav1_listImage;
    @FXML
    private JFXListView<Label> Fav2_listImage1;
    @FXML
    private JFXListView<Label> Fav2_listImage;
    private static int i = 0;
    @FXML
    private JFXTabPane fav1;
    @FXML
    private AnchorPane prod1;
    @FXML
    private AnchorPane prod21;
    @FXML
    private AnchorPane prod31;
    @FXML
    private JFXTabPane fav2;
    @FXML
    private AnchorPane prod22;
    @FXML
    private AnchorPane prod32;
    @FXML
    private JFXListView<Label> Fav1_listImage21;
    @FXML
    private JFXListView<Label> Fav1_listImage2;
    @FXML
    private JFXListView<Label> Fav2_listImage21;
    @FXML
    private JFXListView<Label> Fav2_listImage2;
    @FXML
    private JFXListView<Label> Fav1_listImage31;
    @FXML
    private JFXListView<Label> Fav1_listImage3;
    @FXML
    private JFXListView<Label> Fav2_listImage31;
    @FXML
    private JFXListView<Label> Fav2_listImage3;

    public static boolean ex = true;
    public static boolean ex2 = true;
    public static boolean ex3 = true;

    private static Purchase PurchaseSaleSelected = new Purchase(), PurchaseSaleSelected2 = new Purchase(), PurchaseSaleSelected3 = new Purchase();
    public static FactureSale SaleFacture = new FactureSale(), SaleFacture2 = new FactureSale(), SaleFacture3 = new FactureSale();
    private Sale SaleSelected = new Sale(), SaleSelected2 = new Sale(), SaleSelected3 = new Sale();
    public static Goods SaleGoods = new Goods(), SaleGoods2 = new Goods(), SaleGoods3 = new Goods();
    public static Client SaleClient = new Client(), SaleClient2 = new Client(), SaleClient3 = new Client();
    public static int clientSelect = 1;
    public static Purchase lastPurchase = new Purchase(), lastPurchase2 = new Purchase(), lastPurchase3 = new Purchase();
    public static Purchase lastPurchaseSale = new Purchase(), lastPurchaseSale2 = new Purchase(), lastPurchaseSale3 = new Purchase();
    public static ObservableList<Purchase> Purchase_ListSale, Purchase_ListSale2, Purchase_ListSale3;
    ObservableList<Goods> fav1list, fav2list, fav1list2, fav2list2, fav1list3, fav2list3;
    @FXML
    private JFXComboBox<Client> clientTcombo;
    @FXML
    private JFXComboBox<Client> clientTcombo2;
    @FXML
    private JFXComboBox<Client> clientTcombo3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        switch (loginUser.getRole()) {
            case "user":
                price_purchase_err.setVisible(false);
                price_purchaseC.setVisible(false);
                benefitC.setVisible(false);
                break;
            case "simple":
                price_purchase_err.setVisible(false);
                price_purchaseC.setVisible(false);
                benefitC.setVisible(false);
                break;
            default:
                break;
        }
        Purchase_ListSale = null;
        lastPurchaseSale = new Purchase();
        Sale sale = new Sale();
        if (selectedFSale.getId() != 0) {
            client2.setDisable(true);
            client3.setDisable(true);
            sale.setIdFacture(selectedFSale.getId());
            String mtotal = new BigDecimal(selectedFSale.getTotalAmount() + selectedFSale.getRemise()).setScale(2, RoundingMode.HALF_UP).toPlainString();
            MontentTotal.setText(mtotal);
            remiseValue.setText(selectedFSale.getRemiseS());
            MontentTotal_remise.setText(selectedFSale.getTotalAmountS());
            Payment.setText(selectedFSale.getPaymentS());
            Client c = new Client();
            c.setId(selectedFSale.getIdClient());
            ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(c, "id");
            SaleClient = cl.get(0);
            clientTcombo.setValue(SaleClient);
            LastDebt.setText(cl.get(0).getTotaleDebtS());
            Debt_Montent.setText(cl.get(0).getTotaleDebtS());
            DebtR.setText(cl.get(0).getTotaleDebtS());
            SaleFacture = selectedFSale;
            quantityRetour.setVisible(true);
            quantityRlabel.setVisible(true);
            if (SaleClient.getId() == 1) {
                Payment.setDisable(true);
            }
        } else {
            ObservableList<Client> listclient = (ObservableList<Client>) DbClientAgent.getClient(new Client(), "");
            clientTcombo.setItems(listclient);
            clientTcombo2.setItems(listclient);
            clientTcombo3.setItems(listclient);
            SaleClient = new Client(1);
            ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(SaleClient, "id");
            SaleClient = SaleClient2 = SaleClient3 = cl.get(0);
            setclienttexts(clientTcombo, LastDebt, Debt_Montent, DebtR, SaleClient);
            setclienttexts(clientTcombo2, LastDebt2, Debt_Montent2, DebtR2, SaleClient2);
            setclienttexts(clientTcombo3, LastDebt3, Debt_Montent3, DebtR3, SaleClient3);
        }
        initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
        initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
        initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture);
        initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture);
        initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture);
        initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture);

        refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, sale,
                "facture");
        refrechSale(SaleTable2, productC2, price_purchaseC2, quantityC2, price_saleC2, price_totelC2, benefitC2, quantityRC2, sale,
                "facture");
        refrechSale(SaleTable3, productC3, price_purchaseC3, quantityC3, price_saleC3, price_totelC3, benefitC3, quantityRC3, sale,
                "facture");
        refrechProducts(productT, SaleFacture, goodsTable, productSelectC, quantitySelectC);
        refrechProducts(productT2, SaleFacture2, goodsTable2, productSelectC2, quantitySelectC2);
        refrechProducts(productT3, SaleFacture3, goodsTable3, productSelectC3, quantitySelectC3);
        barcode.setText("");

    }

    @FXML
    private void selectclient(ActionEvent event) {
        Client c = clientTcombo.getSelectionModel().getSelectedItem();
        if (c != null) {
            SaleClient = c;
            LastDebt.setText(SaleClient.getTotaleDebtS());
            Debt_Montent.setText(SaleClient.getTotaleDebtS());
            DebtR.setText(SaleClient.getTotaleDebtS());
        }
    }

    @FXML
    private void selectclien2(ActionEvent event) {
        Client c = clientTcombo2.getSelectionModel().getSelectedItem();
        if (c != null) {
            SaleClient2 = c;
            LastDebt2.setText(SaleClient2.getTotaleDebtS());
            Debt_Montent2.setText(SaleClient2.getTotaleDebtS());
            DebtR2.setText(SaleClient2.getTotaleDebtS());
        }
    }

    @FXML
    private void selectclient3(ActionEvent event) {
        Client c = clientTcombo3.getSelectionModel().getSelectedItem();
        if (c != null) {
            SaleClient3 = c;
            System.out.println("UIController.SaleController.selectclient3()" + SaleClient3.getName());
            LastDebt3.setText(SaleClient3.getTotaleDebtS());
            Debt_Montent3.setText(SaleClient3.getTotaleDebtS());
            DebtR3.setText(SaleClient3.getTotaleDebtS());
        }
    }

    private void setclienttexts(ComboBox<Client> clientT, Label LastDebt, Label Debt_Montent, Label DebtR, Client SaleClient) {
        clientT.getSelectionModel().selectFirst();
        LastDebt.setText(SaleClient.getTotaleDebtS());
        Debt_Montent.setText(SaleClient.getTotaleDebtS());
        DebtR.setText(SaleClient.getTotaleDebtS());
    }

    public void refrechSale(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4, TableColumn Column5, TableColumn Column6,
            TableColumn Column7, Sale pt, String q) {
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

    public void refrechProducts(TextField productT, FactureSale SaleFacture, TableView goodsTable, TableColumn productSelectC, TableColumn quantitySelectC) {
        Goods g = new Goods();
        String s;
        g.setName(productT.getText());
        g.setId(SaleFacture.getId());
        // id-goods = id-facture for search
        s = "goods_not_in_sale";
        ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(g, s);
        goodsTable.setItems(goodslist);
        productSelectC.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantitySelectC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    private FactureSale newfacture(FactureSale SaleFacture, Client SaleClient, TextField Payment) {
        SaleFacture.setCode(generatedCodeFactureSale());
        SaleFacture.setIdClient(SaleClient.getId());
        SaleFacture.setIdUser(loginUser.getId());
        LocalDateTime lDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        String formattedS = lDate.format(formatter);
        SaleFacture.setDate(formattedS);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(null);
        Results.Rstls r = DbFactureSaleAgent.addFactur(SaleFacture);
        if (r == Results.Rstls.DATA_NOT_INSERTED) {
            a.setContentText(r + "");
            a.showAndWait();
        } else {

            ObservableList<FactureSale> listFs = (ObservableList<FactureSale>) DbFactureSaleAgent.getFactures(SaleFacture, "code");
            SaleFacture = listFs.get(0);
            if (SaleClient.getId() == 1) {
                Payment.setDisable(true);
            }

        }
        return SaleFacture;
    }

    public boolean testinput(TextField productT, Pane product_err, TextField quantity,
            TextField quantityRetour, Pane quantity_err, TextField quantityRest,
            TextField price_sale, Pane price_sale_err) {
        boolean Bproduct = false, Bquantity = false, BpriceSale = false;
        if (productT.getText().isEmpty()) {
            product_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            product_err.setStyle("-fx-border-color: transparent;");
            Bproduct = true;
        }
        if (quantity.getText().isEmpty() || quantityRetour.getText().isEmpty()) {
            quantity_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(quantity) && testdouble(quantityRetour) && testdouble(quantityRest)) {
                if (Double.parseDouble(quantityRest.getText()) >= 0
                        && (calculateQ(quantity.getText()) >= Double.parseDouble(quantityRetour.getText()))) {
                    quantity_err.setStyle("-fx-border-color: transparent;");
                    Bquantity = true;
                } else {
                    quantity_err.setStyle("-fx-border-color: #f70d1b;");
                }

            } else {
                quantity_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        if (price_sale.getText().isEmpty()) {
            price_sale_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(price_sale)) {
                price_sale_err.setStyle("-fx-border-color: transparent;");
                BpriceSale = true;
            } else {
                price_sale_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }

        return Bproduct && Bquantity && BpriceSale;
    }

    public void setpaneTransparent(Pane price_sale_err, Pane quantity_err, Pane product_err) {
        price_sale_err.setStyle("-fx-border-color: transparent;");
        quantity_err.setStyle("-fx-border-color: transparent;");
        product_err.setStyle("-fx-border-color: transparent;");
    }

    @FXML
    private void select_Product(MouseEvent event) {
        Goods G = (Goods) goodsTable.getSelectionModel().getSelectedItem();
        if (G != null) {
            SaleGoods = G;
            lastPurchaseSale.setIdProduct(G.getId());
            Purchase_ListSale = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale, "goodsSale");
            lastPurchaseSale = Purchase_ListSale.get(0);
            SaleSelected = new Sale();
            productT.setText(SaleGoods.getName());
            if (lastPurchaseSale.getId() != 0) {
                barcode.setText(lastPurchaseSale.getCode());
                price_purchase.setItems(Purchase_ListSale);
                priceTotal.setText(lastPurchaseSale.getPricePurchaseS());
                price_sale.setText(lastPurchaseSale.getPriceSaleS());
                quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
            } else {
                barcode.setText("");
                price_purchase.getItems().clear();
                priceTotal.setText("0.00");
                price_sale.setText("");
            }
            quantity.setText("1");
            if (event.getClickCount() == 2) {
                add();
            }
        }
    }

    private void selectedProduct(MouseEvent event, Goods SaleGoods, int f, Purchase lastPurchaseSale, ObservableList<Purchase> Purchase_ListSale, Sale SaleSelected,
            TextField productT, TextField barcode, TextField price_purchase, Label priceTotal, TextField price_sale, TextField quantityRest, TextField quantity) {
        if (SaleGoods != null) {
            lastPurchaseSale.setIdProduct(SaleGoods.getId());
            Purchase_ListSale = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale, "goodsSale");
            lastPurchaseSale = Purchase_ListSale.get(0);
            SaleSelected = new Sale();
            productT.setText(SaleGoods.getName());
            if (lastPurchaseSale.getId() != 0) {
                barcode.setText(lastPurchaseSale.getCode());
                price_purchase.setText(lastPurchaseSale.getPricePurchaseS());
                priceTotal.setText(lastPurchaseSale.getPricePurchaseS());
                price_sale.setText(lastPurchaseSale.getPriceSaleS());
                quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
            } else {
                barcode.setText("");
                price_purchase.setText("");
                priceTotal.setText("0.00");
                price_sale.setText("");
            }
            quantity.setText("1");
            if (event.getClickCount() == 2) {
                switch (f) {
                    case 1:
                        add();
                        break;
                    case 2:
                        add2();
                        break;
                    case 3:
                        add3();
                        break;
                    default:
                        break;
                }
                initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
            }
        }
    }

    @FXML
    private void select_Product2(MouseEvent event) {
        Goods G = (Goods) goodsTable2.getSelectionModel().getSelectedItem();
        if (G != null) {
            SaleGoods2 = G;
            lastPurchaseSale2.setIdProduct(G.getId());
            Purchase_ListSale2 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale2, "goodsSale");
            lastPurchaseSale2 = Purchase_ListSale2.get(0);
            SaleSelected2 = new Sale();
            productT2.setText(SaleGoods2.getName());
            if (lastPurchaseSale2.getId() != 0) {
                barcode2.setText(lastPurchaseSale2.getCode());
                price_purchase2.setItems(Purchase_ListSale2);
                priceTotal2.setText(lastPurchaseSale2.getPricePurchaseS());
                price_sale2.setText(lastPurchaseSale2.getPriceSaleS());
                quantityRest2.setText(lastPurchaseSale2.getQuantityRest() - 1 + "");
            } else {
                barcode2.setText("");
                price_purchase2.getItems().clear();
                priceTotal2.setText("0.00");
                price_sale2.setText("");
            }
            quantity2.setText("1");
            if (event.getClickCount() == 2) {
                add2();
            }
        }
    }

    @FXML
    private void select_Product3(MouseEvent event) {
        Goods G = (Goods) goodsTable3.getSelectionModel().getSelectedItem();
        if (G != null) {
            SaleGoods3 = G;
            lastPurchaseSale3.setIdProduct(G.getId());
            Purchase_ListSale3 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale3, "goodsSale");
            lastPurchaseSale3 = Purchase_ListSale3.get(0);
            SaleSelected3 = new Sale();
            productT3.setText(SaleGoods3.getName());
            if (lastPurchaseSale3.getId() != 0) {
                barcode3.setText(lastPurchaseSale3.getCode());
                price_purchase3.setItems(Purchase_ListSale3);
                priceTotal3.setText(lastPurchaseSale3.getPricePurchaseS());
                price_sale3.setText(lastPurchaseSale3.getPriceSaleS());
                quantityRest3.setText(lastPurchaseSale3.getQuantityRest() - 1 + "");
            } else {
                barcode3.setText("");
                price_purchase3.getItems().clear();
                priceTotal3.setText("0.00");
                price_sale3.setText("");
            }
            quantity3.setText("1");
            if (event.getClickCount() == 2) {
                add3();
            }
        }
    }

    @FXML
    private void lastPurchaseSale(ActionEvent event) {
        Purchase p = new Purchase();
        p.setCode(barcode.getText());
        Purchase_ListSale = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(p, "goodsSaleCodeBar");
        if (!Purchase_ListSale.isEmpty()) {
            lastPurchaseSale = Purchase_ListSale.get(0);
            SaleSelected = new Sale();
            productT.setText(lastPurchaseSale.getProduct());
            price_purchase.setValue(lastPurchaseSale);
            priceTotal.setText(lastPurchaseSale.getPriceSaleS());
            price_sale.setText(lastPurchaseSale.getPriceSaleS());
            SaleGoods.setId(lastPurchaseSale.getIdProduct());
            quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods, "id");
            SaleGoods = goodslist.get(0);
            if (speedSale.isSelected()) {
                if (SaleFacture.getId() == 0) {
                    SaleFacture = newfacture(SaleFacture, SaleClient, Payment);
                }
                Sale PonFsale = new Sale();
                PonFsale.setIdFacture(SaleFacture.getId());
                PonFsale.setIdPurchase(lastPurchaseSale.getId());
                ObservableList<Sale> PonS = (ObservableList<Sale>) DbSaleAgent.getSales(PonFsale, "PonFsale");
                if (PonS.isEmpty()) {
                    quantity.setText("1");

                    add();
                } else {
                    PurchaseSaleSelected = lastPurchaseSale;
                    SaleSelected = PonS.get(0);
                    quantity.setText(SaleSelected.getQuantity() + 1 + "");
                    update();
                }
            } else {
                quantity.setText("1");
            }

        } else {
            lastPurchaseSale = new Purchase();
            productT.setText("");
            price_purchase.getItems().clear();
            priceTotal.setText("0.00");
            price_sale.setText("0");
            quantityRest.setText("0");
            quantity.setText("1");
        }

    }

    @FXML
    private void lastPurchaseSale2(ActionEvent event) {
        Purchase p = new Purchase();
        p.setCode(barcode2.getText());
        Purchase_ListSale2 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(p, "goodsSaleCodeBar");
        if (!Purchase_ListSale2.isEmpty()) {
            lastPurchaseSale2 = Purchase_ListSale2.get(0);
            SaleSelected2 = new Sale();
            productT2.setText(lastPurchaseSale2.getProduct());
            price_purchase2.setValue(lastPurchaseSale2);
            priceTotal2.setText(lastPurchaseSale2.getPriceSaleS());
            price_sale2.setText(lastPurchaseSale2.getPriceSaleS());
            SaleGoods2.setId(lastPurchaseSale2.getIdProduct());
            quantityRest2.setText(lastPurchaseSale2.getQuantityRest() - 1 + "");
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods2, "id");
            SaleGoods2 = goodslist.get(0);
            if (speedSale2.isSelected()) {
                if (SaleFacture2.getId() == 0) {
                    SaleFacture2 = newfacture(SaleFacture2, SaleClient2, Payment2);
                }
                Sale PonFsale = new Sale();
                PonFsale.setIdFacture(SaleFacture2.getId());
                PonFsale.setIdPurchase(lastPurchaseSale2.getId());
                ObservableList<Sale> PonS = (ObservableList<Sale>) DbSaleAgent.getSales(PonFsale, "PonFsale");
                if (PonS.isEmpty()) {
                    quantity2.setText("1");
                    add2();
                } else {
                    PurchaseSaleSelected2 = lastPurchaseSale2;
                    SaleSelected2 = PonS.get(0);
                    quantity2.setText(SaleSelected2.getQuantity() + 1 + "");
                    update2();
                }
            } else {
                quantity2.setText("1");
            }

        } else {
            lastPurchaseSale2 = new Purchase();
            productT2.setText("");
            price_purchase2.getItems().clear();
            priceTotal2.setText("0.00");
            price_sale2.setText("0");
            quantityRest2.setText("0");
            quantity2.setText("1");
        }

    }

    @FXML
    private void lastPurchaseSale3(ActionEvent event) {
        Purchase p = new Purchase();
        p.setCode(barcode3.getText());
        Purchase_ListSale3 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(p, "goodsSaleCodeBar");
        if (!Purchase_ListSale3.isEmpty()) {
            lastPurchaseSale3 = Purchase_ListSale3.get(0);
            SaleSelected3 = new Sale();
            productT3.setText(lastPurchaseSale3.getProduct());
            price_purchase3.setValue(lastPurchaseSale3);
            priceTotal3.setText(lastPurchaseSale3.getPriceSaleS());
            price_sale3.setText(lastPurchaseSale3.getPriceSaleS());
            SaleGoods3.setId(lastPurchaseSale3.getIdProduct());
            quantityRest3.setText(lastPurchaseSale3.getQuantityRest() - 1 + "");
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods3, "id");
            SaleGoods3 = goodslist.get(0);
            if (speedSale3.isSelected()) {
                if (SaleFacture3.getId() == 0) {
                    SaleFacture3 = newfacture(SaleFacture3, SaleClient3, Payment3);
                }
                Sale PonFsale = new Sale();
                PonFsale.setIdFacture(SaleFacture3.getId());
                PonFsale.setIdPurchase(lastPurchaseSale3.getId());
                ObservableList<Sale> PonS = (ObservableList<Sale>) DbSaleAgent.getSales(PonFsale, "PonFsale");
                if (PonS.isEmpty()) {
                    quantity3.setText("1");
                    add3();
                } else {
                    PurchaseSaleSelected3 = lastPurchaseSale3;
                    SaleSelected3 = PonS.get(0);
                    quantity3.setText(SaleSelected3.getQuantity() + 1 + "");
                    update3();
                }
            } else {
                quantity3.setText("1");
            }

        } else {
            lastPurchaseSale3 = new Purchase();
            productT3.setText("");
            price_purchase3.getItems().clear();
            priceTotal3.setText("0.00");
            price_sale3.setText("0");
            quantityRest3.setText("0");
            quantity3.setText("1");
        }

    }

    @FXML
    private void add() {
        if (testinput(productT, product_err, quantity,
                quantityRetour, quantity_err, quantityRest,
                price_sale, price_sale_err)) {
            if (SaleFacture.getId() == 0) {
                SaleFacture = newfacture(SaleFacture, SaleClient, Payment);
            }
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture.getId());
            sale.setIdProduct(SaleGoods.getId());
            sale.setQuantity(calculateQ(quantity.getText()));
            sale.setQuantityRetour(0);
            sale.setPricePurchase(lastPurchaseSale.getPricePurchase());
            sale.setIdPurchase(lastPurchaseSale.getId());
            sale.setPrice_sale(Double.parseDouble(price_sale.getText()));
            sale.setPriceTotal(Double.parseDouble(price_sale.getText()) * sale.getQuantity());
            sale.setBenefitSale((Double.parseDouble(price_sale.getText()) - lastPurchaseSale.getPricePurchase()) * sale.getQuantity());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            Results.Rstls r = DbSaleAgent.addSale(sale);
            if (r == Results.Rstls.DATA_INSERTED) {
                ex = false;
                addSale(lastPurchaseSale, quantityRest, SaleGoods, sale, MontentTotal,
                        MontentTotal_remise, Debt_Montent, LastDebt, SaleClient, Payment, DebtR, a);
                app(SaleFacture, remiseValue, MontentTotal_remise, Payment, DebtR, SaleClient);
                //clear static var
                SaleGoods = new Goods();
                lastPurchaseSale = new Purchase();
                Purchase_ListSale = null;
                clearInputP(productT, quantity, quantityRest,
                        quantityRetour, priceTotal, price_sale, barcode,
                        price_purchase);
                refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, sale, "facture");

            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }
        }
    }

    private void addSale(Purchase lastPurchaseSale, TextField quantityRest, Goods SaleGoods, Sale sale, Label MontentTotal, Label MontentTotal_remise,
            Label Debt_Montent, Label LastDebt, Client SaleClient, TextField Payment, Label DebtR, Alert a) {
        lastPurchaseSale.setQuantityRest(Double.parseDouble(quantityRest.getText()));
        DbPurchaseAgent.updatePurchase(lastPurchaseSale);
        SaleGoods.setQuantity(SaleGoods.getQuantity() - sale.getQuantity());
        SaleGoods.setCountSale(SaleGoods.getCountSale() + 1);
        DbGoodsAgent.updateQuantityGoods(SaleGoods);
        double MTotal = Double.parseDouble(MontentTotal.getText());
        MTotal = MTotal + (sale.getPriceTotal());
        MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        MTotal = MTotal - Double.parseDouble(remiseValue.getText());
        MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        double debt_Montent;
        if (selectedFSale.getId() != 0) {
            debt_Montent = MTotal - selectedFSale.getTotalAmount() + Double.parseDouble(LastDebt.getText());
        } else {
            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
        }

        Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
        if (SaleClient.getId() == 1) {
            Payment.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        }
        double debtR;
        if (selectedFSale.getId() != 0) {
            debtR = debt_Montent + selectedFSale.getPayment() - Double.parseDouble(Payment.getText());
        } else {
            debtR = debt_Montent - Double.parseDouble(Payment.getText());
        }
        DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
        if (debt_Montent >= SaleClient.getMaxDebt() && SaleClient.getId() != 1) {
            if (lang == 1) {
                a.setHeaderText("le client " + SaleClient.getName() + " jusqu’à la limite d’endettement :" + debt_Montent);
            } else {
                a.setHeaderText("الزبون " + SaleClient3.getName() + " تعدى الدين الأقصى بمبلغ قدره :" + debt_Montent + " دج");
            }
            a.showAndWait();
        }

    }

    @FXML
    private void add2() {
        if (testinput(productT2, product_err2, quantity2,
                quantityRetour2, quantity_err2, quantityRest2,
                price_sale2, price_sale_err2)) {
            if (SaleFacture2.getId() == 0) {
                SaleFacture2 = newfacture(SaleFacture2, SaleClient2, Payment2);
            }
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture2.getId());
            sale.setIdProduct(SaleGoods2.getId());
            sale.setQuantity(calculateQ(quantity2.getText()));
            sale.setQuantityRetour(0);
            sale.setPricePurchase(lastPurchaseSale2.getPricePurchase());
            sale.setIdPurchase(lastPurchaseSale2.getId());
            sale.setPrice_sale(Double.parseDouble(price_sale2.getText()));
            sale.setPriceTotal(Double.parseDouble(price_sale2.getText()) * sale.getQuantity());
            sale.setBenefitSale((Double.parseDouble(price_sale2.getText()) - lastPurchaseSale2.getPricePurchase()) * sale.getQuantity());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            Results.Rstls r = DbSaleAgent.addSale(sale);
            if (r == Results.Rstls.DATA_INSERTED) {
                ex2 = false;
                addSale(lastPurchaseSale2, quantityRest2, SaleGoods2, sale, MontentTotal2,
                        MontentTotal_remise2, Debt_Montent2, LastDebt2, SaleClient2, Payment2, DebtR2, a);
                app(SaleFacture2, remiseValue2, MontentTotal_remise2, Payment2, DebtR2, SaleClient2);
                //clear static var
                SaleGoods2 = new Goods();
                lastPurchaseSale2 = new Purchase();
                Purchase_ListSale2 = null;
                clearInputP(productT2, quantity2, quantityRest2,
                        quantityRetour2, priceTotal2, price_sale2, barcode2,
                        price_purchase2);
                refrechSale(SaleTable2, productC2, price_purchaseC2, quantityC2, price_saleC2, price_totelC2, benefitC2, quantityRC2, sale, "facture");
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void add3() {
        if (testinput(productT3, product_err3, quantity3,
                quantityRetour3, quantity_err3, quantityRest3,
                price_sale3, price_sale_err3)) {
            if (SaleFacture3.getId() == 0) {
                SaleFacture3 = newfacture(SaleFacture3, SaleClient3, Payment3);
            }
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture3.getId());
            sale.setIdProduct(SaleGoods3.getId());
            sale.setQuantity(calculateQ(quantity3.getText()));
            sale.setQuantityRetour(0);
            sale.setPricePurchase(lastPurchaseSale3.getPricePurchase());
            sale.setIdPurchase(lastPurchaseSale3.getId());
            sale.setPrice_sale(Double.parseDouble(price_sale3.getText()));
            sale.setPriceTotal(Double.parseDouble(price_sale3.getText()) * sale.getQuantity());
            sale.setBenefitSale((Double.parseDouble(price_sale3.getText()) - lastPurchaseSale3.getPricePurchase()) * sale.getQuantity());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            Results.Rstls r = DbSaleAgent.addSale(sale);
            if (r == Results.Rstls.DATA_INSERTED) {
                ex3 = false;
                addSale(lastPurchaseSale3, quantityRest3, SaleGoods3, sale, MontentTotal3,
                        MontentTotal_remise3, Debt_Montent3, LastDebt3, SaleClient3, Payment3, DebtR3, a);
                app(SaleFacture3, remiseValue3, MontentTotal_remise3, Payment3, DebtR3, SaleClient3);
                //clear static var
                SaleGoods3 = new Goods();
                lastPurchaseSale3 = new Purchase();
                Purchase_ListSale3 = null;
                clearInputP(productT3, quantity3, quantityRest3,
                        quantityRetour3, priceTotal3, price_sale3, barcode3,
                        price_purchase3);
                refrechSale(SaleTable3, productC3, price_purchaseC3, quantityC3, price_saleC3, price_totelC3, benefitC3, quantityRC3, sale, "facture");
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void selectSale(MouseEvent event) {
        SaleSelected = (Sale) SaleTable.getSelectionModel().getSelectedItem();
        if (SaleSelected != null) {
            SaleGoods.setId(SaleSelected.getIdProduct());
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods, "id");
            SaleGoods = goodslist.get(0);
            PurchaseSaleSelected.setId(SaleSelected.getIdPurchase());
            ObservableList<Purchase> purchaseSelect = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(PurchaseSaleSelected, "id");
            PurchaseSaleSelected = purchaseSelect.get(0);
            quantityRest.setText(PurchaseSaleSelected.getQuantityRest() + "");
            productT.setText(SaleSelected.getProduct());
            quantity.setText(SaleSelected.getQuantity() + "");
            quantityRetour.setText(SaleSelected.getQuantityRetour() + "");
            price_purchase.setItems(purchaseSelect);
            priceTotal.setText(SaleSelected.getPriceTotalS());
            price_sale.setText(SaleSelected.getPrice_saleS());
        }
    }

    @FXML
    private void selectSale2(MouseEvent event) {
        SaleSelected2 = (Sale) SaleTable2.getSelectionModel().getSelectedItem();
        if (SaleSelected2 != null) {
            SaleGoods2.setId(SaleSelected2.getIdProduct());
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods2, "id");
            SaleGoods2 = goodslist.get(0);
            PurchaseSaleSelected2.setId(SaleSelected2.getIdPurchase());
            ObservableList<Purchase> purchaseSelect = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(PurchaseSaleSelected2, "id");
            PurchaseSaleSelected2 = purchaseSelect.get(0);
            quantityRest2.setText(PurchaseSaleSelected2.getQuantityRest() + "");
            productT2.setText(SaleSelected2.getProduct());
            quantity2.setText(SaleSelected2.getQuantity() + "");
            quantityRetour2.setText(SaleSelected2.getQuantityRetour() + "");
            price_purchase2.setItems(purchaseSelect);
            priceTotal2.setText(SaleSelected2.getPriceTotalS());
            price_sale2.setText(SaleSelected2.getPrice_saleS());
        }
    }

    @FXML
    private void selectSale3(MouseEvent event) {
        SaleSelected3 = (Sale) SaleTable3.getSelectionModel().getSelectedItem();
        if (SaleSelected3 != null) {
            SaleGoods3.setId(SaleSelected3.getIdProduct());
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(SaleGoods3, "id");
            SaleGoods3 = goodslist.get(0);
            PurchaseSaleSelected3.setId(SaleSelected3.getIdPurchase());
            ObservableList<Purchase> purchaseSelect = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(PurchaseSaleSelected3, "id");
            PurchaseSaleSelected3 = purchaseSelect.get(0);
            quantityRest3.setText(PurchaseSaleSelected3.getQuantityRest() + "");
            productT3.setText(SaleSelected3.getProduct());
            quantity3.setText(SaleSelected3.getQuantity() + "");
            quantityRetour3.setText(SaleSelected3.getQuantityRetour() + "");
            price_purchase3.setItems(purchaseSelect);
            priceTotal3.setText(SaleSelected3.getPriceTotalS());
            price_sale3.setText(SaleSelected3.getPrice_saleS());
        }
    }

    @FXML
    private void delete() {
        if (SaleSelected.getId() != 0) {

            Optional<ButtonType> option;
            if (lang == 1) {
                option = alertDelete.showAndWait();
            } else {
                option = alertDelete_ar.showAndWait();
            }
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbSaleAgent.deleteSale(SaleSelected);
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    deleteSale(SaleSelected, SaleGoods, PurchaseSaleSelected, MontentTotal, remiseValue,
                            MontentTotal_remise, Debt_Montent, SaleClient, Payment, DebtR, SaleFacture);
                    if (SaleFacture.getTotalAmount() == 0 && SaleFacture.getPayment() == 0) {
                        ex = true;
                    }
                    //clear static var
                    SaleGoods = new Goods();
                    lastPurchaseSale = new Purchase();
                    Purchase_ListSale = null;
                    clearInputP(productT, quantity, quantityRest,
                            quantityRetour, priceTotal, price_sale, barcode,
                            price_purchase);
                    refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, SaleSelected, "facture");
                    SaleSelected = new Sale();
                }
            }

        }

    }

    private void deleteSale(Sale SaleSelected, Goods SaleGoods, Purchase PurchaseSaleSelected, Label MontentTotal, TextField remiseValue,
            Label MontentTotal_remise, Label Debt_Montent, Client SaleClient, TextField Payment, Label DebtR, FactureSale SaleFacture) {

        // update quantity goods
        SaleGoods.setQuantity(SaleGoods.getQuantity() + SaleSelected.getQuantity() - SaleSelected.getQuantityRetour());
        SaleGoods.setCountSale(SaleGoods.getCountSale() - 1);
        PurchaseSaleSelected.setQuantityRest(PurchaseSaleSelected.getQuantityRest() + SaleSelected.getQuantity() - SaleSelected.getQuantityRetour());
        DbPurchaseAgent.updatePurchase(PurchaseSaleSelected);
        DbGoodsAgent.updateQuantityGoods(SaleGoods);
        double MTotal = Double.parseDouble(MontentTotal.getText());
        MTotal = MTotal - (SaleSelected.getPriceTotal());
        MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        MTotal = MTotal - Double.parseDouble(remiseValue.getText());
        MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        double debt_Montent;
        if (selectedFSale.getId() != 0) {
            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText()) - selectedFSale.getTotalAmount();
        } else {
            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
        }
        Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
        if (SaleClient.getId() == 1) {
            Payment.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        }
        double debtR;
        if (selectedFSale.getId() != 0) {
            debtR = debt_Montent - Double.parseDouble(Payment.getText()) + selectedFSale.getPayment();
        } else {
            debtR = debt_Montent - Double.parseDouble(Payment.getText());
        }
        DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
        app(SaleFacture, remiseValue, MontentTotal_remise, Payment, DebtR, SaleClient);
        if (SaleFacture.getTotalAmount() == 0 && SaleFacture.getPayment() == 0) {
            ex = true;
        }
    }

    @FXML
    private void delete2() {
        if (SaleSelected2.getId() != 0) {
            Optional<ButtonType> option;
            if (lang == 1) {
                option = alertDelete.showAndWait();
            } else {
                option = alertDelete_ar.showAndWait();
            }
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbSaleAgent.deleteSale(SaleSelected2);
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    deleteSale(SaleSelected2, SaleGoods2, PurchaseSaleSelected2, MontentTotal2, remiseValue2,
                            MontentTotal_remise2, Debt_Montent2, SaleClient2, Payment2, DebtR2, SaleFacture2);
                    if (SaleFacture2.getTotalAmount() == 0 && SaleFacture2.getPayment() == 0) {
                        ex2 = true;
                    }
                    //clear static var
                    SaleGoods2 = new Goods();
                    lastPurchaseSale2 = new Purchase();
                    Purchase_ListSale2 = null;
                    clearInputP(productT2, quantity2, quantityRest2,
                            quantityRetour2, priceTotal2, price_sale2, barcode2,
                            price_purchase2);
                    refrechSale(SaleTable2, productC2, price_purchaseC2, quantityC2, price_saleC2, price_totelC2, benefitC2, quantityRC2, SaleSelected2, "facture");
                    SaleSelected2 = new Sale();
                }
            }

        }

    }

    @FXML
    private void delete3() {
        if (SaleSelected3.getId() != 0) {
            Optional<ButtonType> option;
            if (lang == 1) {
                option = alertDelete.showAndWait();
            } else {
                option = alertDelete_ar.showAndWait();
            }
            if (option.get() == ButtonType.OK) {
                Results.Rstls r = DbSaleAgent.deleteSale(SaleSelected3);
                if (r == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(r + "");
                    a.showAndWait();
                } else {
                    deleteSale(SaleSelected3, SaleGoods3, PurchaseSaleSelected3, MontentTotal3, remiseValue3,
                            MontentTotal_remise3, Debt_Montent3, SaleClient3, Payment3, DebtR3, SaleFacture3);
                    if (SaleFacture3.getTotalAmount() == 0 && SaleFacture3.getPayment() == 0) {
                        ex3 = true;
                    }
                    //clear static var
                    SaleGoods3 = new Goods();
                    lastPurchaseSale3 = new Purchase();
                    Purchase_ListSale3 = null;
                    clearInputP(productT3, quantity3, quantityRest3,
                            quantityRetour3, priceTotal3, price_sale3, barcode3,
                            price_purchase3);
                    refrechSale(SaleTable3, productC3, price_purchaseC3, quantityC3, price_saleC3, price_totelC3, benefitC3, quantityRC3, SaleSelected3, "facture");
                    SaleSelected3 = new Sale();
                }
            }

        }

    }

    public void clearInputP(TextField productT, TextField quantity, TextField quantityRest,
            TextField quantityRetour, Label priceTotal, TextField price_sale, TextField barcode,
            ComboBox price_purchase) {
        productT.setText("");
        quantity.setText("1");
        quantityRest.setText("");
        quantityRetour.setText("0");
        priceTotal.setText("0.00");
        price_sale.setText("0.00");
        barcode.setText("");
        price_purchase.getItems().clear();
        refrechProducts(productT, SaleFacture, goodsTable, productSelectC, quantitySelectC);
        refrechProducts(productT2, SaleFacture2, goodsTable2, productSelectC2, quantitySelectC2);
        refrechProducts(productT3, SaleFacture3, goodsTable3, productSelectC3, quantitySelectC3);
        initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
        initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
        initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
        initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
        initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
        initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
    }

    public void clearlabelsF(TextField Payment, Label DebtR, TextField remiseValue, Label LastDebt,
            Label MontentTotal, Label Debt_Montent, Label MontentTotal_remise) {
        DebtR.setText("0.00");
        LastDebt.setText("0.00");
        MontentTotal.setText("0.00");
        Debt_Montent.setText("0.00");
        Payment.setText("0");
        remiseValue.setText("0");
        MontentTotal_remise.setText("0.00");
    }

    @FXML
    private void update() {
        if (SaleSelected.getId() != 0 && testinput(productT, product_err, quantity,
                quantityRetour, quantity_err, quantityRest,
                price_sale, price_sale_err)) {
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture.getId());
            sale.setId(SaleSelected.getId());
            sale.setQuantity(calculateQ(quantity.getText()));
            sale.setQuantityRetour(calculateQ(quantityRetour.getText()));
            sale.setPricePurchase(SaleSelected.getPricePurchase());
            sale.setPrice_sale(Double.parseDouble(price_sale.getText()));
            sale.setPriceTotal(sale.getPrice_sale() * (sale.getQuantity() - sale.getQuantityRetour()));
            sale.setBenefitSale((sale.getPrice_sale() - sale.getPricePurchase()) * (sale.getQuantity() - sale.getQuantityRetour()));
            Results.Rstls r = DbSaleAgent.updateSale(sale);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            if (r == Results.Rstls.DATA_UPDATED) {
                // update quantity goods
                updateSale(PurchaseSaleSelected, SaleSelected, SaleGoods, sale, MontentTotal,
                        remiseValue, MontentTotal_remise, LastDebt, Debt_Montent, SaleClient, Payment,
                        DebtR, a);
                app(SaleFacture, remiseValue, MontentTotal_remise, Payment, DebtR, SaleClient);
                // clear 
                PurchaseSaleSelected = new Purchase();
                SaleSelected = new Sale();
                SaleGoods = new Goods();
                clearInputP(productT, quantity, quantityRest,
                        quantityRetour, priceTotal, price_sale, barcode,
                        price_purchase);
                refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, sale, "facture");
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }

        }
    }

    private void updateSale(Purchase PurchaseSaleSelected, Sale SaleSelected, Goods SaleGoods, Sale sale, Label MontentTotal,
            TextField remiseValue, Label MontentTotal_remise, Label LastDebt, Label Debt_Montent, Client SaleClient, TextField Payment,
            Label DebtR, Alert a) {
        PurchaseSaleSelected.setQuantityRest(PurchaseSaleSelected.getQuantityRest()
                + SaleSelected.getQuantity() - sale.getQuantity()
                - SaleSelected.getQuantityRetour() + sale.getQuantityRetour());
        DbPurchaseAgent.updatePurchase(PurchaseSaleSelected);
        double n = SaleGoods.getQuantity() + SaleSelected.getQuantity() - sale.getQuantity() - SaleSelected.getQuantityRetour() + sale.getQuantityRetour();
        SaleGoods.setQuantity(n);
        DbGoodsAgent.updateQuantityGoods(SaleGoods);

        // update labels
        double MTotal = Double.parseDouble(MontentTotal.getText());
        MTotal = MTotal - SaleSelected.getPriceTotal() + sale.getPriceTotal();
        MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        MTotal = MTotal - Double.parseDouble(remiseValue.getText());
        MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());

        double debt_Montent;
        if (selectedFSale.getId() != 0) {
            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText()) - selectedFSale.getTotalAmount();
        } else {
            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
        }

        Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
        if (SaleClient.getId() == 1) {
            Payment.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
        }
        double debtR;
        if (selectedFSale.getId() != 0) {
            debtR = debt_Montent + selectedFSale.getPayment() - Double.parseDouble(Payment.getText());
        } else {
            debtR = debt_Montent - Double.parseDouble(Payment.getText());
        }
        DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
        if (debt_Montent >= SaleClient.getMaxDebt() && SaleClient.getId() != 1) {
            if (lang == 1) {
                a.setHeaderText("le client " + SaleClient.getName() + " jusqu’à la limite d’endettement :" + debt_Montent);
            } else {
                a.setHeaderText("الزبون " + SaleClient.getName() + " تعدى الدين الأقصى بمبلغ قدره :" + debt_Montent + " دج");
            }
            a.showAndWait();
        }
    }

    @FXML
    private void update2() {
        if (SaleSelected2.getId() != 0 && testinput(productT2, product_err2, quantity2,
                quantityRetour2, quantity_err2, quantityRest2,
                price_sale2, price_sale_err2)) {
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture2.getId());
            sale.setId(SaleSelected2.getId());
            sale.setQuantity(calculateQ(quantity2.getText()));
            sale.setQuantityRetour(calculateQ(quantityRetour2.getText()));
            sale.setPricePurchase(SaleSelected2.getPricePurchase());
            sale.setPrice_sale(Double.parseDouble(price_sale2.getText()));
            sale.setPriceTotal(sale.getPrice_sale() * (sale.getQuantity() - sale.getQuantityRetour()));
            sale.setBenefitSale((sale.getPrice_sale() - sale.getPricePurchase()) * (sale.getQuantity() - sale.getQuantityRetour()));
            Results.Rstls r = DbSaleAgent.updateSale(sale);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            if (r == Results.Rstls.DATA_UPDATED) {
                // update quantity goods
                updateSale(PurchaseSaleSelected2, SaleSelected2, SaleGoods2, sale, MontentTotal2,
                        remiseValue2, MontentTotal_remise2, LastDebt2, Debt_Montent2, SaleClient2, Payment2,
                        DebtR2, a);
                app(SaleFacture2, remiseValue2, MontentTotal_remise2, Payment2, DebtR2, SaleClient2);
                // clear 
                PurchaseSaleSelected2 = new Purchase();
                SaleSelected2 = new Sale();
                SaleGoods2 = new Goods();
                clearInputP(productT2, quantity2, quantityRest2,
                        quantityRetour2, priceTotal2, price_sale2, barcode2,
                        price_purchase2);
                refrechSale(SaleTable2, productC2, price_purchaseC2, quantityC2, price_saleC2, price_totelC2, benefitC2, quantityRC2, sale, "facture");
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }

        }
    }

    @FXML
    private void update3() {
        if (SaleSelected3.getId() != 0 && testinput(productT3, product_err3, quantity3,
                quantityRetour3, quantity_err3, quantityRest3,
                price_sale3, price_sale_err3)) {
            Sale sale = new Sale();
            sale.setIdFacture(SaleFacture3.getId());
            sale.setId(SaleSelected3.getId());
            sale.setQuantity(calculateQ(quantity3.getText()));
            sale.setQuantityRetour(calculateQ(quantityRetour3.getText()));
            sale.setPricePurchase(SaleSelected3.getPricePurchase());
            sale.setPrice_sale(Double.parseDouble(price_sale3.getText()));
            sale.setPriceTotal(sale.getPrice_sale() * (sale.getQuantity() - sale.getQuantityRetour()));
            sale.setBenefitSale((sale.getPrice_sale() - sale.getPricePurchase()) * (sale.getQuantity() - sale.getQuantityRetour()));
            Results.Rstls r = DbSaleAgent.updateSale(sale);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            if (r == Results.Rstls.DATA_UPDATED) {
                // update quantity goods
                updateSale(PurchaseSaleSelected3, SaleSelected3, SaleGoods3, sale, MontentTotal3,
                        remiseValue3, MontentTotal_remise3, LastDebt3, Debt_Montent3, SaleClient3, Payment3,
                        DebtR3, a);
                app(SaleFacture3, remiseValue3, MontentTotal_remise3, Payment3, DebtR3, SaleClient3);
                // clear 
                PurchaseSaleSelected3 = new Purchase();
                SaleSelected3 = new Sale();
                SaleGoods3 = new Goods();
                clearInputP(productT3, quantity3, quantityRest3,
                        quantityRetour3, priceTotal3, price_sale3, barcode3,
                        price_purchase3);
                refrechSale(SaleTable3, productC3, price_purchaseC3, quantityC3, price_saleC3, price_totelC3, benefitC3, quantityRC3, sale, "facture");
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }

        }
    }

    public boolean testapplyFacture(TextField Payment, Pane payment_err, TextField remiseValue, Pane remise_err) {
        boolean Bpayment = false, Bremise = false;
        if (Payment.getText().isEmpty()) {
            payment_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(Payment)) {
                payment_err.setStyle("-fx-border-color: transparent;");
                Bpayment = true;
            } else {
                payment_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        if (remiseValue.getText().isEmpty()) {
            remise_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(remiseValue)) {
                remise_err.setStyle("-fx-border-color: transparent;");
                Bremise = true;
            } else {
                remise_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        return Bpayment & Bremise;
    }

    private void app(FactureSale SaleFacture, TextField remiseValue, Label MontentTotal_remise, TextField Payment, Label DebtR, Client SaleClient) {
        if (selectedFSale.getId() == 0) {
            LocalDateTime lDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
            String formattedS = lDate.format(formatter);
            SaleFacture.setDate(formattedS);
        }
        SaleFacture.setRemise(Double.parseDouble(remiseValue.getText()));
        SaleFacture.setTotalAmount(Double.parseDouble(MontentTotal_remise.getText()));
        SaleFacture.setPayment(Double.parseDouble(Payment.getText()));
        SaleClient.setTotaleDebt(Double.parseDouble(DebtR.getText()));
        DbClientAgent.updateClient(SaleClient);
        DbFactureSaleAgent.applyFacturSale(SaleFacture);
    }

    @FXML
    private void apply(ActionEvent event) {
        if (testapplyFacture(Payment, payment_err, remiseValue, remise_err)) {
            if (SaleFacture.getTotalAmount() != 0 || SaleFacture.getPayment() != 0) {
                app(SaleFacture, remiseValue, MontentTotal_remise, Payment, DebtR, SaleClient);

                SaleClient = new Client(1);
                ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(SaleClient, "id");
                SaleClient = cl.get(0);
                ex = true;
                SaleFacture = new FactureSale();

                setpaneTransparent(price_sale_err, quantity_err, product_err);
                clearlabelsF(Payment, DebtR, remiseValue, LastDebt,
                        MontentTotal, Debt_Montent,
                        MontentTotal_remise);
                setclienttexts(clientTcombo, LastDebt, Debt_Montent, DebtR, SaleClient);
                clearInputP(productT, quantity, quantityRest,
                        quantityRetour, priceTotal, price_sale, barcode,
                        price_purchase);
                if (selectedFSale.getId() != 0) {
                    Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    home_stage.hide();
                }
                refrechSale(SaleTable, productC, price_purchaseC, quantityC, price_saleC, price_totelC, benefitC, quantityRC, new Sale(), "facture");
            } else {
                DbFactureSaleAgent.deleteFactureSale(SaleFacture);
                SaleFacture = new FactureSale();
                if (selectedFSale.getId() != 0) {
                    Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    home_stage.hide();
                }
            }
        }
    }

    @FXML
    private void apply2(ActionEvent event) {
        if (testapplyFacture(Payment2, payment_err2, remiseValue2, remise_err2)) {
            if (SaleFacture2.getTotalAmount() != 0 || SaleFacture2.getPayment() != 0) {
                app(SaleFacture2, remiseValue2, MontentTotal_remise2, Payment2, DebtR2, SaleClient2);
                SaleClient2 = new Client(1);
                ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(SaleClient2, "id");
                SaleClient2 = cl.get(0);
                ex2 = true;
                SaleFacture2 = new FactureSale();
                setpaneTransparent(price_sale_err2, quantity_err2, product_err2);

                clearlabelsF(Payment2, DebtR2, remiseValue2, LastDebt2,
                        MontentTotal2, Debt_Montent2,
                        MontentTotal_remise2);
                setclienttexts(clientTcombo2, LastDebt2, Debt_Montent2, DebtR2, SaleClient2);
                clearInputP(productT2, quantity2, quantityRest2,
                        quantityRetour2, priceTotal2, price_sale2, barcode2,
                        price_purchase2);
                refrechSale(SaleTable2, productC2, price_purchaseC2, quantityC2, price_saleC2, price_totelC2, benefitC2, quantityRC2, new Sale(), "facture");
            }

        }
    }

    @FXML
    private void apply3(ActionEvent event) {
        if (testapplyFacture(Payment3, payment_err3, remiseValue3, remise_err3)) {
            if (SaleFacture3.getTotalAmount() != 0 || SaleFacture3.getPayment() != 0) {
                app(SaleFacture3, remiseValue3, MontentTotal_remise3, Payment3, DebtR3, SaleClient3);

                SaleClient3 = new Client(1);
                ObservableList<Client> cl = (ObservableList<Client>) DbClientAgent.getClient(SaleClient3, "id");
                SaleClient3 = cl.get(0);
                ex3 = true;
                SaleFacture3 = new FactureSale();

                setpaneTransparent(price_sale_err3, quantity_err3, product_err3);

                clearlabelsF(Payment3, DebtR3, remiseValue3, LastDebt3,
                        MontentTotal3, Debt_Montent3,
                        MontentTotal_remise3);
                setclienttexts(clientTcombo3, LastDebt3, Debt_Montent3, DebtR3, SaleClient3);
                clearInputP(productT3, quantity3, quantityRest3,
                        quantityRetour3, priceTotal3, price_sale3, barcode3,
                        price_purchase3);
                refrechSale(SaleTable3, productC3, price_purchaseC3, quantityC3, price_saleC3, price_totelC3, benefitC3, quantityRC3, new Sale(), "facture");
            }
        }
    }

    @FXML
    private void calcule_montent(KeyEvent event) {
        calculateMontent(remiseValue, MontentTotal, MontentTotal_remise, Payment, LastDebt, Debt_Montent, DebtR, SaleClient);
    }

    @FXML
    private void calcule_montent2(KeyEvent event) {
        calculateMontent(remiseValue2, MontentTotal2, MontentTotal_remise2, Payment2, LastDebt2, Debt_Montent2, DebtR2, SaleClient2);
    }

    @FXML
    private void calcule_montent3(KeyEvent event) {
        calculateMontent(remiseValue3, MontentTotal3, MontentTotal_remise3, Payment3, LastDebt3, Debt_Montent3, DebtR3, SaleClient3);
    }

    private void calculateMontent(TextField remiseValue, Label MontentTotal, Label MontentTotal_remise, TextField Payment, Label LastDebt, Label Debt_Montent, Label DebtR, Client SaleClient) {
        if (!remiseValue.getText().isEmpty() && testdouble(remiseValue)) {
            double newMontent, newdebt, rdebt;
            if (selectedFSale.getId() != 0) {
                newMontent = Double.parseDouble(MontentTotal.getText()) - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(newMontent).setScale(3, RoundingMode.HALF_UP).toPlainString());
                if (SaleClient.getId() == 1) {
                    Payment.setText(new BigDecimal(newMontent).setScale(3, RoundingMode.HALF_UP).toPlainString());
                }
                newdebt = Double.parseDouble(LastDebt.getText()) - selectedFSale.getTotalAmount() + Double.parseDouble(MontentTotal_remise.getText());
                Debt_Montent.setText(new BigDecimal(newdebt).setScale(3, RoundingMode.HALF_UP).toPlainString());

                rdebt = newdebt + selectedFSale.getPayment() - Double.parseDouble(Payment.getText());
                DebtR.setText(new BigDecimal(rdebt).setScale(3, RoundingMode.HALF_UP).toPlainString());
            } else {
                newMontent = Double.parseDouble(MontentTotal.getText()) - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(newMontent).setScale(3, RoundingMode.HALF_UP).toPlainString());
                if (SaleClient.getId() == 1) {
                    Payment.setText(new BigDecimal(newMontent).setScale(3, RoundingMode.HALF_UP).toPlainString());
                }
                newdebt = Double.parseDouble(LastDebt.getText()) + Double.parseDouble(MontentTotal_remise.getText());
                Debt_Montent.setText(new BigDecimal(newdebt).setScale(3, RoundingMode.HALF_UP).toPlainString());

                rdebt = newdebt - Double.parseDouble(Payment.getText());
                DebtR.setText(new BigDecimal(rdebt).setScale(3, RoundingMode.HALF_UP).toPlainString());
            }
        }
    }

    @FXML
    private void calcule_rest(KeyEvent event) {
        calculate_rest(Payment, Debt_Montent, DebtR);
        if (SaleFacture.getTotalAmount() == 0 && SaleFacture.getPayment() == 0) {
            ex = true;
        }
    }

    @FXML
    private void calcule_rest2(KeyEvent event) {
        calculate_rest(Payment2, Debt_Montent2, DebtR2);
        if (SaleFacture2.getTotalAmount() == 0 && SaleFacture2.getPayment() == 0) {
            ex2 = true;
        }
    }

    @FXML
    private void calcule_rest3(KeyEvent event) {
        calculate_rest(Payment3, Debt_Montent3, DebtR3);
        if (SaleFacture2.getTotalAmount() == 0 && SaleFacture2.getPayment() == 0) {
            ex2 = true;
        }
    }

    private void calculate_rest(TextField Payment, Label Debt_Montent, Label DebtR) {
        double debtR;
        if (!Payment.getText().isEmpty() && testdouble(Payment)) {
            if (selectedFSale.getId() != 0) {
                debtR = Double.parseDouble(Debt_Montent.getText()) + selectedFSale.getPayment() - Double.parseDouble(Payment.getText());
            } else {
                debtR = Double.parseDouble(Debt_Montent.getText()) - Double.parseDouble(Payment.getText());
            }
            DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
        } else if (Payment.getText().isEmpty()) {
            debtR = Double.parseDouble(Debt_Montent.getText());
            DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
        }
    }

    @FXML
    private void calculePriceTotal(KeyEvent event) {
        calculatePriceTotal(quantity, price_sale, productT,
                priceTotal, SaleSelected, PurchaseSaleSelected,
                lastPurchaseSale, quantityRest, quantity_err);
    }

    @FXML
    private void calculePriceTotal2(KeyEvent event) {
        calculatePriceTotal(quantity2, price_sale2, productT2,
                priceTotal2, SaleSelected2, PurchaseSaleSelected2,
                lastPurchaseSale2, quantityRest2, quantity_err2);
    }

    @FXML
    private void calculePriceTotal3(KeyEvent event) {
        calculatePriceTotal(quantity3, price_sale3, productT3,
                priceTotal3, SaleSelected3, PurchaseSaleSelected3,
                lastPurchaseSale3, quantityRest3, quantity_err3);
    }

    private void calculatePriceTotal(TextField quantity, TextField price_sale, TextField productT,
            Label priceTotal, Sale SaleSelected, Purchase PurchaseSaleSelected,
            Purchase lastPurchaseSale, TextField quantityRest, Pane quantity_err) {
        double priceT;
        if (!quantity.getText().isEmpty() && !price_sale.getText().isEmpty() && !productT.getText().isEmpty()) {
            if (testdouble(quantity) && testdouble(price_sale)) {
                double q = calculateQ(quantity.getText());
                priceT = q * Double.parseDouble(price_sale.getText());
                priceTotal.setText(new BigDecimal(priceT).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double qRest;
                if (SaleSelected.getId() != 0) {
                    qRest = PurchaseSaleSelected.getQuantityRest() + SaleSelected.getQuantity() - q;
                } else {
                    qRest = lastPurchaseSale.getQuantityRest() - q;
                }
                quantityRest.setText(qRest + "");
                if (qRest >= 0) {
                    quantity_err.setStyle("-fx-border-color: transparent;");
                } else {
                    quantity_err.setStyle("-fx-border-color: #f70d1b;");
                }
            }
        }
    }

    @FXML
    private void hotkey(KeyEvent event) {
        switch (event.getCode()) {
            case F1:
                add();
                break;
            case DELETE:
                delete();
                break;
            case F2:
                update();
                break;
            case F12:
                ActionEvent e = null;
                apply(e);
                break;
            default:
                break;
        }
    }

    @FXML
    private void hotkey2(KeyEvent event) {
        switch (event.getCode()) {
            case F1:
                add2();
                break;
            case DELETE:
                delete2();
                break;
            case F2:
                update2();
                break;
            case F12:
                ActionEvent e = null;
                apply2(e);
                break;
            default:
                break;
        }
    }

    @FXML
    private void hotkey3(KeyEvent event) {
        switch (event.getCode()) {
            case F1:
                add3();
                break;
            case DELETE:
                delete3();
                break;
            case F2:
                update3();
                break;
            case F12:
                ActionEvent e = null;
                apply3(e);
                break;

            default:
                break;
        }
    }

    @FXML
    private void calcPriceTotal(ActionEvent event) {
        calculatePriceTotal(quantity, price_sale, productT,
                priceTotal, SaleSelected, PurchaseSaleSelected,
                lastPurchaseSale, quantityRest, quantity_err);
    }

    @FXML
    private void calcPriceTotal2(ActionEvent event) {
        calculatePriceTotal(quantity2, price_sale2, productT2,
                priceTotal2, SaleSelected2, PurchaseSaleSelected2,
                lastPurchaseSale2, quantityRest2, quantity_err2);
    }

    @FXML
    private void calcPriceTotal3(ActionEvent event) {
        calculatePriceTotal(quantity3, price_sale3, productT3,
                priceTotal3, SaleSelected3, PurchaseSaleSelected3,
                lastPurchaseSale3, quantityRest3, quantity_err3);
    }

    @FXML
    private void search_product(KeyEvent event) {
        refrechProducts(productT, SaleFacture, goodsTable, productSelectC, quantitySelectC);
    }

    @FXML
    private void search_product2(KeyEvent event) {
        refrechProducts(productT2, SaleFacture2, goodsTable2, productSelectC2, quantitySelectC2);
    }

    @FXML
    private void search_product3(KeyEvent event) {
        refrechProducts(productT3, SaleFacture3, goodsTable3, productSelectC3, quantitySelectC3);
    }

    private void initial_fav(int favselect, ListView<Label> Fav_listImage, ListView<Label> Fav_listImage1, FactureSale SaleFacture) {
        Fav_listImage.getItems().clear();
        Fav_listImage1.getItems().clear();
        int f;
        if (favselect % 2 == 0) {
            f = 2;
        } else {
            f = 1;
        }
        Goods g = new Goods();
        g.setName("");
        g.setId(SaleFacture.getId());
        ObservableList<Goods> pr = (ObservableList<Goods>) DbGoodsAgent.getGoods(g, "fav" + f);
        switch (favselect) {
            case 1:
                fav1list = pr;
                break;
            case 2:
                fav2list = pr;
                break;
            case 3:
                fav1list2 = pr;
                break;
            case 4:
                fav2list2 = pr;
                break;
            case 5:
                fav1list3 = pr;
                break;
            case 6:
                fav2list3 = pr;
                break;
            default:
                break;
        }
        pr.forEach((G) -> {
            Label imgL = new Label(G.getName() + " :" + G.getQuantity());
            imgL.setTextFill(Color.web("white"));
            imgL.setMaxWidth(160);
            imgL.setMaxHeight(90);
            imgL.setContentDisplay(ContentDisplay.TOP);
            ImageView imgv = new ImageView();
            imgv.setFitWidth(120);
            imgv.setFitHeight(80);
            if (G.getImgProduct() != null && !G.getImgProduct().isEmpty()) {
                Image image = new Image(G.getImgProduct());
                imgv.setImage(image);
                imgL.setGraphic(imgv);
            } else {
                imgv.setImage(null);
                imgL.setGraphic(imgv);
            }
            if (i < 20) {
                Fav_listImage.getItems().add(imgL);
            } else {
                Fav_listImage1.getItems().add(imgL);
            }
            i++;
        });
        i = 0;
    }

    @FXML
    private void Fav1_selectproduct(MouseEvent event) {

        if (Fav1_listImage.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods = fav1list.get(Fav1_listImage.getSelectionModel().getSelectedIndex());
            if (SaleGoods != null) {
                lastPurchaseSale.setIdProduct(SaleGoods.getId());
                Purchase_ListSale = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale, "goodsSale");
                lastPurchaseSale = Purchase_ListSale.get(0);
                SaleSelected = new Sale();
                productT.setText(SaleGoods.getName());
                if (lastPurchaseSale.getId() != 0) {
                    barcode.setText(lastPurchaseSale.getCode());
                    price_purchase.setItems(Purchase_ListSale);
                    priceTotal.setText(lastPurchaseSale.getPricePurchaseS());
                    price_sale.setText(lastPurchaseSale.getPriceSaleS());
                    quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
                } else {
                    barcode.setText("");
                    price_purchase.getItems().clear();
                    priceTotal.setText("0.00");
                    price_sale.setText("");
                }
                quantity.setText("1");
                if (event.getClickCount() == 2) {
                    add();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }

    }

    @FXML
    private void Fav2_selectproduct(MouseEvent event) {

        if (Fav2_listImage.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods = fav2list.get(Fav2_listImage.getSelectionModel().getSelectedIndex());
            if (SaleGoods != null) {
                lastPurchaseSale.setIdProduct(SaleGoods.getId());
                Purchase_ListSale = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale, "goodsSale");
                lastPurchaseSale = Purchase_ListSale.get(0);
                SaleSelected = new Sale();
                productT.setText(SaleGoods.getName());
                if (lastPurchaseSale.getId() != 0) {
                    barcode.setText(lastPurchaseSale.getCode());
                    price_purchase.setItems(Purchase_ListSale);
                    priceTotal.setText(lastPurchaseSale.getPricePurchaseS());
                    price_sale.setText(lastPurchaseSale.getPriceSaleS());
                    quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
                } else {
                    barcode.setText("");
                    price_purchase.getItems().clear();
                    priceTotal.setText("0.00");
                    price_sale.setText("");
                }
                quantity.setText("1");
                if (event.getClickCount() == 2) {
                    add();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }
    }

    @FXML
    private void Fav1_selectproduct2(MouseEvent event) {

        if (Fav1_listImage2.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods2 = fav1list2.get(Fav1_listImage2.getSelectionModel().getSelectedIndex());
            if (SaleGoods2 != null) {
                lastPurchaseSale2.setIdProduct(SaleGoods2.getId());
                Purchase_ListSale2 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale2, "goodsSale");
                lastPurchaseSale2 = Purchase_ListSale2.get(0);
                SaleSelected2 = new Sale();
                productT2.setText(SaleGoods2.getName());
                if (lastPurchaseSale2.getId() != 0) {
                    barcode2.setText(lastPurchaseSale2.getCode());
                    price_purchase2.setItems(Purchase_ListSale2);
                    priceTotal2.setText(lastPurchaseSale2.getPricePurchaseS());
                    price_sale2.setText(lastPurchaseSale2.getPriceSaleS());
                    quantityRest2.setText(lastPurchaseSale2.getQuantityRest() - 1 + "");
                } else {
                    barcode2.setText("");
                    price_purchase2.getItems().clear();
                    priceTotal2.setText("0.00");
                    price_sale2.setText("");
                }
                quantity2.setText("1");
                if (event.getClickCount() == 2) {
                    add2();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }
    }

    @FXML
    private void Fav2_selectproduct2(MouseEvent event) {

        if (Fav2_listImage2.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods2 = fav2list2.get(Fav2_listImage2.getSelectionModel().getSelectedIndex());
            if (SaleGoods2 != null) {
                lastPurchaseSale2.setIdProduct(SaleGoods2.getId());
                Purchase_ListSale2 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale2, "goodsSale");
                lastPurchaseSale2 = Purchase_ListSale2.get(0);
                SaleSelected2 = new Sale();
                productT2.setText(SaleGoods2.getName());
                if (lastPurchaseSale2.getId() != 0) {
                    barcode2.setText(lastPurchaseSale2.getCode());
                    price_purchase2.setItems(Purchase_ListSale2);
                    priceTotal2.setText(lastPurchaseSale2.getPricePurchaseS());
                    price_sale2.setText(lastPurchaseSale2.getPriceSaleS());
                    quantityRest2.setText(lastPurchaseSale2.getQuantityRest() - 1 + "");
                } else {
                    barcode2.setText("");
                    price_purchase2.getItems().clear();
                    priceTotal2.setText("0.00");
                    price_sale2.setText("");
                }
                quantity2.setText("1");
                if (event.getClickCount() == 2) {
                    add2();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }
    }

    @FXML
    private void Fav1_selectproduct3(MouseEvent event) {

        if (Fav1_listImage3.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods3 = fav1list3.get(Fav1_listImage3.getSelectionModel().getSelectedIndex());
            if (SaleGoods3 != null) {
                lastPurchaseSale3.setIdProduct(SaleGoods3.getId());
                Purchase_ListSale3 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale3, "goodsSale");
                lastPurchaseSale3 = Purchase_ListSale3.get(0);
                SaleSelected3 = new Sale();
                productT3.setText(SaleGoods3.getName());
                if (lastPurchaseSale3.getId() != 0) {
                    barcode3.setText(lastPurchaseSale.getCode());
                    price_purchase3.setItems(Purchase_ListSale3);
                    priceTotal3.setText(lastPurchaseSale3.getPricePurchaseS());
                    price_sale3.setText(lastPurchaseSale3.getPriceSaleS());
                    quantityRest3.setText(lastPurchaseSale3.getQuantityRest() - 1 + "");
                } else {
                    barcode3.setText("");
                    price_purchase3.getItems().clear();
                    priceTotal3.setText("0.00");
                    price_sale3.setText("");
                }
                quantity3.setText("1");
                if (event.getClickCount() == 2) {
                    add3();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }
    }

    @FXML
    private void Fav2_selectproduct3(MouseEvent event) {

        if (Fav2_listImage3.getSelectionModel().getSelectedIndex() >= 0) {
            SaleGoods3 = fav2list3.get(Fav2_listImage3.getSelectionModel().getSelectedIndex());
            if (SaleGoods3 != null) {
                lastPurchaseSale3.setIdProduct(SaleGoods3.getId());
                Purchase_ListSale3 = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchaseSale3, "goodsSale");
                lastPurchaseSale3 = Purchase_ListSale3.get(0);
                SaleSelected3 = new Sale();
                productT3.setText(SaleGoods3.getName());
                if (lastPurchaseSale3.getId() != 0) {
                    barcode3.setText(lastPurchaseSale.getCode());
                    price_purchase3.setItems(Purchase_ListSale3);
                    priceTotal3.setText(lastPurchaseSale3.getPricePurchaseS());
                    price_sale3.setText(lastPurchaseSale3.getPriceSaleS());
                    quantityRest3.setText(lastPurchaseSale3.getQuantityRest() - 1 + "");
                } else {
                    barcode3.setText("");
                    price_purchase3.getItems().clear();
                    priceTotal3.setText("0.00");
                    price_sale3.setText("");
                }
                quantity3.setText("1");
                if (event.getClickCount() == 2) {
                    add3();
                    initial_fav(2, Fav2_listImage, Fav2_listImage1, SaleFacture);
                    initial_fav(1, Fav1_listImage, Fav1_listImage1, SaleFacture);
                    initial_fav(4, Fav2_listImage2, Fav2_listImage21, SaleFacture2);
                    initial_fav(3, Fav1_listImage2, Fav1_listImage21, SaleFacture2);
                    initial_fav(6, Fav2_listImage3, Fav2_listImage31, SaleFacture3);
                    initial_fav(5, Fav1_listImage3, Fav1_listImage31, SaleFacture3);
                }
            }
        }
    }

    @FXML
    private void selectPricePurchase(ActionEvent event) {
        lastPurchaseSale = (Purchase) price_purchase.getSelectionModel().getSelectedItem();
        if (lastPurchaseSale != null) {
            productT.setText(lastPurchaseSale.getProduct());
            barcode.setText(lastPurchaseSale.getCode());
            price_purchase.setValue(lastPurchaseSale);
            priceTotal.setText(lastPurchaseSale.getPriceSaleS());
            price_sale.setText(lastPurchaseSale.getPriceSaleS());
            quantityRest.setText(lastPurchaseSale.getQuantityRest() - 1 + "");
        } else {
            productT.setText("");
            priceTotal.setText("0.00");
            price_sale.setText("0.00");
            quantityRest.setText("");
        }
        quantity.setText("1");
    }

    @FXML
    private void selectPricePurchase2(ActionEvent event) {
        lastPurchaseSale2 = (Purchase) price_purchase2.getSelectionModel().getSelectedItem();
        if (lastPurchaseSale2 != null) {
            productT2.setText(lastPurchaseSale2.getProduct());
            barcode2.setText(lastPurchaseSale2.getCode());
            priceTotal2.setText(lastPurchaseSale2.getPriceSaleS());
            price_sale2.setText(lastPurchaseSale2.getPriceSaleS());
            quantityRest2.setText(lastPurchaseSale2.getQuantityRest() - 1 + "");
        } else {
            productT2.setText("");
            priceTotal2.setText("0.00");
            price_sale2.setText("0.00");
            quantityRest2.setText("");
        }
        quantity2.setText("1");
    }

    @FXML
    private void selectPricePurchase3(ActionEvent event) {
        lastPurchaseSale3 = (Purchase) price_purchase3.getSelectionModel().getSelectedItem();
        if (lastPurchaseSale3 != null) {
            productT3.setText(lastPurchaseSale3.getProduct());
            barcode3.setText(lastPurchaseSale3.getCode());
            priceTotal3.setText(lastPurchaseSale3.getPriceSaleS());
            price_sale3.setText(lastPurchaseSale3.getPriceSaleS());
            quantityRest3.setText(lastPurchaseSale3.getQuantityRest() - 1 + "");
        } else {
            productT3.setText("");
            priceTotal3.setText("0.00");
            price_sale3.setText("0.00");
            quantityRest3.setText("");
        }
        quantity3.setText("1");
    }

}
