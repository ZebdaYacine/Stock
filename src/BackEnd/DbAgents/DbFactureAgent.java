/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Facture;
import BackEnd.models.Provider;
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
public class DbFactureAgent {

    public static String generatedCodeFacturePurchase() {
        String query = " select id from facture_purchase order by id desc limit 1; ";
        int lastId = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lastId = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return "BL" + (lastId + 1);
    }

    public static Results.Rstls addFactur(Facture facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " facture_purchase(code,TotalAmount,remise,payment,id_provider,date) "
                    + "values (?,?,?,?,?,?)");
            stm.setString(1, facture.getCode());
            stm.setDouble(2, facture.getTotalAmount());
            stm.setDouble(3, facture.getRemise());
            stm.setDouble(4, facture.getPayment());
            stm.setDouble(5, facture.getIdProvider());
            stm.setString(6, facture.getDate());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls applyFactur(Facture facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update facture_purchase "
                    + " set TotalAmount=?,remise=?,payment=?,date=? where id =? ");
            stm.setDouble(1, facture.getTotalAmount());
            stm.setDouble(2, facture.getRemise());
            stm.setDouble(3, facture.getPayment());
            stm.setString(4, facture.getDate());
            stm.setInt(5, facture.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteFacture(Facture facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " facture_purchase WHERE id = ?");
            stm.setInt(1, facture.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static double getpurchasefactures(String date) {
        String query;
        if (date.equals("")) {
            query = "select sum(totalePrice) sum from purchase";
        } else {
            query = "select sum(totalePrice) sum from purchase where id_facture in (select id from facture_purchase where date like '" + date + "')";
        }

        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("sum");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getPrsenterDate(String date1, String date2) {
        String query = " SELECT sum(TotalAmount) Prs FROM facture_purchase where date >= '" + date1 + "' and date<'" + date2 + "'";
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("Prs");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getPrsQrest() {
        String query = " SELECT * FROM purchase where quantity_rest>0";
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = pTc + rs.getDouble("price_purchase") * rs.getDouble("quantity_rest");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getbenefitFacturePurchase(int id_F) {
        String query = "SELECT * FROM purchase where id_facture = " + id_F;
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = pTc + ((rs.getDouble("price_sale") - rs.getDouble("price_purchase")) * rs.getInt("quantity"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    private static String selctQuery(Facture facture, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "statPrs": {
                query = " SELECT * FROM facture_purchase where date >='" + facture.getDate() + "' order by id desc";
                break;
            }
            case "date": {
                query = " SELECT * FROM facture_purchase where date like '" + facture.getDate() + "%' order by id desc";
                break;
            }
            case "provider": {
                query = " SELECT * FROM facture_purchase where id_provider LIKE '" + facture.getIdProvider() + "'";
                break;
            }
            case "code": {
                query = " SELECT * FROM facture_purchase where code LIKE '" + facture.getCode() + "%'";
                break;
            }
            case "id": {
                query = " SELECT * FROM facture_purchase where id = " + facture.getId();
                break;
            }

            default: {
                query = "SELECT * FROM facture_purchase order by id desc";
            }
        }
        return query;
    }

    public static Object getFactures(Facture facture, String ArgSearch) {
        String query = selctQuery(facture, ArgSearch);
        ObservableList<Facture> listFacture = FXCollections.observableArrayList(new Facture());
        listFacture.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Facture fct = new Facture();
                fct.setId(rs.getInt("id"));
                fct.setCode(rs.getString("code"));
                fct.setIdProvider(rs.getInt("id_provider"));
                // get client name 
                Provider p = new Provider();
                p.setId(fct.getIdProvider());
                ObservableList<Provider> listP = (ObservableList<Provider>) DbProviderAgent.getProvider(p, "id");
                fct.setProvider(listP.get(0).getName());
                fct.setDate(rs.getString("date"));
                fct.setTotalAmount(rs.getDouble("TotalAmount"));
                fct.setRemise(rs.getDouble("remise"));
                fct.setTotalAmountS(new BigDecimal(rs.getDouble("TotalAmount")).setScale(2, RoundingMode.HALF_UP).toPlainString());
                fct.setPayment(rs.getDouble("payment"));
                fct.setRemiseS(new BigDecimal(fct.getRemise()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                fct.setPaymentS(new BigDecimal(fct.getPayment()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listFacture.add(fct);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listFacture;
    }

    private static String SumQuery(Facture facture, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "statSle": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP, sum(remise) SumR from facture_purchase where date >'" + facture.getDate() + "' order by id desc";
                break;
            }
            case "date": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_purchase where date like '" + facture.getDate() + "%' order by id desc";
                break;
            }
            case "client": {
                query = " select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_purchase where id_provider = " + facture.getIdProvider() + " order by id desc";
                break;
            }
            case "code": {
                query = " select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_purchase where code LIKE '" + facture.getCode() + "%'";
                break;
            }
            case "id": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_purchase where id = " + facture.getId();
                break;
            }

            default: {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_purchase";
            }
        }
        return query;
    }

    public static Object getSum(Facture facture, String ArgSearch) {
        String query = SumQuery(facture, ArgSearch);
        ObservableList<Facture> listFacture = FXCollections.observableArrayList(new Facture());
        listFacture.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Facture Sum = new Facture();
                Sum.setCode(rs.getInt("CountId") + "");
                Sum.setTotalAmountS(new BigDecimal(rs.getDouble("SumT")).setScale(2, RoundingMode.HALF_UP).toPlainString());
                Sum.setPaymentS(new BigDecimal(rs.getDouble("SumP")).setScale(2, RoundingMode.HALF_UP).toPlainString());
                Sum.setRemiseS(new BigDecimal(rs.getDouble("SumR")).setScale(2, RoundingMode.HALF_UP).toPlainString());
                double sumT_R = rs.getDouble("SumT") + rs.getDouble("SumR");
                Sum.setDate(new BigDecimal(sumT_R).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listFacture.add(Sum);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listFacture;
    }
}
