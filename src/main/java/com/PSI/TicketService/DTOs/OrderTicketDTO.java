package com.PSI.TicketService.DTOs;

import com.PSI.TicketService.Enums.TicketReservationState;
import lombok.Data;

import java.util.List;

// Order -> Ticket
// przesyłamy do endpointa, żedy sprawdzić dostępność biletów i ewentualnie zmieniać ich stan rezerwacje, orderId itp
@Data
public class OrderTicketDTO {
    private Long orderId;
    private List<Long> ticketIds;
    private TicketReservationState ticketReservationState;
}
