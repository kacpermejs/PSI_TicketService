package com.PSI.TicketService.Controllers;

import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.Models.TicketEntity;
import com.PSI.TicketService.Service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // event event event event event event event event event event event

    @GetMapping("/tickets/events_availability/{eventId}")
    public List<TicketDTO> checkAvailability(@PathVariable Long eventId) {

        List<TicketDTO> tickets = ticketService.getTicketsForEvent(eventId);
        System.out.println(tickets);
        return tickets;
    }

    @GetMapping("/tickets/events_availability_unreserved/{eventId}")
    public List<TicketDTO> checkAvailabilityUnreservedTickets(@PathVariable Long eventId) {

        List<TicketDTO> tickets = ticketService.getUnreservedTicketsForEvent(eventId);
        System.out.println(tickets);
        return tickets;
    }

    // event event event event event event event event event event event


    // order order order order order order order order order order order

    @PostMapping("/tickets/order_created")
    public String changeStatusCreated(@RequestBody String order){
        return "3";
    }

    @PostMapping("/tickets/order_paid")
    public String changeStatusPaid(@RequestBody String order){
        return "4";
    }

    @PostMapping("/tickets/order_deserted")
    public String changeStatusDeserted(@RequestBody String order){
        return "5";
    }

    // order order order order order order order order order order order


}
