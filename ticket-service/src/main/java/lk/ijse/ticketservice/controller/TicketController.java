package lk.ijse.ticketservice.controller;

import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.service.UserServiceClient;
import lk.ijse.ticketservice.service.VehicleServiceClient;
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
        return "Ticket Service is up and running !";
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
    @GetMapping
    public ResponseEntity<?> getAllTickets(){
        logger.info("Fetching all Tickets");
        try{
            return ResponseEntity.ok(ticketService.getAllTicket());
        }catch(Exception exception){
            logger.error("Error fetching Tickets: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Tickets fetching Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getTicketById(@PathVariable String ticketId){
        logger.info("Fetching Ticket by ID: {}", ticketId);
        try{
            TicketDTO ticketDTO = ticketService.getSelectedTicket(ticketId);
            if(ticketDTO == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ticket not found with ID: " + ticketId);
            }
            return ResponseEntity.ok(ticketDTO);
        }catch(Exception exception){
            logger.error("Error fetching Ticket by ID: {}", ticketId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Ticket fetching Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable String ticketId){
        logger.info("Deleting Ticket by ID: {}", ticketId);
        try{
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok("Ticket deleted successfully");
        }catch(Exception exception){
            logger.error("Error deleting Ticket by ID: {}", ticketId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Ticket deletion Unsuccessfully.\nMore Details\n" + exception);
        }
    }


    @PutMapping("/{ticketId}")
    public ResponseEntity<?> updateTicket(@PathVariable String ticketId, @Validated @RequestBody TicketDTO ticketDTO, BindingResult bindingResult){
        logger.info("Updating Ticket by ID: {}", ticketId);
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
            ticketService.updateTicket(ticketId, ticketDTO);
            return ResponseEntity.ok("Ticket updated successfully");
        }catch(Exception exception){
            logger.error("Error updating Ticket by ID: {}", ticketId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Ticket updating Unsuccessfully.\nMore Details\n" + exception);
        }
    }
    @GetMapping("/ticketExists/{ticketId}")
    public ResponseEntity<?> isTicketExists(@PathVariable String ticketId){
        logger.info("Checking Ticket exists by ID: {}", ticketId);
        try{
            return ResponseEntity.ok(ticketService.isTicketExists(ticketId));
        }catch(Exception exception){
            logger.error("Error checking Ticket exists by ID: {}", ticketId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Ticket checking Unsuccessfully.\nMore Details\n" + exception);
        }
    }

}
