package lk.ijse.project_rio.model;

import lk.ijse.project_rio.dto.NewDelivery;
import lk.ijse.project_rio.util.CrudUtil;

import java.sql.SQLException;

public class NewDeliveryModel {
    public static boolean save(NewDelivery gotnewdelivery) throws SQLException {
        String sql = "INSERT INTO delivery(deliveryId,Date,location,orderId,empId)" +
                "VALUES (?,?,?,?,?)";

        return CrudUtil.execute(
                sql,
                gotnewdelivery.getDelId(),
                gotnewdelivery.getDueDate(),
                gotnewdelivery.getLocation(),
                gotnewdelivery.getOrderId(),
                gotnewdelivery.getEmpId()
        );
    }
}
