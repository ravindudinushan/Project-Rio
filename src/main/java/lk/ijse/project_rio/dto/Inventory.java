package lk.ijse.project_rio.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Inventory {
    private String id;
    private String name;
    private String category;
    private Double unitPrice;
    private String qtyOnHand;
}
