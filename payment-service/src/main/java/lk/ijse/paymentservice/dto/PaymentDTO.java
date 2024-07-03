package lk.ijse.paymentservice.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    @NotNull(message = "Amount is must be required")
    @Positive(message = "Amount must be positive")
    private double amount;
    @NotNull(message = "Payment Date is must be required")
    private String paymentDate;
    @NotBlank(message = "Payment Status is must be required")
    private String paymentStatus;
    @NotBlank(message = "Ticket ID is must be required")
    private String ticketId;
}
