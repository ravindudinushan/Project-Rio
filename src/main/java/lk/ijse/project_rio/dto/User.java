package lk.ijse.project_rio.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String name;
    private String password;
    private String email;
    private String jobTitle;
}
