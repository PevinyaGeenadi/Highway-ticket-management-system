package lk.ijse.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String id;
    @NotBlank(message = "First Name is must")
    private String firstName;
    @NotBlank(message = "Last Name is must")
    private String lastName;
    @NotBlank(message = "Email is must")
    private String email;
    @NotBlank(message = "Password is must")
    private String password;

}
