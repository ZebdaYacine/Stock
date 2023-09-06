/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.DbAgents;


import BackEnd.Results;
import BackEnd.models.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static stock.Stock.con;

/**
 *
 * @author Zed-Yacine
 */
public class DbTypeAgent {
    

    public static Results.Rstls addType(Type tp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into type (name) values (?)");
            stm.setString(1, tp.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_INSERTED;
        } catch (Exception ex) {
            System.err.println(ex);
            return Results.Rstls.DATA_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateType(Type tp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + " type SET name = ? WHERE id = ? ");
            stm.setString(1, tp.getName());
            stm.setInt(2, tp.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_UPDATED;
        } catch (Exception ex) {
            return Results.Rstls.DATA_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteType(Type tp) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + " type WHERE id = ?");
            stm.setInt(1, tp.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DATA_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DATA_NOT_DELETED;
        }
    }

    private static String selctQuery(Type type, String ArgSearch) {
        String query = "";
        switch (ArgSearch) {
            case "goods": {
                query = " SELECT * FROM type where id not in (select id_type from goods)";
                break;
            }
            case "name": {
                query = " SELECT * FROM type where name LIKE '" + type.getName() + "'";
                break;
            }
            case "id": {
                query = " SELECT * FROM type where name LIKE '" + type.getId()+ "'";
                break;
            }

            default: {
                query = "SELECT * FROM type order by id desc";
            }
        }
        return query;
    }
    public static Object getTypes(Type tp, String ArgSearch) {
        String query = selctQuery(tp, ArgSearch);
        
        
        ObservableList<Type> ListTypes = FXCollections.observableArrayList(new Type());
        ListTypes.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Type splty = new Type();
                splty.setId(rs.getInt("id"));
                splty.setName(rs.getString("name"));
                ListTypes.add(splty);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return ListTypes;
    }

}
