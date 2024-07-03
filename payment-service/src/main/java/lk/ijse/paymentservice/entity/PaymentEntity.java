package lk.ijse.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Payment")
@Entity
public class PaymentEntity implements SuperEntity{
    @Id
    private String paymentId;
    private double amount;
    private String paymentDate;
    private String paymentStatus;
    private String ticketId;
}
