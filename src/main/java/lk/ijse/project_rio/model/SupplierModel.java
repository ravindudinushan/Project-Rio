package lk.ijse.project_rio.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Supplier;
import lk.ijse.project_rio.dto.tm.SupplierTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier(supId,supName,address,email,contact) " +
                "VALUES(?,?,?,?,?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supplier.getId());
            pstm.setString(2, supplier.getName());
            pstm.setString(3, supplier.getAddress());
            pstm.setString(4, supplier.getEmail());
            pstm.setString(5, supplier.getContact());
            return pstm.executeUpdate() > 0;
        }
    }

    public static Supplier findById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return (new Supplier(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return null;
        }
    }

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = pstm.executeQuery(sql);
            while (resultSet.next()) {
                obList.add(new SupplierTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return obList;
        }
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supName=?,address=?,email=?,contact=? WHERE supId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supplier.getName());
            pstm.setString(2, supplier.getAddress());
            pstm.setString(3, supplier.getEmail());
            pstm.setString(4, supplier.getContact());
            pstm.setString(5, supplier.getId());

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }
}
