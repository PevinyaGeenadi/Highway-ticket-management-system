package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService {
    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        return null;
    }

    @Override
    public void deleteTicket(String ticketId) {

    }

    @Override
    public TicketDTO getSelectedTicket(String ticketId) {
        return null;
    }

    @Override
    public List<TicketDTO> getAllTicket() {
        return List.of();
    }

    @Override
    public void updateTicket(String ticketId, TicketDTO ticketDTO) {

    }

    @Override
    public List<TicketDTO> getTicketByUserId(String userId) {
        return List.of();
    }
}
