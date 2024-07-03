package lk.ijse.vehicleservice.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "vehicle")
@Entity
public class VehicleEntity implements SuperEntity{
    @Id
    private String vehicleId;
    private String licensePlate;
    private String brand;
    private String model;
    private String userId;
}
