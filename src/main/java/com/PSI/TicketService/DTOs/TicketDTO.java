package com.PSI.TicketService.DTOs;

import com.PSI.TicketService.Enums.TicketReservationState;

public class TicketDTO {
    private long id;
    private TicketReservationState reservationState;
    private long seatId;
    private String price;
}
