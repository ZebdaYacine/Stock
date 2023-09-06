/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
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
 * @author RD°INFO
 */
public class DbProviderAgent {

    public static Results.Rstls addProvider(Provider provider) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " provider(name,phone,address,debt) "
                    + "values (?,?,?,?)");
            stm.setString(1, provider.getName());
            stm.setString(2, provider.getPhone());
            stm.setString(3, provider.getAddress());
            stm.setDouble(4, provider.getDebt());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteProvider(Provider provider) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " provider WHERE id = ?");
            stm.setInt(1, provider.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Results.Rstls updateProvider(Provider provider) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update  provider set "
                    + "name=?,phone=?,address=?,debt=?"
                    + " where id =?");
            stm.setString(1, provider.getName());
            stm.setString(2, provider.getPhone());
            stm.setString(3, provider.getAddress());
            stm.setDouble(4, provider.getDebt());
            stm.setInt(5, provider.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static double getdebtProviders() {
        String query = " SELECT sum(debt) debt FROM provider ";
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("debt");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    public static double getdebtProvider(int idP) {
        String query = " SELECT debt FROM provider  where id=" + idP;
        double pTc = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pTc = rs.getDouble("debt");
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return pTc;
    }

    private static String selctQuery(Provider provider, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "id": {
                query = " SELECT * FROM provider where id LIKE '" + provider.getId() + "'";
                break;
            }
            case "name": {
                query = " SELECT * FROM provider where name LIKE '" + provider.getName() + "'";
                break;
            }
            case "phone": {
                query = " SELECT * FROM provider where phone LIKE '" + provider.getPhone() + "'";
                break;
            }
            default: {
                query = "SELECT * FROM provider order by id desc";
            }
        }
        return query;
    }

    public static Object getProvider(Provider provider, String ArgSearch) {
        String query = selctQuery(provider, ArgSearch);
        ObservableList<Provider> ListProviders = FXCollections.observableArrayList(new Provider());
        ListProviders.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Provider prvd = new Provider();
                prvd.setId(rs.getInt("id"));
                prvd.setName(rs.getString("name"));
                prvd.setPhone(rs.getString("phone"));
                prvd.setAddress(rs.getString("address"));
                prvd.setDebt(rs.getDouble("debt"));
                prvd.setDebtS(new BigDecimal(prvd.getDebt()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                ListProviders.add(prvd);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return ListProviders;
    }
}
