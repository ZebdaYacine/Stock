/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIController;

import BackEnd.models.Purchase;
import static UIController.SaleController.lastPurchaseSale;
import static UIController.SaleController.lastPurchaseSale2;
import static UIController.SaleController.lastPurchaseSale3;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static UIController.SaleController.Purchase_ListSale;
import static UIController.SaleController.Purchase_ListSale2;
import static UIController.SaleController.Purchase_ListSale3;
import static UIController.SaleController.clientSelect;

/**
 * FXML Controller class
 *
 * @author kadri
 */
public class SelectPricePurchaseController implements Initializable {

    @FXML
    private TableView<Purchase> SelectpriceT;
    @FXML
    private TableColumn<?, ?> pricePurchaseC;
    @FXML
    private TableColumn<?, ?> quantityRC;
    @FXML
    private TableColumn<?, ?> priceSaleC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        switch (clientSelect) {
            case 2:
                SelectpriceT.setItems(Purchase_ListSale2);
                break;
            case 3:
                SelectpriceT.setItems(Purchase_ListSale3);
                break;
            default:
                SelectpriceT.setItems(Purchase_ListSale);
                break;
        }

        pricePurchaseC.setCellValueFactory(new PropertyValueFactory<>("PricePurchaseS"));
        quantityRC.setCellValueFactory(new PropertyValueFactory<>("quantityRest"));
        priceSaleC.setCellValueFactory(new PropertyValueFactory<>("priceSaleS"));
    }

    @FXML
    private void selectprice(MouseEvent event) {
        Purchase P = (Purchase) SelectpriceT.getSelectionModel().getSelectedItem();
        if (P != null) {
            switch (clientSelect) {
                case 2:
                    lastPurchaseSale2 = P;
                    break;
                case 3:
                    lastPurchaseSale3 = P;
                    break;
                default:
                    lastPurchaseSale = P;
                    break;
            }

           // selectPricePurchasestage.close();
        }
    }

    @FXML
    private void close(MouseEvent event) {
        //selectPricePurchasestage.close();
    }

}
