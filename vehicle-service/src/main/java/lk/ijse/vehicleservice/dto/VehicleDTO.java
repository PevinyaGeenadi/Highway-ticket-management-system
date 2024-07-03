package lk.ijse.vehicleservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {
    private String vehicleId;
    @NotBlank(message = "Vehicle License plate is must be required")
    private String licensePlate;
    @NotBlank(message = "Vehicle Brand is must be required")
    private String brand;
    @NotBlank(message = "Vehicle Model is must be required")
    private String model;
    @NotBlank(message = "Vehicle User ID is must be required")
    private String userId;
}
