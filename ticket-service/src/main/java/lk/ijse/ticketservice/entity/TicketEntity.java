package lk.ijse.ticketservice.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ticket")
@Entity
public class TicketEntity implements SuperEntity{
    @Id
    private String ticketId;
    private Date ticketIssueDate;
    private String ticketStatus;
    private String vehicleId;
    private String userId;
}
