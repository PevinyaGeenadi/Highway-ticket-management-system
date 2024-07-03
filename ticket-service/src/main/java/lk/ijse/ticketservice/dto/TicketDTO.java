package lk.ijse.ticketservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDTO {
    private String ticketId;
    @NotBlank(message = "Ticket Issue Date is must be required")
    private Date ticketIssueDate;
    @NotBlank(message = "Ticket Status is must be required")
    private String ticketStatus;
    @NotBlank(message = "Vehicle Id is must be required")
    private String vehicleId;
    @NotBlank(message = "User Id is must be required")
    private String userId;
}
