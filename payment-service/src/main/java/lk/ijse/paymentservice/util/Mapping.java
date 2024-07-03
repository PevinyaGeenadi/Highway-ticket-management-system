package lk.ijse.paymentservice.util;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {
    private final ModelMapper mapper;
    Mapping(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PaymentDTO toPaymentDTO (PaymentEntity paymentEntity) {
        return mapper.map(paymentEntity, PaymentDTO.class);
    }

    public PaymentEntity toPaymentEntity (PaymentDTO paymentDTO) {
        return mapper.map(paymentDTO, PaymentEntity.class);
    }

    public List<PaymentDTO> toPaymentDTOList(List<PaymentEntity> paymentEntities) {
        return paymentEntities.stream()
                .map(paymentEntity -> mapper.map(paymentEntity, PaymentDTO.class))
                .collect(Collectors.toList());
    }
}
