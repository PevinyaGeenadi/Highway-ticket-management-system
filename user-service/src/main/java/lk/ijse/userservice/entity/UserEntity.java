package lk.ijse.userservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Entity
public class UserEntity implements SuperEntity{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
