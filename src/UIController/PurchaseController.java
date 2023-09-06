/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbFactureAgent;
import static BackEnd.DbAgents.DbFactureAgent.*;
import static BackEnd.DbAgents.DbFactureAgent.generatedCodeFacturePurchase;
import BackEnd.DbAgents.DbGoodsAgent;
import BackEnd.DbAgents.DbProviderAgent;
import static BackEnd.DbAgents.DbProviderAgent.updateProvider;
import BackEnd.DbAgents.DbPurchaseAgent;
import static BackEnd.DbAgents.DbPurchaseAgent.generatedCodeBarPurchase;
import static BackEnd.Functions.*;
import BackEnd.Results;
import BackEnd.models.Facture;
import BackEnd.models.Goods;
import BackEnd.models.Provider;
import BackEnd.models.Purchase;
import static UIController.Facture_PurchaseController.selectedFactureP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static stock.Stock.loginstage;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class PurchaseController implements Initializable {

    @FXML
    private TableView<Purchase> PurchaseTable;
    @FXML
    private TableColumn<?, ?> price_totelC;
    @FXML
    private TextField quantity;
    @FXML
    private TableColumn<?, ?> quantityC;
    @FXML
    private Pane quantity_err;
    @FXML
    private Label FactureDate;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton update;
    @FXML
    private TextField price_sale_1;
    @FXML
    private TextField codeT;
    @FXML
    private JFXButton generateCode;
    @FXML
    private Label DebtR;
    @FXML
    private Pane price_sale_err;
    @FXML
    private Pane price_purchase_err;
    @FXML
    private TextField price_purchase;
    @FXML
    private Pane product_err;
    @FXML
    private TextField product;
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
    private JFXButton newFacture;
    @FXML
    private Label Debt_Montent;
    @FXML
    private TableColumn<?, ?> productC;
    @FXML
    private TableColumn<?, ?> price_purchaseC;
    @FXML
    private TableColumn<?, ?> price_sale1C;
    @FXML
    private Label code_err;
    @FXML
    private Label provider_err;

    public static boolean PurchaseGoods = false;
    public static Facture PrchsFacture = new Facture();
    private Purchase prchsSelected = new Purchase();
    public static Goods PrchsGoods = new Goods();
    public static Provider PrchsProvider = new Provider();
    public static Purchase lastPurchase = new Purchase();
    @FXML
    private Pane payment_err;
    @FXML
    private Label MontentTotal_remise;
    @FXML
    private JFXTextField remiseValue;
    @FXML
    private Pane remise_err;
    @FXML
    private TableColumn<?, ?> quantityRC;
    @FXML
    private JFXTextField barcode;
    @FXML
    private JFXButton generateCodeBar;
    @FXML
    private HBox barcode_err;
    @FXML
    private TableColumn<?, ?> barcodeC;
    @FXML
    private AnchorPane main;
    @FXML
    private TableView<Goods> goodsTable;
    @FXML
    private TableColumn<?, ?> productSelectC;
    @FXML
    private TableColumn<?, ?> quantitySelectC;
    @FXML
    private JFXComboBox<Provider> providerTcombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (selectedFactureP.getId() != 0) {
            FactureDate.setText(selectedFactureP.getDate());
            codeT.setText(selectedFactureP.getCode());
            String mtotal = new BigDecimal(selectedFactureP.getTotalAmount() + selectedFactureP.getRemise()).setScale(2, RoundingMode.HALF_UP).toPlainString();
            MontentTotal.setText(mtotal);
            remiseValue.setText(selectedFactureP.getRemiseS());
            MontentTotal_remise.setText(selectedFactureP.getTotalAmountS());
            Payment.setText(selectedFactureP.getPaymentS());
            Provider p = new Provider();
            p.setId(selectedFactureP.getIdProvider());
            ObservableList<Provider> provider = (ObservableList<Provider>) DbProviderAgent.getProvider(p, "id");
            providerTcombo.setItems(provider);
            PrchsProvider = provider.get(0);
            LastDebt.setText(provider.get(0).getDebtS());
            Debt_Montent.setText(provider.get(0).getDebtS());
            DebtR.setText(provider.get(0).getDebtS());
            PrchsFacture = selectedFactureP;
            setdisable(true, false);
        } else {
            PrchsFacture = new Facture();
            PrchsProvider = new Provider();
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = localDate.format(formatter);
            FactureDate.setText(formattedString);
            ObservableList<Provider> listprovider = (ObservableList<Provider>) DbProviderAgent.getProvider(new Provider(), "");
            providerTcombo.setItems(listprovider);
        }
        refrechPurchase();
        refrechProducts();

    }

    public void refrechPurchase() {
        Purchase newP = new Purchase();
        newP.setIdFacture(PrchsFacture.getId());
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
        price_sale1C.setCellValueFactory(
                new PropertyValueFactory<>("priceSaleS")
        );
        PurchaseTable.setItems(pr);
    }

    public void refrechProducts() {
        Goods g = new Goods();
        String s;
        g.setName(product.getText());
        g.setId(PrchsFacture.getId());
        // id-goods = id-facture for search
        s = "goods_not_in_purchase";
        ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(g, s);
        goodsTable.setItems(goodslist);
        productSelectC.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantitySelectC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    private void search_product(KeyEvent event) {
        refrechProducts();
    }

    @FXML
    private void select_Product(MouseEvent event) {
        Goods G = (Goods) goodsTable.getSelectionModel().getSelectedItem();
        if (G != null) {
            PrchsGoods = G;
            lastPurchase.setIdProduct(G.getId());
            ObservableList<Purchase> pr = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(lastPurchase, "goods");
            if (!pr.isEmpty()) {
                lastPurchase = pr.get(0);
            } else {
                lastPurchase = new Purchase();
            }
            prchsSelected = new Purchase();
            product.setText(PrchsGoods.getName());
            if (lastPurchase.getId() != 0) {
                barcode.setText(lastPurchase.getCode());
                price_purchase.setText(lastPurchase.getPricePurchaseS());
                priceTotal.setText(lastPurchase.getPricePurchaseS());
                price_sale_1.setText(lastPurchase.getPriceSaleS());
            } else {
                barcode.setText("");
                price_purchase.setText("0.00");
                priceTotal.setText("0.00");
                price_sale_1.setText("0.00");
            }
            quantity.setText("1");
            if (event.getClickCount() == 2) {
                add();
            }
        }
    }

    public boolean testinputFacture() {
        boolean Bcode = false, Bprovider = false;
        if (codeT.getText().isEmpty()) {
            code_err.setText("Le code est vide");
            code_err.setVisible(true);
        } else {
            code_err.setVisible(false);
            Bcode = true;
        }
        if (providerTcombo.getValue().equals("")) {
            provider_err.setText("Le Fournisseur est vide");
            provider_err.setVisible(true);
        } else {
            provider_err.setVisible(false);
            Bprovider = true;
        }
        return Bcode && Bprovider;
    }

    @FXML
    private void generateCode(ActionEvent event) {
        codeT.setText(generatedCodeFacturePurchase());
    }


    @FXML
    private void newFacture(ActionEvent event) {
        if (testinputFacture()) {
            UIController.SaleController.ex = false;
            PrchsFacture.setCode(codeT.getText());
            PrchsFacture.setIdProvider(PrchsProvider.getId());
            LocalDateTime lDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
            String formattedS = lDate.format(formatter);
            PrchsFacture.setDate(formattedS);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbFactureAgent.addFactur(PrchsFacture);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            } else {
                ObservableList<Facture> listF = (ObservableList<Facture>) DbFactureAgent.getFactures(PrchsFacture, "code");
                PrchsFacture = listF.get(0);
                setdisable(true, false);

            }
        }
    }

    public void setdisable(boolean a, boolean b) {
        newFacture.setDisable(a);
        generateCode.setDisable(a);
        codeT.setDisable(a);
        providerTcombo.setDisable(a);
        //
        remiseValue.setDisable(b);
        Payment.setDisable(b);
        product_err.setDisable(b);
        quantity_err.setDisable(b);
        price_purchase_err.setDisable(b);
        barcode_err.setDisable(b);
        price_sale_err.setDisable(b);
        applyFacture.setDisable(b);
        add.setDisable(b);
        update.setDisable(b);
        delete.setDisable(b);
        PurchaseTable.setDisable(b);
    }

    public boolean testinput() {
        boolean Bproduct = false, Bcodebar = false, Bquantity = false, BpriceSale = false, BpricePurchase = false;
        if (product.getText().isEmpty()) {
            product_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            product_err.setStyle("-fx-border-color: transparent;");
            Bproduct = true;
        }
        if (barcode.getText().isEmpty()) {
            barcode_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            barcode_err.setStyle("-fx-border-color: transparent;");
            Bcodebar = true;
        }
        if (quantity.getText().isEmpty()) {
            quantity_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(quantity)) {
                if (selectedFactureP.getId() != 0) {
                    if ((calculateQ(quantity.getText()) - prchsSelected.getQuantity() + prchsSelected.getQuantityRest()) >= 0) {
                        quantity_err.setStyle("-fx-border-color: transparent;");
                        Bquantity = true;
                    } else {
                        quantity_err.setStyle("-fx-border-color: #f70d1b;");
                    }
                } else {
                    quantity_err.setStyle("-fx-border-color: transparent;");
                    Bquantity = true;
                }

            } else {
                quantity_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        if (price_purchase.getText().isEmpty()) {
            price_purchase_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(price_purchase)) {
                price_purchase_err.setStyle("-fx-border-color: transparent;");
                BpricePurchase = true;
            } else {
                price_purchase_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        if (price_sale_1.getText().isEmpty()) {
            price_sale_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(price_sale_1)) {
                price_sale_err.setStyle("-fx-border-color: transparent;");
                BpriceSale = true;
            } else {
                price_sale_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }

        return Bproduct && Bquantity && Bcodebar && BpricePurchase && BpriceSale;

    }

    public void clearInputP() {
        product.setText("");
        barcode.setText("");
        quantity.setText("1");
        price_purchase.setText("0.00");
        priceTotal.setText("0.00");
        price_sale_1.setText("");
    }

    public void setpaneTransparent() {
        price_sale_err.setStyle("-fx-border-color: transparent;");
        quantity_err.setStyle("-fx-border-color: transparent;");
        product_err.setStyle("-fx-border-color: transparent;");
    }

    public void clearlabelsF() {
        DebtR.setText("0.00");
        LastDebt.setText("0.00");
        MontentTotal.setText("0.00");
        Debt_Montent.setText("0.00");
        Payment.setText("0");
        remiseValue.setText("0");
        MontentTotal_remise.setText("0.00");
        providerTcombo.setValue(new Provider());
        codeT.setText("");
    }

    @FXML
    private void generateCodeBar(ActionEvent event) throws IOException {
        barcode.setText(generatedCodeBarPurchase());
    }

    @FXML
    private void add() {
        if (testinput() && PrchsGoods.getId() != 0) {
            Purchase prchs = new Purchase();
            prchs.setIdFacture(PrchsFacture.getId());
            prchs.setIdProduct(PrchsGoods.getId());
            prchs.setCode(barcode.getText());
            prchs.setPricePurchase(calculatePP(price_purchase.getText()));
            prchs.setPriceSale(Double.parseDouble(price_sale_1.getText()));
            prchs.setQuantity(calculateQ(quantity.getText()));
            prchs.setQuantityRest(prchs.getQuantity());
            prchs.setPriceTotal(prchs.getPricePurchase() * prchs.getQuantity());
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            Results.Rstls r = DbPurchaseAgent.addPurchase(prchs);
            if (r == Results.Rstls.DATA_INSERTED) {
                double n = PrchsGoods.getQuantity() + prchs.getQuantity();
                PrchsGoods.setQuantity(n);
                n = PrchsGoods.getCountPurchase() + 1;
                PrchsGoods.setCountPurchase(n);
                DbGoodsAgent.updateQuantityGoods(PrchsGoods);
                PrchsGoods = new Goods();
                double MTotal = Double.parseDouble(MontentTotal.getText());
                MTotal = MTotal + (prchs.getPriceTotal());
                MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                MTotal = MTotal - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double debt_Montent;
                if (selectedFactureP.getId() != 0) {
                    debt_Montent = MTotal - selectedFactureP.getTotalAmount() + Double.parseDouble(LastDebt.getText());
                } else {
                    debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
                }
                Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double debtR;
                if (selectedFactureP.getId() != 0) {
                    debtR = debt_Montent + selectedFactureP.getPayment() - Double.parseDouble(Payment.getText());
                } else {
                    debtR = debt_Montent - Double.parseDouble(Payment.getText());
                }
                DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
                app();
                //barcodeImage.setVisible(false);
                prchsSelected = new Purchase();
                PrchsGoods = new Goods();
                clearInputP();
                refrechPurchase();
                refrechProducts();
            } else {
                a.setHeaderText(r + "");
                a.showAndWait();
            }

        }
    }

    @FXML
    private void delete() {
        if (prchsSelected.getId() != 0) {
            if (prchsSelected.getQuantity() <= prchsSelected.getQuantityRest()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("suprimer");
                alert.setHeaderText("suprimer confirmation");
                alert.setContentText("Vérifier avant de supprimer");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    Results.Rstls r = DbPurchaseAgent.deletePurchase(new Purchase(prchsSelected.getId()));
                    if (r == Results.Rstls.DATA_NOT_DELETED) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Information Dialog");
                        a.setHeaderText(r + "");
                        a.showAndWait();
                    } else {
                        // update quantity goods
                        double n = PrchsGoods.getQuantity() - prchsSelected.getQuantity();
                        PrchsGoods.setQuantity(n);
                        n = PrchsGoods.getCountPurchase() - 1;
                        PrchsGoods.setCountPurchase(n);
                        DbGoodsAgent.updateQuantityGoods(PrchsGoods);
                        double MTotal = Double.parseDouble(MontentTotal.getText());
                        MTotal = MTotal - prchsSelected.getPriceTotal();
                        // update labels
                        MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                        MTotal = MTotal - Double.parseDouble(remiseValue.getText());
                        MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                        double debt_Montent;
                        if (selectedFactureP.getId() != 0) {
                            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText()) - selectedFactureP.getTotalAmount();
                        } else {
                            debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
                        }
                        Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
                        double debtR;
                        if (selectedFactureP.getId() != 0) {
                            debtR = debt_Montent + selectedFactureP.getPayment() - Double.parseDouble(Payment.getText());
                        } else {
                            debtR = debt_Montent - Double.parseDouble(Payment.getText());
                        }
                        DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
                        app();
                        // clear
                        prchsSelected = new Purchase();
                        PrchsGoods = new Goods();
                        clearInputP();
                        refrechPurchase();
                        refrechProducts();
                    }
                }
            }

        }
    }

    @FXML
    private void update() {
        if (prchsSelected.getId() != 0 && testinput()) {
            Purchase prchs = new Purchase();
            prchs.setId(prchsSelected.getId());
            prchs.setIdFacture(PrchsFacture.getId());
            prchs.setIdProduct(PrchsGoods.getId());
            prchs.setCode(barcode.getText());
            prchs.setQuantity(calculateQ(quantity.getText()));
            prchs.setQuantityRest(prchs.getQuantity() - prchsSelected.getQuantity() + prchsSelected.getQuantityRest());
            prchs.setPricePurchase(calculatePP(price_purchase.getText()));
            prchs.setPriceSale(Double.parseDouble(price_sale_1.getText()));
            prchs.setPriceTotal(prchs.getPricePurchase() * prchs.getQuantity());
            Results.Rstls r = DbPurchaseAgent.updatePurchase(prchs);
            if (r == Results.Rstls.DATA_UPDATED) {

                // update quantity goods
                double n = PrchsGoods.getQuantity() - prchsSelected.getQuantity() + prchs.getQuantity();
                PrchsGoods.setQuantity(n);
                DbGoodsAgent.updateQuantityGoods(PrchsGoods);

                // update labels
                double MTotal = Double.parseDouble(MontentTotal.getText());
                MTotal = MTotal - prchsSelected.getPriceTotal() + prchs.getPriceTotal();
                MontentTotal.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                MTotal = MTotal - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(MTotal).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double debt_Montent;
                if (selectedFactureP.getId() != 0) {
                    debt_Montent = MTotal + Double.parseDouble(LastDebt.getText()) - selectedFactureP.getTotalAmount();
                } else {
                    debt_Montent = MTotal + Double.parseDouble(LastDebt.getText());
                }
                Debt_Montent.setText(new BigDecimal(debt_Montent).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double debtR;
                if (selectedFactureP.getId() != 0) {
                    debtR = debt_Montent + selectedFactureP.getPayment() - Double.parseDouble(Payment.getText());
                } else {
                    debtR = debt_Montent - Double.parseDouble(Payment.getText());
                }
                DebtR.setText(new BigDecimal(debtR).setScale(2, RoundingMode.HALF_UP).toPlainString());
                app();
                // clear 
                prchsSelected = new Purchase();
                PrchsGoods = new Goods();
                clearInputP();
                refrechPurchase();
                refrechProducts();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Information Dialog");
                a.setHeaderText(r + "");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void selectPurchase(MouseEvent event) {
        prchsSelected = (Purchase) PurchaseTable.getSelectionModel().getSelectedItem();
        if (prchsSelected != null) {
            PrchsGoods.setId(prchsSelected.getIdProduct());
            ObservableList<Goods> goodslist = (ObservableList<Goods>) DbGoodsAgent.getGoods(PrchsGoods, "id");
            PrchsGoods = goodslist.get(0);
            product.setText(prchsSelected.getProduct());
            barcode.setText(prchsSelected.getCode());
            quantity.setText(Double.toString(prchsSelected.getQuantity()));
            price_purchase.setText(prchsSelected.getPricePurchaseS());
            priceTotal.setText(prchsSelected.getPriceTotalS());
            price_sale_1.setText(prchsSelected.getPriceSaleS());
        }
    }

    public boolean testapplyFacture() {
        boolean Bpayment = false, Bremise = false;
        if (Payment.getText().isEmpty()) {
            payment_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(Payment)) {
                payment_err.setStyle("-fx-border-color: #1dbf1d;");
                Bpayment = true;
            } else {
                payment_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        if (remiseValue.getText().isEmpty()) {
            remise_err.setStyle("-fx-border-color: #f70d1b;");
        } else {
            if (testdouble(remiseValue)) {
                remise_err.setStyle("-fx-border-color: #1dbf1d;");
                Bremise = true;
            } else {
                remise_err.setStyle("-fx-border-color: #f70d1b;");
            }
        }
        return Bpayment & Bremise;
    }

    private void app() {
        if (selectedFactureP.getId() == 0) {
            LocalDateTime lDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
            String formattedS = lDate.format(formatter);
            PrchsFacture.setDate(formattedS);
        }
        PrchsFacture.setRemise(Double.parseDouble(remiseValue.getText()));
        PrchsFacture.setTotalAmount(Double.parseDouble(MontentTotal_remise.getText()));
        PrchsFacture.setPayment(Double.parseDouble(Payment.getText()));
        PrchsProvider.setDebt(Double.parseDouble(DebtR.getText()));
        updateProvider(PrchsProvider);
        applyFactur(PrchsFacture);
    }

    @FXML
    private void apply(ActionEvent event) {

        if (testapplyFacture()) {
            UIController.SaleController.ex = true;
            if (Double.parseDouble(MontentTotal.getText()) == 0 && Double.parseDouble(Payment.getText()) == 0) {
                deleteFacture(PrchsFacture);
            }
            app();
            PrchsProvider = new Provider();
            PrchsFacture = new Facture();
            clearlabelsF();
            clearInputP();
            setpaneTransparent();
            if (selectedFactureP.getId() != 0) {
                Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                home_stage.hide();
            }
            setdisable(false, true);
            refrechPurchase();
            refrechProducts();
        }
    }

    @FXML
    private void calculePriceTotal(KeyEvent event) {
        calculatePriceTotal();
    }

    private void calculatePriceTotal() {
        double priceT;
        if (!quantity.getText().isEmpty() && !price_purchase.getText().isEmpty()) {
            if (testdouble(quantity) && testdouble(price_purchase)) {
                double q = calculateQ(quantity.getText());
                double pp = calculatePP(price_purchase.getText());
                priceT = q * pp;
                priceTotal.setText(new BigDecimal(priceT).setScale(3, RoundingMode.HALF_UP).toPlainString());
            }
        }
    }

    @FXML
    private void calcule_rest(KeyEvent event) {
        calculate_rest();
    }

    private void calculate_rest() {
        double debtR;
        if (!Payment.getText().isEmpty() && testdouble(Payment)) {
            if (selectedFactureP.getId() != 0) {
                debtR = Double.parseDouble(Debt_Montent.getText()) + selectedFactureP.getPayment() - Double.parseDouble(Payment.getText());
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
    private void calcule_montent(KeyEvent event) {
        calculateMontent();
    }

    private void calculateMontent() {
        if (!remiseValue.getText().isEmpty() && testdouble(remiseValue)) {
            if (selectedFactureP.getId() != 0) {
                double newMontent = Double.parseDouble(MontentTotal.getText()) - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(newMontent).setScale(2, RoundingMode.HALF_UP).toPlainString());

                double newdebt = Double.parseDouble(LastDebt.getText()) - selectedFactureP.getTotalAmount() + Double.parseDouble(MontentTotal_remise.getText());
                Debt_Montent.setText(new BigDecimal(newdebt).setScale(2, RoundingMode.HALF_UP).toPlainString());

                double rdebt = newdebt + selectedFactureP.getPayment() - Double.parseDouble(Payment.getText());
                DebtR.setText(new BigDecimal(rdebt).setScale(2, RoundingMode.HALF_UP).toPlainString());
            } else {
                double newMontent = Double.parseDouble(MontentTotal.getText()) - Double.parseDouble(remiseValue.getText());
                MontentTotal_remise.setText(new BigDecimal(newMontent).setScale(2, RoundingMode.HALF_UP).toPlainString());

                double newdebt = Double.parseDouble(LastDebt.getText()) + Double.parseDouble(MontentTotal_remise.getText());
                Debt_Montent.setText(new BigDecimal(newdebt).setScale(2, RoundingMode.HALF_UP).toPlainString());

                double rdebt = newdebt - Double.parseDouble(Payment.getText());
                DebtR.setText(new BigDecimal(rdebt).setScale(2, RoundingMode.HALF_UP).toPlainString());
            }

        }
    }

    @FXML
    private void lastPurchase(ActionEvent event) throws IOException {
        Purchase p = new Purchase();
        p.setCode(barcode.getText());
        ObservableList<Purchase> pr = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(p, "lastPcode");
        if (!pr.isEmpty()) {
            lastPurchase = pr.get(0);
            quantity.setText("1");
            product.setText(lastPurchase.getProduct());
            PrchsGoods.setId(lastPurchase.getIdProduct());
            price_purchase.setText(lastPurchase.getPricePurchaseS());
            priceTotal.setText(lastPurchase.getPricePurchaseS());
            price_sale_1.setText(lastPurchase.getPriceSaleS());
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
            default:
                break;
        }
    }

    private void calculate(MouseEvent event) {
        calculatePriceTotal();
        calculateMontent();
        calculate_rest();
    }

    @FXML
    private void generateBarCode(KeyEvent event) {

    }

    @FXML
    private void selectProvider(ActionEvent event) {
        Provider p = providerTcombo.getSelectionModel().getSelectedItem();
        if (p != null) {
            PrchsProvider = p;
            providerTcombo.setValue(PrchsProvider);
            LastDebt.setText(PrchsProvider.getDebtS());
            Debt_Montent.setText(PrchsProvider.getDebtS());
            DebtR.setText(PrchsProvider.getDebtS());
        }

    }

}
