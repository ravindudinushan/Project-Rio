package lk.ijse.project_rio.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.Employee;
import lk.ijse.project_rio.dto.tm.EmployeeTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeModel {
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee(empId,empName,nic,dob,address,email,jobTitle,contact,salary) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, employee.getId());
            pstm.setString(2, employee.getName());
            pstm.setString(4, employee.getDob());
            pstm.setString(5, employee.getAddress());
            pstm.setString(8, employee.getContact());
            pstm.setString(6, employee.getEmail());
            pstm.setString(7, employee.getJob());
            pstm.setString(3, employee.getNic());
            pstm.setDouble(9, employee.getSalary());
            return pstm.executeUpdate() > 0;
        }
    }

    public static ObservableList<EmployeeTM> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();

            ResultSet resultSet = pstm.executeQuery(sql);
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
    }

    public static Employee findById(String id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE empId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1,id);

            ResultSet resultSet = pstm.executeQuery();
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
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE Employee SET empName=?,nic=?,dob=?,address=?,email=?,jobTitle=?,contact=?,salary=? WHERE empId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, employee.getName());
            pstm.setString(2, employee.getNic());
            pstm.setString(3, employee.getDob());
            pstm.setString(4, employee.getAddress());
            pstm.setString(5, employee.getEmail());
            pstm.setString(6, employee.getJob());
            pstm.setString(7, employee.getContact());
            pstm.setDouble(8, employee.getSalary());
            pstm.setString(9, employee.getId());


            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employee WHERE empId=?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }
    }
}
