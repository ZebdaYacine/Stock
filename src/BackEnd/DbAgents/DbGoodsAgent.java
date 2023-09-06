/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Goods;
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
public class DbGoodsAgent {

    public static Results.Rstls addGoods(Goods goods) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " product(`name`, `quantity_alert`,`image`,`fav`) "
                    + "values (?,?,?,?)");
            stm.setString(1, goods.getName());
            stm.setInt(2, goods.getQuantityAlert());
            stm.setString(3, goods.getImgProduct());
            stm.setInt(4, goods.getFav());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static int getTotalproduct() {
        String query = " SELECT sum(quantity) quantity FROM product";
        int pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getInt("quantity");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static Results.Rstls updateGoods(Goods goods) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update product "
                    + " SET `name` = ?,`quantity_alert` = ? ,`image` = ?,`fav` = ?"
                    + " WHERE `id` = ?");
            stm.setString(1, goods.getName());
            stm.setInt(2, goods.getQuantityAlert());
            stm.setString(3, goods.getImgProduct());
            stm.setInt(4, goods.getFav());
            stm.setInt(5, goods.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls updateQuantityGoods(Goods goods) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update product "
                    + " set quantity=?, `countPurchase` = ?"
                    + ", `countSale` = ? where id =  ? ");
            stm.setDouble(1, goods.getQuantity());
            stm.setDouble(2, goods.getCountPurchase());
            stm.setDouble(3, goods.getCountSale());
            stm.setInt(4, goods.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static void setQ(Goods g) {
        int q = 0;
        String query = "select * from purchase where id_product=" + g.getId();
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                q = q + rs.getInt("quantity_rest");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        g.setQuantity(q);
        updateQuantityGoods(g);
    }

    public static Results.Rstls deleteGoods(Goods goods) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " product WHERE id = ?");
            stm.setInt(1, goods.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    private static String selctQuery(Goods goods, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "goods_not_in_sale": {
                query = "select * from product where "
                        + " id in (select id_product from purchase"
                        + " where id not in (select id_purchase from sale where id_facture =" + goods.getId() + ") "
                        + " and quantity_rest!=0) "
                        + " and name like '" + goods.getName() + "%' "
                        + " and quantity!=0 "
                        + " order by countSale desc";
                break;
            }
            case "fav1": {
                query = "select * from product where "
                        + " id in (select id_product from purchase"
                        + " where id not in (select id_purchase from sale where id_facture =" + goods.getId() + ") "
                        + " and quantity_rest!=0) "
                        + " and name like '" + goods.getName() + "%' "
                        + " and quantity!=0 "
                        + " and fav=1 "
                        + " order by countSale desc";
                break;
            }
            case "fav2": {
                query = "select * from product where "
                        + " id in (select id_product from purchase"
                        + " where id not in (select id_purchase from sale where id_facture =" + goods.getId() + ") "
                        + " and quantity_rest!=0) "
                        + " and name like '" + goods.getName() + "%' "
                        + " and quantity!=0 "
                        + " and fav=2 "
                        + " order by countSale desc";
                break;
            }
            case "goods_not_in_purchase": {
                query = "select * from product where id not in (select id_product from purchase where id_facture = " + goods.getId() + ") "
                        + " and name like '" + goods.getName() + "%' order by countPurchase desc";
                break;
            }
            case "id": {
                query = " SELECT * FROM product where id = " + goods.getId();
                break;
            }
            case "name": {
                query = " SELECT * FROM product where name LIKE '" + goods.getName() + "%'";
                break;
            }
            case "minquantity": {
                query = "SELECT * FROM product where quantity <= quantity_alert";
                break;
            }
            default: {
                query = "SELECT * FROM product";
            }
        }
        return query;
    }

    public static Object getGoods(Goods goods, String ArgSearch) {
        String query = selctQuery(goods, ArgSearch);
        ObservableList<Goods> listGoods = FXCollections.observableArrayList(new Goods());
        listGoods.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Goods gds = new Goods();
                gds.setId(rs.getInt("id"));
                gds.setFav(rs.getInt("fav"));
                gds.setName(rs.getString("name"));
                gds.setImgProduct(rs.getString("image"));
                gds.setQuantity(rs.getInt("quantity"));
                gds.setQuantityAlert(rs.getInt("quantity_alert"));
                gds.setCountPurchase(rs.getInt("countPurchase"));
                gds.setCountSale(rs.getInt("countSale"));
                //price purchase 
                /*gds.setPrice_purchase(rs.getDouble("price_purchase"));
                gds.setPrice_purchaseS(new BigDecimal(gds.getPrice_purchase()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                //price sale 1 2 3 
                gds.setPrice_sale(rs.getDouble("price_sale"));
                gds.setPrice_saleS(new BigDecimal(gds.getPrice_sale()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                 */
                listGoods.add(gds);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listGoods;
    }
}
