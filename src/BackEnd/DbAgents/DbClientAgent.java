/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;

import BackEnd.Results;
import BackEnd.models.Client;
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
public class DbClientAgent {

    public static Results.Rstls addClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("insert into "
                    + " client(name,phone,address,debt,max_debt) "
                    + "values (?,?,?,?,?)");
            stm.setString(1, client.getName());
            stm.setString(2, client.getPhone());
            stm.setString(3, client.getAddress());
            stm.setDouble(4, client.getTotaleDebt());
            stm.setDouble(5, client.getMaxDebt());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls deleteClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " client WHERE id = ?");
            stm.setInt(1, client.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    public static Results.Rstls updateClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("update  client set "
                    + "name=?,phone=?,address=?,debt=?,max_debt=?"
                    + " where id =?");
            stm.setString(1, client.getName());
            stm.setString(2, client.getPhone());
            stm.setString(3, client.getAddress());
            stm.setDouble(4, client.getTotaleDebt());
            stm.setDouble(5, client.getMaxDebt());
            stm.setInt(6, client.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (SQLException ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static double getTotaldebt() {
        String query = " SELECT sum(debt) debt FROM client ";
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

    public static double getdebt(int idC) {
        String query = " SELECT debt FROM client where id=" + idC;
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

    private static String selctQuery(Client client, String ArgSearch) {
        String query;
        switch (ArgSearch) {
            case "id": {
                query = " SELECT * FROM client where id =" + client.getId();
                break;
            }
            case "name": {
                query = " SELECT * FROM client where name LIKE '" + client.getName() + "%'";
                break;
            }
            case "phone": {
                query = " SELECT * FROM client where phone LIKE '" + client.getPhone() + "%'";
                break;
            }
            case "list_noir": {
                query = "SELECT * FROM client where debt>=max_debt and id!=1";
                break;
            }
            default: {
                query = "SELECT * FROM client";
            }
        }
        return query;
    }

    public static Object getClient(Client client, String ArgSearch) {
        String query = selctQuery(client, ArgSearch);
        ObservableList<Client> listClient = FXCollections.observableArrayList(new Client());
        listClient.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setTotaleDebt(rs.getDouble("debt"));
                c.setTotaleDebtS(new BigDecimal(c.getTotaleDebt()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                c.setMaxDebt(rs.getDouble("max_debt"));
                c.setMaxDebtS(new BigDecimal(c.getMaxDebt()).setScale(2, RoundingMode.HALF_UP).toPlainString());
                listClient.add(c);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return listClient;
    }

}
