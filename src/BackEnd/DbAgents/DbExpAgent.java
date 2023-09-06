/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

//import static BackEnd.BackEndMilk.con;
import BackEnd.Results;
import BackEnd.models.Exp;
import BackEnd.models.User;
import java.lang.reflect.Array;
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
public class DbExpAgent {

    public static Results.Rstls addExp(Exp exp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " exp(type,montent,date) "
                    + "values (?,?,?)");
            stm.setString(1, exp.getType());
            stm.setDouble(2, exp.getMontent());
            stm.setString(3, exp.getDate());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }
    public static Results.Rstls addAlmony(Exp exp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " alimony (name) "
                    + "values (?)");
            stm.setString(1, exp.getType());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteExp(Exp exp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " exp WHERE id = ?");
            stm.setInt(1, exp.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Results.Rstls updateExp(Exp exp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update  exp set type=?,montent=?"
                    + " ,date=? where id =?");
            stm.setString(1, exp.getType());
            stm.setDouble(2, exp.getMontent());
            stm.setString(3, exp.getDate());
            stm.setInt(4, exp.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    private static String selctQuery(Exp exp, String ArgSearch) {
        String query = "";
        switch (ArgSearch) {
            case "id": {
                query = " SELECT * FROM exp where id = " + exp.getId();
                break;
            }
            case "userName": {
                query = " SELECT * FROM exp where type LIKE '" + exp.getType()+ "%'";
                break;
            }
            case "date": {
                query = " SELECT * FROM exp where date like '" + exp.getDate() + "%' order by id desc";
                break;
            }
            default: {
                query = "SELECT * FROM exp order by id desc";
            }
        }
        return query;
    }

    public static Object getExps(Exp exp, String ArgSearch) {
        String query = selctQuery(exp, ArgSearch);
        ObservableList<Exp> Listexps = FXCollections.observableArrayList(new Exp());
        Listexps.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exp e = new Exp();
                e.setId(rs.getInt("id"));
                e.setType(rs.getString("type"));
                e.setMontent(rs.getDouble("montent"));
                e.setMontentS(new BigDecimal(e.getMontent()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                e.setDate(rs.getString("date"));
                Listexps.add(e);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return Listexps;
    }
    public static Object getAlimony() {
        ObservableList<String> Listexps = FXCollections.observableArrayList("");
        Listexps.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("SELECT * FROM alimony");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Listexps.add(rs.getString("name"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return Listexps;
    }

}
