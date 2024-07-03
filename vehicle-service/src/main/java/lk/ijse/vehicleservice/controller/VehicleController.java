package lk.ijse.vehicleservice.controller;
import lk.ijse.vehicleservice.dto.VehicleDTO;
import lk.ijse.vehicleservice.service.UserServiceClient;
import lk.ijse.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;


import java.util.List;


@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    private final UserServiceClient userServiceClient;

    @GetMapping("/health")
    public String healthTest() {
        return "Vehicle Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveVehicle(@Validated @RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult) {
        logger.info("Saving vehicle details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            if (!userServiceClient.isUserExists(vehicleDTO.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with ID: " + vehicleDTO.getUserId());
            }
            VehicleDTO savedVehicle = vehicleService.saveVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
        } catch (Exception exception) {
            logger.error("Error saving Vehicle: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Vehicle saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllVehicles() {
        logger.info("Fetching all Vehicles");
        try {
            List<VehicleDTO> vehicleDTOList = vehicleService.getAllVehicle();
            return ResponseEntity.ok(vehicleDTOList);
        } catch (Exception exception) {
            logger.error("Error fetching all Vehicles: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch Vehicles.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable String vehicleId) {
        logger.info("Fetching Vehicle by ID: {}", vehicleId);
        try {
            VehicleDTO vehicleDTO = vehicleService.getSelectedVehicle(vehicleId);
            return ResponseEntity.ok(vehicleDTO);
        } catch (Exception exception) {
            logger.error("Error fetching Vehicle by ID: " + vehicleId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch Vehicle by ID: " + vehicleId + ".\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String vehicleId) {
        logger.info("Deleting Vehicle by ID: {}", vehicleId);
        try {
            vehicleService.deleteVehicle(vehicleId);
            return ResponseEntity.ok("Vehicle deleted successfully");
        } catch (Exception exception) {
            logger.error("Error deleting Vehicle by ID: " + vehicleId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete Vehicle by ID: " + vehicleId + ".\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable String vehicleId, @Validated @RequestBody VehicleDTO vehicleDTO, BindingResult bindingResult) {
        logger.info("Updating Vehicle by ID: {}", vehicleId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            if (!userServiceClient.isUserExists(vehicleDTO.getUserId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with ID: " + vehicleDTO.getUserId());
            }
            vehicleService.updateVehicle(vehicleId, vehicleDTO);
            return ResponseEntity.ok("Vehicle updated successfully");
        } catch (Exception exception) {
            logger.error("Error updating Vehicle by ID: {}", vehicleId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to update Vehicle by ID: " + vehicleId + ".\nMore Details\n" + exception);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehicleByUserId(@PathVariable String userId) {
        logger.info("Fetching Vehicles by User ID: {}", userId);
        try {
            List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleByUserId(userId);
            return ResponseEntity.ok(vehicleDTOList);
        } catch (Exception exception) {
            logger.error("Error fetching Vehicles by User ID: {}", userId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch Vehicles by User ID: " + userId + ".\nMore Details\n" + exception);
        }
    }

    @GetMapping("/exists/{vehicleId}")
    public ResponseEntity<?> isVehicleExists(@PathVariable String vehicleId) {
        logger.info("Checking Vehicle exists by ID: {}", vehicleId);
        try {
            boolean isVehicleExists = vehicleService.isVehicleExists(vehicleId);
            return ResponseEntity.ok(isVehicleExists);
        } catch (Exception exception) {
            logger.error("Error checking Vehicle exists by ID: {}", vehicleId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check Vehicle exists by ID: " + vehicleId + ".\nMore Details\n" + exception);
        }
    }
}
