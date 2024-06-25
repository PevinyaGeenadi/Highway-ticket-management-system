package lk.ijse.ticketservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    @GetMapping
    public String get(){
        return "ticketservice get method invoked";
    }

}
