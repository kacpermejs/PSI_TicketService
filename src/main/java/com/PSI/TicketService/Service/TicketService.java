package com.PSI.TicketService.Service;

import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Mappers.TicketMapper;
import com.PSI.TicketService.Models.TicketEntity;
import com.PSI.TicketService.Repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;

    }

    public List<TicketDTO> getTicketsForEvent(Long eventId) {
        List<TicketEntity> tickets = ticketRepository.findAllByEventId(eventId);
        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(TicketMapper::MapTicketEntityToTicketDTO)
                .collect(Collectors.toList());

        return ticketDTOs;
    }

    public List<TicketDTO> getUnreservedTicketsForEvent(Long eventId) {
        List<TicketEntity> tickets = ticketRepository.findAllByEventIdAndTicketState(eventId, TicketReservationState.UNRESERVED);

        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(TicketMapper::MapTicketEntityToTicketDTO)
                .collect(Collectors.toList());

        return ticketDTOs;
    }

}
