/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Goods;
import BackEnd.models.Sale;
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
 * @author RD°INFO
 */
public class DbSaleAgent {

    public static Results.Rstls addSale(Sale sale) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " sale(id_facture,id_product,quantity,price_purchase,price_sale,price_Total,benefit_Total,quantity_retour,id_purchase) "
                    + "values (?,?,?,?,?,?,?,?,?)");
            stm.setInt(1, sale.getIdFacture());
            stm.setInt(2, sale.getIdProduct());
            stm.setDouble(3, sale.getQuantity());
            stm.setDouble(4, sale.getPricePurchase());
            stm.setDouble(5, sale.getPrice_sale());
            stm.setDouble(6, sale.getPriceTotal());
            stm.setDouble(7, sale.getBenefitSale());
            stm.setDouble(8, sale.getQuantityRetour());
            stm.setInt(9, sale.getIdPurchase());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateSale(Sale usale) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(" update  "
                    + "sale set quantity=?,price_purchase=?,price_sale=? ,price_Total=?,benefit_Total=?,quantity_retour=? "
                    + " where id=? ");
            stm.setDouble(1, usale.getQuantity());
            stm.setDouble(2, usale.getPricePurchase());
            stm.setDouble(3, usale.getPrice_sale());
            stm.setDouble(4, usale.getPriceTotal());
            stm.setDouble(5, usale.getBenefitSale());
            stm.setDouble(6, usale.getQuantityRetour());
            stm.setInt(7, usale.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteSale(Sale sale) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " sale WHERE id = ?");
            stm.setInt(1, sale.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    private static String selctQuery(Sale sle, String ArgSearch) {
        String query;

        switch (ArgSearch) {
            case "PonFsale": {
                query = "select * from sale where id_facture=" + sle.getIdFacture() + " and id_purchase=" + sle.getIdPurchase();
                break;
            }
            case "allSaleIdproduct": {
                query = " SELECT * FROM sale where id_product = " + sle.getIdProduct() + " order by id desc";
                break;
            }
            case "facture": {
                query = " SELECT * FROM sale where id_facture = " + sle.getIdFacture() + " order by id desc";
                break;
            }
            default: {
                query = "SELECT * FROM sale order by id desc";
            }
        }
        return query;
    }

    public static double getbenefitClient(int id_C) {
        String query = " select id from facture_sale where id_client=" + id_C;
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = pTc + getbenefitFacture(rs.getInt("id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getBenefitenterDate(String date1, String date2) {
        String query = " SELECT id FROM facture_sale where date >= '" + date1 + "' and date<'" + date2 + "'";
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = pTc + getbenefitFacture(rs.getInt("id"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getbenefitFacture(int id_F) {
        String query = " select sum(benefit_Total)  benefit from sale where id_facture=" + id_F;
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("benefit");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static Object getSales(Sale sale, String ArgSearch) {
        String query = selctQuery(sale, ArgSearch);
        ObservableList<Sale> listPurchase = FXCollections.observableArrayList(new Sale());
        listPurchase.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sale Sle = new Sale();
                Sle.setId(rs.getInt("id"));
                Sle.setIdFacture(rs.getInt("id_facture"));
                Sle.setIdProduct(rs.getInt("id_product"));
                Sle.setIdPurchase(rs.getInt("id_purchase"));

                // get product by id product in table sale
                Goods g = new Goods();
                g.setId(Sle.getIdProduct());
                ObservableList<Goods> listg = (ObservableList<Goods>) DbGoodsAgent.getGoods(g, "id");
                Sle.setProduct(listg.get(0).getName());

                Sle.setQuantity(rs.getDouble("quantity"));
                Sle.setQuantityRetour(rs.getDouble("quantity_retour"));
                Sle.setPricePurchase(rs.getDouble("price_purchase"));
                Sle.setPricePurchaseS(new BigDecimal(Sle.getPricePurchase()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                Sle.setPrice_sale(rs.getDouble("price_sale"));
                Sle.setPrice_saleS(new BigDecimal(Sle.getPrice_sale()).setScale(2, RoundingMode.HALF_UP).toPlainString());

                Sle.setPriceTotal(rs.getDouble("price_Total"));
                Sle.setPriceTotalS(new BigDecimal(Sle.getPriceTotal()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                Sle.setBenefitSale(rs.getDouble("benefit_Total"));
                Sle.setBenefitSaleS(new BigDecimal(Sle.getBenefitSale()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listPurchase.add(Sle);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listPurchase;
    }

}
