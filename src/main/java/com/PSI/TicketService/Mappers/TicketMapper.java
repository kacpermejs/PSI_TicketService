package com.PSI.TicketService.Mappers;

import com.PSI.TicketService.DTOs.Event.Address;
import com.PSI.TicketService.DTOs.Event.EventTicketDTO;
import com.PSI.TicketService.DTOs.Event.SchematicTicketDTO;
import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.DTOs.TicketOrderDTO;
import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Enums.TicketValidity;
import com.PSI.TicketService.Models.TicketEntity;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Map;

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

    public static TicketOrderDTO mapToSpecific(TicketEntity ticket, Map<Long, SchematicTicketDTO> map,EventTicketDTO event, Long orderId){
        TicketOrderDTO ticketOrderDTO = new TicketOrderDTO();

        ticketOrderDTO.setId(ticket.getId());

        ticketOrderDTO.setOrderId(orderId);

        ticketOrderDTO.setEventName(event.getEventName());
        ticketOrderDTO.setEventStart(event.getEventStart());
        ticketOrderDTO.setVenueName(event.getVenueName());
        ticketOrderDTO.setVenueFacilityName(event.getVenueFacilityName());
        ticketOrderDTO.setVenueAddress(event.getVenueAddress());

        //SchematicTicketDTO schematicTicketDTO = event.getSchematicTicketDTOs();
        SchematicTicketDTO seat = map.get(ticket.getSeatId());
        String seatInfo = "Seat " + seat.getSeatColumn() + " Row " + seat.getSeatRow();
        String sectionInfo = "Section " + seat.getSectionLabel();

        ticketOrderDTO.setSeatInfo(seatInfo);
        ticketOrderDTO.setSectionInfo(sectionInfo);

        ticketOrderDTO.setTicketValidity(ticket.getValidity());
        ticketOrderDTO.setTicketReservationState(ticket.getTicketState());
        ticketOrderDTO.setPrice(ticket.getPrice());
        ticketOrderDTO.setQrCode(ticket.getQrCode());

        return ticketOrderDTO;
    }

}
