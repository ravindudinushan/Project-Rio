package lk.ijse.project_rio.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Inventory;
import lk.ijse.project_rio.dto.tm.InventoryTM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryModel {
    public static boolean save(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO item(itemId,itemName,category,unitPrice,qtyOnHand)" +
                "VALUES(?,?,?,?,?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, inventory.getId());
            pstm.setString(2, inventory.getName());
            pstm.setString(3, inventory.getCategory());
            pstm.setDouble(4, inventory.getUnitPrice());
            pstm.setString(5, inventory.getQtyOnHand());
            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean update(Inventory inventory) throws SQLException {
        String sql = "UPDATE item SET itemName=?,category=?,unitPrice=?,qtyOnHand=? WHERE itemId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, inventory.getName());
            pstm.setString(2, inventory.getCategory());
            pstm.setDouble(3, inventory.getUnitPrice());
            pstm.setString(4, inventory.getQtyOnHand());
            pstm.setString(5, inventory.getId());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM item WHERE itemId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public static Inventory findById(String id) throws SQLException {
        String sql = "SELECT * FROM item WHERE itemId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return (new Inventory(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
            return null;
        }
    }

    public static ObservableList<InventoryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM item";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ObservableList<InventoryTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = pstm.executeQuery(sql);
            while (resultSet.next()) {
                obList.add(new InventoryTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }
            return obList;
        }
    }
}
