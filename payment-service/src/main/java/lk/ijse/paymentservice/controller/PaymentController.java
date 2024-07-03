package lk.ijse.paymentservice.controller;
import lk.ijse.paymentservice.dto.PaymentDTO;
import lk.ijse.paymentservice.service.PaymentService;
import lk.ijse.paymentservice.service.TicketServiceClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    private final TicketServiceClient ticketServiceClient;

    @GetMapping("/checkPayment")
    public String userCheck() {
        return "Payment Service is up and running ! ";

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> savePayment(@Validated @RequestBody PaymentDTO paymentDTO, BindingResult bindingResult) {
        logger.info("Saving payment details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            if (!ticketServiceClient.isTicketExists(paymentDTO.getTicketId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket not found with ID: " + paymentDTO.getTicketId());
            }
            PaymentDTO savedPayment = paymentService.savePayment(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
        } catch (Exception exception) {
            logger.error("Error saving Payment: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Payment saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPayments() {
        logger.info("Getting all payments");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.getAllPayment());
        } catch (Exception exception) {
            logger.error("Error getting all payments: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Payments not found.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable String paymentId) {
        logger.info("Getting payment by ID: " + paymentId);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(paymentService.getSelectedPayment(paymentId));
        } catch (Exception exception) {
            logger.error("Error getting payment by ID: " + paymentId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Payment not found with ID: " + paymentId + ".\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePaymentById(@PathVariable String paymentId) {
        logger.info("Deleting payment by ID: " + paymentId);
        try {
            paymentService.deletePayment(paymentId);
            return ResponseEntity.status(HttpStatus.OK).body("Payment deleted successfully");
        } catch (Exception exception) {
            logger.error("Error deleting payment by ID: " + paymentId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Payment not found with ID: " + paymentId + ".\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<?> updatePayment(@PathVariable String paymentId, @RequestBody PaymentDTO paymentDTO) {
        logger.info("Updating payment by ID: " + paymentId);
        try {
            if (!ticketServiceClient.isTicketExists(paymentDTO.getTicketId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ticket not found with ID: " + paymentDTO.getTicketId());
            }
            paymentService.updatePayment(paymentId, paymentDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Payment updated successfully");
        } catch (Exception exception) {
            logger.error("Error updating payment by ID: " + paymentId, exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Payment not found with ID: " + paymentId + ".\nMore Details\n" + exception);
        }
    }
}
