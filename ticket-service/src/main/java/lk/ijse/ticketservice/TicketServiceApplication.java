package lk.ijse.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TicketServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketServiceApplication.class, args);
    }

}
