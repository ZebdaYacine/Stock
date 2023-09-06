/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Goods;
import BackEnd.models.Purchase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static stock.Stock.con;

/**
 *
 * @author kadri
 */
public class DbPurchaseAgent {

    public static String generatedCodeBarPurchase() {
        String query = " select code from purchase order by code desc limit 1; ";
        String lastId = "0";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lastId = rs.getString("code");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return (String.format("%09d", Integer.parseInt(lastId) + 1));
    }

    public static Results.Rstls addPurchase(Purchase purchase) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " purchase(id_facture,id_product,code,quantity,quantity_rest,price_purchase,price_sale,price_Total) "
                    + "values (?,?,?,?,?,?,?,?)");
            stm.setInt(1, purchase.getIdFacture());
            stm.setInt(2, purchase.getIdProduct());
            stm.setString(3, purchase.getCode());
            stm.setDouble(4, purchase.getQuantity());
            stm.setDouble(5, purchase.getQuantityRest());
            stm.setDouble(6, purchase.getPricePurchase());
            stm.setDouble(7, purchase.getPriceSale());
            stm.setDouble(8, purchase.getPriceTotal());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updatePurchase(Purchase purchase) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update  "
                    + " purchase set quantity=?,quantity_rest=?,price_purchase=?,price_sale=?,price_Total=?,code=? "
                    + " where id =?");
            stm.setDouble(1, purchase.getQuantity());
            stm.setDouble(2, purchase.getQuantityRest());
            stm.setDouble(3, purchase.getPricePurchase());
            stm.setDouble(4, purchase.getPriceSale());
            stm.setDouble(5, purchase.getPriceTotal());
            stm.setString(6, purchase.getCode());
            stm.setInt(7, purchase.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deletePurchase(Purchase purchase) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " purchase WHERE id = ?");
            stm.setInt(1, purchase.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }
    
    /*public static int getquantityTotalRest(Purchase prchs) {
        int QTotal = 0;
        String query = " SELECT sum(quantity_rest) quantityTotal FROM purchase where code = '" + prchs.getCode() + "' and quantity_rest!=0";
        ObservableList<Purchase> listContains = FXCollections.observableArrayList(new Purchase());
        listContains.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QTotal = rs.getInt("quantityTotal");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return QTotal;
    }*/
    private static String selctQuery(Purchase prchs, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            // get last purchase use codebar
            case "lastPcode": {
                query = " SELECT * FROM purchase where code = '" + prchs.getCode() + "' order by id desc limit 1";
                break;
            }
            // get last purchase use idproduct
            case "goods": {
                query = " SELECT * FROM purchase where id_product = " + prchs.getIdProduct() + " order by id desc limit 1";
                break;
            }
            case "allPurchaseIdproduct": {
                query = " SELECT * FROM purchase where id_product = " + prchs.getIdProduct() + " order by id desc";
                break;
            }
            case "goodsSale": {
                query = " SELECT * FROM purchase where id_product = " + prchs.getIdProduct() + " and quantity_rest!=0";
                break;
            }
            case "goodsSaleCodeBar": {
                query = " SELECT * FROM purchase where code = '" + prchs.getCode() + "' and quantity_rest!=0";
                break;
            }
            case "facture": {
                query = " SELECT * FROM purchase where id_facture = " + prchs.getIdFacture() + " order by id desc";
                break;
            }

            case "id": {
                query = " SELECT * FROM purchase where id = " + prchs.getId();
                break;
            }
            default: {
                query = "SELECT * FROM purchase order by id desc";
            }
        }
        return query;
    }

    public static Object getPurchase(Purchase prchs, String ArgSearch) {
        String query = selctQuery(prchs, ArgSearch);
        ObservableList<Purchase> listContains = FXCollections.observableArrayList(new Purchase());
        listContains.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Purchase prchase = new Purchase();
                prchase.setId(rs.getInt("id"));
                prchase.setCode(rs.getString("code"));
                prchase.setIdFacture(rs.getInt("id_facture"));
                prchase.setIdProduct(rs.getInt("id_product"));
                // get product by id product in table purchase
                Goods g = new Goods();
                g.setId(prchase.getIdProduct());
                ObservableList<Goods> listg = (ObservableList<Goods>) DbGoodsAgent.getGoods(g, "id");
                prchase.setProduct(listg.get(0).getName());
                prchase.setPricePurchase(rs.getDouble("price_purchase"));
                prchase.setPriceSale(rs.getDouble("price_sale"));
                prchase.setPricePurchaseS(new BigDecimal(prchase.getPricePurchase()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                prchase.setPriceSaleS(new BigDecimal(prchase.getPriceSale()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                prchase.setQuantity(rs.getDouble("quantity"));
                prchase.setQuantityRest(rs.getDouble("quantity_rest"));
                prchase.setPriceTotal(rs.getDouble("price_Total"));
                prchase.setPriceTotalS(new BigDecimal(prchase.getPriceTotal()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listContains.add(prchase);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listContains;
    }

}
