package lk.ijse.project_rio.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Employee;
import lk.ijse.project_rio.dto.tm.EmployeeTM;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(empId,empName,nic,dob,address,email,jobTitle,contact,salary) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, employee.getId());
//            pstm.setString(2, employee.getName());
//            pstm.setString(4, employee.getDob());
//            pstm.setString(5, employee.getAddress());
//            pstm.setString(8, employee.getContact());
//            pstm.setString(6, employee.getEmail());
//            pstm.setString(7, employee.getJob());
//            pstm.setString(3, employee.getNic());
//            pstm.setDouble(9, employee.getSalary());
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getNic(),employee.getDob(),employee.getAddress(),employee.getEmail(),employee.getJob(),employee.getContact(),employee.getSalary());
    }

    public static ObservableList<EmployeeTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = CrudUtil.execute(sql);
            while (resultSet.next()) {
                obList.add(new EmployeeTM(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getDouble(9)
                ));
            }
            return obList;
    }

    public static Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE empId=?";

            ResultSet resultSet = CrudUtil.execute(sql,id);
            if(resultSet.next()){
                return (new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getDouble(9)

                ));
            }
            return null;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET empName=?,nic=?,dob=?,address=?,email=?,jobTitle=?,contact=?,salary=? WHERE empId=?";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, employee.getName());
//            pstm.setString(2, employee.getNic());
//            pstm.setString(3, employee.getDob());
//            pstm.setString(4, employee.getAddress());
//            pstm.setString(5, employee.getEmail());
//            pstm.setString(6, employee.getJob());
//            pstm.setString(7, employee.getContact());
//            pstm.setDouble(8, employee.getSalary());
//            pstm.setString(9, employee.getId());
//
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,employee.getName(),employee.getNic(),employee.getDob(),employee.getAddress(),employee.getEmail(),employee.getJob(),employee.getContact(),employee.getSalary(),employee.getId());
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE empId=?";
        return CrudUtil.execute(sql,id);
    }

    public static List<String> loadIds() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT empId FROM employee");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }
}
