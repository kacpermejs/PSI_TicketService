package com.PSI.TicketService.DTOs;

import com.PSI.TicketService.DTOs.Event.Address;
import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Enums.TicketValidity;
import lombok.Data;

import java.sql.Timestamp;

// Ticket -> Order
@Data
public class TicketOrderDTO {

    //ticketId
    private long id;
    //orderId
    private Long orderId;

    //eventInfo
    private String eventName;
    private Timestamp eventStart;
    private String venueName;
    private String venueFacilityName;
    private Address venueAddress;

    //seatInfo
    private String seatInfo;
    private String sectionInfo;

    //ticketInfo
    private TicketValidity ticketValidity;
    private TicketReservationState ticketReservationState;
    private double price;
    private String qrCode;

}
