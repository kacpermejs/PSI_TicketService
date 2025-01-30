package com.PSI.TicketService.Models;


import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Enums.TicketValidity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TicketEntity {
    @Id
    private long id;

    @Column(nullable=false)
    private long seatId;

    @Column(nullable=true)
    private Long orderId;

    @Column(nullable=false)
    private long eventId;

    @Column(nullable=false)
    private long sectionId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TicketValidity validity;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable=false)
    private TicketReservationState ticketState;

    // idk, było w modelu informacyjnym
    //private int number;

    @Column(nullable=false)
    private double price;

    private String qrCode;

}
