package lk.ijse.project_rio.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryTM {
    private String orderid;
    private String delid;
    private String delstatus;
    private String duedate;
    private String deldate;
    private String location;
    private String empid;
}
