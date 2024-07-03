package lk.ijse.paymentservice.service;

import lk.ijse.paymentservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO savePayment(PaymentDTO paymentDTO);

    void deletePayment(String paymentId);

    PaymentDTO getSelectedPayment(String paymentId);

    List<PaymentDTO> getAllPayment();

    void updatePayment(String paymentId, PaymentDTO paymentDTO);

    List<PaymentDTO> getPaymentTicketId(String ticketId);
}
