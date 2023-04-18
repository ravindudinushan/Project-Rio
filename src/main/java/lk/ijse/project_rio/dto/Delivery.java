package lk.ijse.project_rio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Delivery {
    private String delid;
    private String delsts;
    private String loc;
    private String deldate;
    private String duedate;
    private String ordid;
    private String empid;
}
