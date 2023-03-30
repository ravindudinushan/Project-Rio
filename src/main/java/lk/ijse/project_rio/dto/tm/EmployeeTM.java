package lk.ijse.project_rio.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeTM {
    private String id;
    private String name;
    private String nic;
    private String dob;
    private String address;
    private String email;
    private String job;
    private String contact;
    private Double salary;
}
