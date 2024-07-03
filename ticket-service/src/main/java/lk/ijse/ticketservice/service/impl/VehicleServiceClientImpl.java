package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.service.VehicleServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
@RequiredArgsConstructor
public class VehicleServiceClientImpl implements VehicleServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceClientImpl.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isVehicleExists(String vehicleId) {
        try {
            String url = "http://vehicle-service/api/v1/vehicle/exists/" + vehicleId;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("vehicle Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if vehicle exists", e);
        }
        return false;
    }
}
