package lk.ijse.project_rio.model;

import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.User;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public static boolean save(User user) throws SQLException {
        String sql = "INSERT INTO user(userName,password,jobTitle,email) " +
                "VALUES(?,?,?,?)";
//        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {
//
//            pstm.setString(1, user.getName());
//            pstm.setString(2,user.getPassword());
//            pstm.setString(3,user.getJobTitle());
//            pstm.setString(4,user.getEmail());
//
//            return pstm.executeUpdate() > 0;
//        }
        return CrudUtil.execute(sql,user.getName(),user.getPassword(),user.getJobTitle(),user.getEmail());
    }

    public static User findbyusername(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE userName=?";

            ResultSet resultSet = CrudUtil.execute(sql,username);
            if(resultSet.next()) {
                return new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(4),
                        resultSet.getString(3)
                );
            }
            return null;
    }
}
