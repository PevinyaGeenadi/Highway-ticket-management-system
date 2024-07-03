package lk.ijse.ticketservice.service;
import lk.ijse.ticketservice.dto.TicketDTO;

import java.util.List;
public interface TicketService {
    TicketDTO saveTicket(TicketDTO ticketDTO);
    void deleteTicket(String ticketId);
    TicketDTO getSelectedTicket(String ticketId);
    List<TicketDTO> getAllTicket();
    void updateTicket(String ticketId,TicketDTO ticketDTO);
    List<TicketDTO> getTicketByUserId(String userId);
}
