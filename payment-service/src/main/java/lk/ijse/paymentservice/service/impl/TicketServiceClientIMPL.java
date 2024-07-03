package lk.ijse.paymentservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.paymentservice.service.TicketServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceClientIMPL implements TicketServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceClientIMPL.class);
    private final RestTemplate restTemplate;

    @Override
    public boolean isTicketExists(String ticketId) {
        try {
            String url = "http://ticket-service/api/v1/ticket/ticketExists/" + ticketId;
            Boolean userExists = restTemplate.getForObject(url, Boolean.class);
            logger.info("ticket Exists: {}", userExists);
            return userExists != null && userExists;
        } catch (Exception e) {
            logger.error("Error checking if ticket exists", e);
        }
        return false;
    }
}
