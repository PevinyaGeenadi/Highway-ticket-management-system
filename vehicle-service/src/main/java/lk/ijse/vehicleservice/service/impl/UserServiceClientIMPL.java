package lk.ijse.vehicleservice.service.impl;

import lk.ijse.vehicleservice.service.UserServiceClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceClientIMPL implements UserServiceClient {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceClientIMPL.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isUserExists(String userId) {
        try {
            String url = "http://user-service/api/v1/user/userExists/" + userId;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("User Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if user exists", e);
        }
        return false;
    }
}
