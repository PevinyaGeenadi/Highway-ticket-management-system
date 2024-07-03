package lk.ijse.vehicleservice.controller;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    @GetMapping("/health")
    public String healthTest() {
        return "Vehicle Service is up and running";
    }
}
