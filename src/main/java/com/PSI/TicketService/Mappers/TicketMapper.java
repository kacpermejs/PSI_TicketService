package com.PSI.TicketService.Mappers;

import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Models.TicketEntity;
import org.springframework.context.annotation.Bean;

/*
public class TicketDTO {
    private long id;
    private TicketReservationState reservationState;
    private long seatId;
    private String price;
}
*/

public class TicketMapper {

    public static TicketDTO MapTicketEntityToTicketDTO(TicketEntity ticketEntity) {

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticketEntity.getId());
        ticketDTO.setReservationState(ticketEntity.getTicketState());
        ticketDTO.setSeatId(ticketEntity.getSeatId());
        ticketDTO.setPrice(ticketEntity.getPrice());
        return ticketDTO;
    }

}
