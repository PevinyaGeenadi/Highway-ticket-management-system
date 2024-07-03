package lk.ijse.paymentservice.dao;

import lk.ijse.paymentservice.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<PaymentEntity, String> {
    List<PaymentEntity> findByTicketId(String ticketId);
}
