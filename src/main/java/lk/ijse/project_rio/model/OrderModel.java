package lk.ijse.project_rio.model;

import lk.ijse.project_rio.db.DBConnection;
import lk.ijse.project_rio.dto.CartDTO;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OrderModel {

    public static String getNextOrderId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";

        ResultSet resultSet = CrudUtil.execute(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("ORD-");
            int id = Integer.parseInt(strings[1]);
            ++id;
            String digit=String.format("%03d", id);
            return "ORD-" + digit;
        }
        return "ORD-001";
    }

    public static boolean placeOrder(String oId, String cusId, Boolean delivery, String totle, List<CartDTO> cartDTOList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = save(oId, cusId, delivery,LocalDate.now(), LocalTime.now(), totle );
            if(isSaved) {
                System.out.println("isSaved");
                boolean isUpdated = InventoryModel.updateQty(cartDTOList);
                if(isUpdated) {
                    System.out.println("isUpdated");
                    boolean isOrdered = OrderDetailModel.save(oId, cartDTOList);
                    if(isOrdered) {
                        System.out.println("isOrdered");
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    private static boolean save(String orderId, String custId, Boolean delivery, LocalDate date, LocalTime time, String ordpay) throws SQLException {
        String sql = "INSERT INTO orders(orderId,date,time,deliveryStatus,payment,custId) VALUES(?,?,?,?,?,?)";

        return  CrudUtil.execute(
                sql,
                orderId,
                date,
                time,
                delivery,
                ordpay,
                custId
        );
    }
}
