package lk.ijse.project_rio.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CustomerTM {
    private String id;
    private String name;
    private String address;
    private String email;
    private String contact;
}
