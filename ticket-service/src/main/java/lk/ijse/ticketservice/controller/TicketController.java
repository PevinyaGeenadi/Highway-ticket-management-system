package lk.ijse.ticketservice.controller;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;
    private final UserServiceClient userServiceClient;
    private final VehicleServiceClient vehicleServiceClient;
    @GetMapping("/checkTicket")
    public String userCheck(){
        return "Ticket Check Successful!";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveTicket(@Validated @RequestBody TicketDTO ticketDTO, BindingResult bindingResult){
        logger.info("Saving ticket details");
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try{
            if(!userServiceClient.isUserExists(ticketDTO.getUserId())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found with ID: " + ticketDTO.getUserId());
            }
            if(!vehicleServiceClient.isVehicleExists(ticketDTO.getVehicleId())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle not found with ID: " + ticketDTO.getVehicleId());
            }
            TicketDTO savedTicket = ticketService.saveTicket(ticketDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTicket);
        }catch(Exception exception){
            logger.error("Error saving Ticket: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Ticket saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

}
