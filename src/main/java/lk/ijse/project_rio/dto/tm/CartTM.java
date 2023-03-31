package lk.ijse.project_rio.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CartTM {
    private String itemId;
    private String itemName;
    private String category;
    private String quantity;
    private String delivery;
    private Double unitPrice;
    private Double total;
    private Button btn;
}
