package lk.ijse.project_rio.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Customer;
import lk.ijse.project_rio.dto.tm.CustomerTM;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer(custId,custName,address,email,contact) " +
                "VALUES(?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, customer.getId());
//            pstm.setString(2, customer.getName());
//            pstm.setString(3, customer.getAddress());
//            pstm.setString(4, customer.getEmail());
//            pstm.setString(5, customer.getContact());
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact());
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET custName=?,address=?,email=?,contact=? WHERE custId=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, customer.getName());
//            pstm.setString(2, customer.getAddress());
//            pstm.setString(3, customer.getEmail());
//            pstm.setString(4, customer.getContact());
//            pstm.setString(5, customer.getId());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,customer.getName(),customer.getAddress(),customer.getEmail(),customer.getContact(),customer.getId());

    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customer WHERE custId=?";
//
        return CrudUtil.execute(sql,id);
    }

    public static ObservableList<CustomerTM> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";


            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new CustomerTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
                 }
                return obList;
            }

    public static Customer findById(String id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE custId=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return null;

    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT custId FROM Customer");

        List<String> data = new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static String getCustName(String custId) throws SQLException {
        String sql = "SELECT custName FROM customer WHERE custId=?";
        ResultSet resultSet = CrudUtil.execute(sql,custId);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }

    public static String getCustEmail(String custId) throws SQLException {
        String sql = "SELECT email FROM customer WHERE custId=?";
        ResultSet resultSet = CrudUtil.execute(sql,custId);

        if(resultSet.next()){
            return (new String(
                    resultSet.getString(1)
            ));
        }
        return null;
    }
}
