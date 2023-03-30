package lk.ijse.project_rio.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class InventoryTM {
    private String id;
    private String name;
    private String category;
    private Double unitPrice;
    private String qtyOnHand;
}
