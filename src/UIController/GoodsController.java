/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.DbAgents.DbGoodsAgent;
import static BackEnd.DbAgents.DbGoodsAgent.setQ;
import BackEnd.DbAgents.DbPurchaseAgent;
import BackEnd.DbAgents.DbSaleAgent;
import static BackEnd.Functions.testint;
import BackEnd.Results;
import BackEnd.models.Goods;
import BackEnd.models.Purchase;
import BackEnd.models.Sale;
import static UIController.LoginController.loginUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class GoodsController implements Initializable {

    private static Goods G = new Goods();
    @FXML
    private Label quantity_err;
    @FXML
    private TableColumn quantityC;
    @FXML
    private Label goods_err;
    @FXML
    private TextField product;
    @FXML
    private JFXTextField q;
    @FXML
    private TableView<?> ProductTable;
    @FXML
    private TableColumn<?, ?> productC;
    @FXML
    private TableColumn<?, ?> quantityAlertC;
    @FXML
    private JFXButton update;
    @FXML
    private TableColumn<?, ?> countPurchaseC;
    @FXML
    private TableColumn<?, ?> countSaleC;
    @FXML
    private TableView<Purchase> PurchasesTable;
    @FXML
    private TableColumn<?, ?> quantityPurchaseC;
    @FXML
    private TableColumn<?, ?> quantityRestC;
    @FXML
    private TableView<Sale> SalesTable;
    @FXML
    private TableColumn<?, ?> quantitySaleC;
    @FXML
    private TableColumn<?, ?> quantityRetourC;
    @FXML
    private ImageView img;

    private static File file;
    @FXML
    private JFXComboBox<String> favcombo;

    private static ObservableList<String> fav = FXCollections.observableArrayList("جميع السلع","اختصار 1","اختصار 2");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        favcombo.setItems(fav);
        favcombo.setValue("جميع السلع");
        if (loginUser.getRole().equals("simple")) {
            update.setVisible(false);
        }
        try {
            refrechGoods(ProductTable, productC, quantityC, quantityAlertC, countPurchaseC, countSaleC, new Goods(), "");
        } catch (SQLException ex) {
            Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void refrechGoods(TableView table, TableColumn Column1, TableColumn Column2,
            TableColumn Column3, TableColumn Column4,
            TableColumn Column5, Goods Gd, String s)
            throws SQLException {
        ObservableList<Goods> pr = (ObservableList<Goods>) DbGoodsAgent.getGoods(Gd, s);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("quantityAlert")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("countPurchase")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("countSale")
        );
        table.setItems(pr);
    }

    @FXML
    private void add(ActionEvent event) {
        if (testinput()) {
            Goods goods = new Goods();
            goods.setName(product.getText());
            if (file == null) {
                goods.setImgProduct("");
            } else {
                goods.setImgProduct("/img/petit/"+file.getName());
            }
            if (favcombo.getValue().isEmpty() || favcombo.getValue().equals("جميع السلع")) {
                goods.setFav(0);
            } else if (favcombo.getValue().equals("اختصار 1")) {
                goods.setFav(1);
            } else if (favcombo.getValue().equals("اختصار 2")) {
                goods.setFav(2);
            }
            goods.setQuantityAlert(Integer.parseInt(q.getText()));
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information Dialog");
            a.setHeaderText(null);
            Results.Rstls r = DbGoodsAgent.addGoods(goods);
            if (r == Results.Rstls.DATA_NOT_INSERTED) {
                a.setContentText(r + "");
                a.showAndWait();
            } else {
                try {
                    refrechGoods(ProductTable, productC, quantityC, quantityAlertC, countPurchaseC, countSaleC, new Goods(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean testinput() {
        boolean Bproduct = false, Bquantity = false;
        if (product.getText().isEmpty()) {
            goods_err.setText("Le produit est vide");
            goods_err.setVisible(true);
        } else {
            goods_err.setVisible(false);
            Bproduct = true;
        }
        if (q.getText().isEmpty()) {
            quantity_err.setText("La quantité est vide");
            quantity_err.setVisible(true);
        } else {
            if (testint(q)) {
                quantity_err.setVisible(false);
                Bquantity = true;
            } else {
                quantity_err.setText("Entrer des nombres");
                quantity_err.setVisible(true);
            }
        }

        return Bproduct && Bquantity;

    }

    @FXML
    private void selectGoods(MouseEvent event) {
        G = (Goods) ProductTable.getSelectionModel().getSelectedItem();
        if (G != null) {
            if (event.getClickCount() == 2) {
                setQ(G);
                try {
                    refrechGoods(ProductTable, productC, quantityC, quantityAlertC, countPurchaseC, countSaleC, new Goods(), "");
                } catch (SQLException ex) {
                    Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            product.setText(G.getName());
            if (G.getImgProduct() != null && !G.getImgProduct().isEmpty()) {
                Image image = new Image(G.getImgProduct());
                img.setImage(image);
            } else {
                img.setImage(null);
            }
            favcombo.setValue(fav.get(G.getFav()));
            q.setText(Integer.toString(G.getQuantityAlert()));
            Purchase p = new Purchase();
            p.setIdProduct(G.getId());
            ObservableList<Purchase> plist = (ObservableList<Purchase>) DbPurchaseAgent.getPurchase(p, "allPurchaseIdproduct");
            quantityPurchaseC.setCellValueFactory(
                    new PropertyValueFactory<>("quantity")
            );
            quantityRestC.setCellValueFactory(
                    new PropertyValueFactory<>("quantityRest")
            );
            PurchasesTable.setItems(plist);
            Sale s = new Sale();
            s.setIdProduct(G.getId());
            ObservableList<Sale> slist = (ObservableList<Sale>) DbSaleAgent.getSales(s, "allSaleIdproduct");
            quantitySaleC.setCellValueFactory(
                    new PropertyValueFactory<>("quantity")
            );
            quantityRetourC.setCellValueFactory(
                    new PropertyValueFactory<>("quantityRetour")
            );
            SalesTable.setItems(slist);
        }
    }

    /*private void delete(ActionEvent event) {
        if (G.getId() != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suprimer");
            alert.setHeaderText("suprimer confirmation");
            alert.setContentText("Vérifier avant de supprimer");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Results.Rstls rs = DbGoodsAgent.deleteGoods(new Goods(G.getId()));
                if (rs == Results.Rstls.DATA_NOT_DELETED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(rs + "");
                    a.showAndWait();
                } else {
                    G = new Goods();
                    try {
                        refrechGoods(ProductTable, productC, quantityC, price_purchaseC, price_saleC, new Goods(), "");
                    } catch (SQLException ex) {
                        Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }*/
    @FXML
    private void update(ActionEvent event) {
        if (G.getId() != 0 && testinput()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modifier");
            alert.setHeaderText("Modifier confirmation");
            alert.setContentText("Vérifier avant de modifier");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                Goods goods = new Goods();
                goods.setId(G.getId());
                goods.setName(product.getText());
                if (file == null) {
                    goods.setImgProduct("");
                } else {
                    goods.setImgProduct("/img/article/petit/"+file.getName());
                }
                if (favcombo.getValue().isEmpty() || favcombo.getValue().equals("جميع السلع")) {
                    goods.setFav(0);
                } else if (favcombo.getValue().equals("اختصار 1")) {
                    goods.setFav(1);
                } else if (favcombo.getValue().equals("اختصار 2")) {
                    goods.setFav(2);
                }
                goods.setQuantityAlert(Integer.parseInt(q.getText()));
                Results.Rstls rs = DbGoodsAgent.updateGoods(goods);
                if (rs == Results.Rstls.DATA_NOT_UPDATED) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setHeaderText(rs + "");
                    a.showAndWait();
                } else {
                    G = new Goods();
                    try {
                        refrechGoods(ProductTable, productC, quantityC, quantityAlertC, countPurchaseC, countSaleC, new Goods(), "");
                    } catch (SQLException ex) {
                        Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    @FXML
    private void search(KeyEvent event) {
        Goods g = new Goods();
        String s = "";
        if (!product.getText().isEmpty()) {
            g.setName(product.getText());
            s = "name";
        }
        try {
            refrechGoods(ProductTable, productC, quantityC, quantityAlertC, countPurchaseC, countSaleC, g, s);
        } catch (SQLException ex) {
            Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectimage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            img.setImage(image);
        }
    }

    @FXML
    private void deleteimage(ActionEvent event) {
        img.setImage(null);
        file = null;
    }
}
