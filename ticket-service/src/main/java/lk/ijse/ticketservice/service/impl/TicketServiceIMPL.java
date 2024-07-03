package lk.ijse.ticketservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.ticketservice.dao.TicketRepo;
import lk.ijse.ticketservice.dto.TicketDTO;
import lk.ijse.ticketservice.entity.TicketEntity;
import lk.ijse.ticketservice.service.TicketService;
import lk.ijse.ticketservice.util.Mapping;
import lk.ijse.ticketservice.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketServiceIMPL implements TicketService {

    private final TicketRepo ticketRepo;
    private final Mapping mapping;

    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO) {
        long ticketCount = ticketRepo.count();
        String ticketId = String.format("T%04d", ticketCount + 1);
        ticketDTO.setTicketId(ticketId);
        return mapping.toTicketDTO(ticketRepo.save(mapping.toTicketEntity(ticketDTO)));
    }

    @Override
    public void deleteTicket(String ticketId) {
        if (ticketRepo.existsById(ticketId)){
            ticketRepo.deleteById(ticketId);
        } else throw new NotFoundException("Ticket not found" + ticketId);
    }

    @Override
    public TicketDTO getSelectedTicket(String ticketId) {
        TicketEntity ticketEntity = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found" + ticketId));
        return mapping.toTicketDTO(ticketEntity);
    }

    @Override
    public List<TicketDTO> getAllTicket() {
        return mapping.toTicketDTOList(ticketRepo.findAll());
    }

    @Override
    public void updateTicket(String ticketId, TicketDTO ticketDTO) {
        TicketEntity ticketEntity = ticketRepo.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found" + ticketId));
        if (ticketEntity != null) {
            ticketEntity.setTicketIssueDate(ticketDTO.getTicketIssueDate());
            ticketEntity.setTicketStatus(ticketDTO.getTicketStatus());
            ticketEntity.setVehicleId(ticketDTO.getVehicleId());
            ticketEntity.setUserId(ticketDTO.getUserId());
            ticketRepo.save(ticketEntity);
        }
    }

    @Override
    public List<TicketDTO> getTicketByUserId(String userId) {
        return mapping.toTicketDTOList(ticketRepo.findByUserId(userId));
    }

    @Override
    public List<TicketDTO> getTicketByVehicleId(String vehicleId) {
        return mapping.toTicketDTOList(ticketRepo.findByVehicleId(vehicleId));
    }
}
