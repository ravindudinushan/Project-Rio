package lk.ijse.project_rio.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Supplier;
import lk.ijse.project_rio.dto.tm.SupplierTM;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SupplierModel {
    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO supplier(supId,supName,address,email,contact) " +
                "VALUES(?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, supplier.getId());
//            pstm.setString(2, supplier.getName());
//            pstm.setString(3, supplier.getAddress());
//            pstm.setString(4, supplier.getEmail());
//            pstm.setString(5, supplier.getContact());
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getEmail(),supplier.getContact());
    }

    public static Supplier findById(String id) throws SQLException {
        String sql = "SELECT * FROM supplier WHERE supId=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
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

    public static ObservableList<SupplierTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
            ObservableList<SupplierTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
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

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE supplier SET supName=?,address=?,email=?,contact=? WHERE supId=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, supplier.getName());
//            pstm.setString(2, supplier.getAddress());
//            pstm.setString(3, supplier.getEmail());
//            pstm.setString(4, supplier.getContact());
//            pstm.setString(5, supplier.getId());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,supplier.getName(),supplier.getAddress(),supplier.getEmail(),supplier.getContact(),supplier.getId());
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE supId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }

    public static List<String> loadSupIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT supId FROM supplier");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
    public static String getNextSupId() throws SQLException {
        String sql = "SELECT supplierOrderId FROM supplierorderdetail ORDER BY supplierOrderId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("SLD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "SLD-" + digit;
        }
        return "SLD-001";
    }
}
