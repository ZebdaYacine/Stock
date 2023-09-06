/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Client;
import BackEnd.models.FactureSale;
import BackEnd.models.User;
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
public class DbFactureSaleAgent {

    public static String generatedCodeFactureSale() {
        String query = " select id from facture_sale order by id desc limit 1; ";
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

    public static Results.Rstls addFactur(FactureSale facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " facture_Sale(code,id_client,id_user,TotalAmount,remise,payment,date) "
                    + "values (?,?,?,?,?,?,?)");
            stm.setString(1, facture.getCode());
            stm.setInt(2, facture.getIdClient());
            stm.setInt(3, facture.getIdUser());
            stm.setDouble(4, facture.getTotalAmount());
            stm.setDouble(5, facture.getRemise());
            stm.setDouble(6, facture.getPayment());
            stm.setString(7, facture.getDate());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls applyFacturSale(FactureSale facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update facture_sale "
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

    public static Results.Rstls deleteFactureSale(FactureSale facture) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " facture_sale WHERE id = ?");
            stm.setInt(1, facture.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static double getSalesenterDate(String date1, String date2) {
        String query = " SELECT sum(TotalAmount) Sle FROM facture_sale where date >= '" + date1 + "' and date<'" + date2 + "'";
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("Sle");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    private static String selctQuery(FactureSale facture, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "statSle": {
                query = " SELECT * FROM facture_sale where date >'" + facture.getDate() + "' order by id desc";
                break;
            }
            case "date": {
                query = " SELECT * FROM facture_sale where date like '" + facture.getDate() + "%' order by id desc";
                break;
            }
            case "client": {
                query = " SELECT * FROM facture_sale where id_client = " + facture.getIdClient() + " order by id desc";
                break;
            }
            case "code": {
                query = " SELECT * FROM facture_sale where code LIKE '" + facture.getCode() + "%'";
                break;
            }
            case "id": {
                query = " SELECT * FROM facture_sale where id = " + facture.getId();
                break;
            }

            default: {
                query = "SELECT * FROM facture_sale order by id desc";
            }
        }
        return query;
    }

    public static Object getFactures(FactureSale facture, String ArgSearch) {
        String query = selctQuery(facture, ArgSearch);
        ObservableList<FactureSale> listFacture = FXCollections.observableArrayList(new FactureSale());
        listFacture.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FactureSale fct = new FactureSale();
                fct.setId(rs.getInt("id"));
                fct.setCode(rs.getString("code"));
                fct.setIdClient(rs.getInt("id_client"));
                fct.setIdUser(rs.getInt("id_user"));
                // get client name 
                Client c = new Client();
                User u = new User();
                c.setId(fct.getIdClient());
                u.setId(fct.getIdUser());
                ObservableList<Client> listc = (ObservableList<Client>) DbClientAgent.getClient(c, "id");
                ObservableList<User> listU = (ObservableList<User>) DbUserAgent.getUsers(u, "id");
                fct.setClient(listc.get(0).getName());
                fct.setUser(listU.get(0).getUserName());
                fct.setDate(rs.getString("date"));
                fct.setTotalAmount(rs.getDouble("TotalAmount"));
                fct.setTotalAmountS(new BigDecimal(rs.getDouble("TotalAmount")).setScale(2, RoundingMode.HALF_UP).toPlainString());
                fct.setPayment(rs.getDouble("payment"));
                fct.setPaymentS(new BigDecimal(fct.getPayment()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                fct.setRemise(rs.getDouble("remise"));
                fct.setRemiseS(new BigDecimal(fct.getRemise()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listFacture.add(fct);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listFacture;
    }

    private static String SumQuery(FactureSale facture, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "statSle": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP, sum(remise) SumR from facture_sale where date >'" + facture.getDate() + "' order by id desc";
                break;
            }
            case "date": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_sale where date like '" + facture.getDate() + "%' order by id desc";
                break;
            }
            case "client": {
                query = " select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_sale where id_client = " + facture.getIdClient() + " order by id desc";
                break;
            }
            case "code": {
                query = " select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_sale where code LIKE '" + facture.getCode() + "%'";
                break;
            }
            case "id": {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_sale where id = " + facture.getId();
                break;
            }

            default: {
                query = "select count(id) CountId,sum(TotalAmount) SumT ,sum(payment) SumP,sum(remise) SumR from facture_sale";
            }
        }
        return query;
    }

    public static Object getSum(FactureSale facture, String ArgSearch) {
        String query = SumQuery(facture, ArgSearch);
        ObservableList<FactureSale> listFacture = FXCollections.observableArrayList(new FactureSale());
        listFacture.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FactureSale Sum = new FactureSale();
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
