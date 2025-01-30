package com.PSI.TicketService.DTOs;

import com.PSI.TicketService.Enums.TicketReservationState;
import lombok.Data;

@Data
public class TicketDTO {
    private long id;
    private TicketReservationState reservationState;
    private long seatId;
    private double price;
}
