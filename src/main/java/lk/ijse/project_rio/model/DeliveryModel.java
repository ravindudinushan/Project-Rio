package lk.ijse.project_rio.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.project_rio.dto.Delivery;
import lk.ijse.project_rio.dto.tm.DeliveryTM;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryModel {
    public static ObservableList<DeliveryTM> getAll() throws SQLException {
        String sql = "SELECT * FROM delivery";

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql);
        while (resultSet.next()) {
            obList.add(new DeliveryTM(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return obList;
    }

    public static boolean update(Delivery delivery) throws SQLException {
        String sql = "UPDATE delivery SET delivery_status=?,delivered_date=?,location=?,orderId=?,empId=? WHERE deliveryId=?";

        return CrudUtil.execute(
                sql,
                delivery.getDelsts(),
                delivery.getDeldate(),
                delivery.getLoc(),
                delivery.getOrdid(),
                delivery.getEmpid(),
                delivery.getDelid()
        );
    }

    public static boolean delete(String delid) throws SQLException {
        String sql = "DELETE FROM delivery WHERE delivery_id=?";

        return CrudUtil.execute(sql,delid);
    }

    public static Delivery findById(String id) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE order_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,id);
        if(resultSet.next()){
            return (new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return null;
    }

    public static ObservableList<DeliveryTM> getByDeliveryStatus(String delists) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE delivery_status=?";

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,delists);
        while (resultSet.next()) {
            obList.add(new DeliveryTM(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return obList;
    }

    public static ObservableList<DeliveryTM> getByDueDate(String duedate) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE due_date=?";

        ObservableList<DeliveryTM> obList = FXCollections.observableArrayList();

        ResultSet resultSet = CrudUtil.execute(sql,duedate);
        while (resultSet.next()) {
            obList.add(new DeliveryTM(
                    resultSet.getString(5),
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getString(2),
                    resultSet.getString(4),
                    resultSet.getString(6)
            ));
        }
        return obList;
    }

    public static Delivery findBydeliveryId(String delid) throws SQLException {
        String sql = "SELECT * FROM delivery WHERE delivery_id=?";

        ResultSet resultSet = CrudUtil.execute(sql,delid);
        if(resultSet.next()){
            return (new Delivery(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return null;
    }
}
