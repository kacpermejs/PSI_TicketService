package com.PSI.TicketService.Controllers;

import com.PSI.TicketService.DTOs.OrderTicketDTO;
import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.DTOs.TicketOrderDTO;
import com.PSI.TicketService.Enums.TicketReservationState;
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
        //System.out.println(tickets);
        return tickets;
    }

    @GetMapping("/tickets/events_availability_unreserved/{eventId}")
    public List<TicketDTO> checkAvailabilityUnreservedTickets(@PathVariable Long eventId) {

        List<TicketDTO> tickets = ticketService.getUnreservedTicketsForEvent(eventId);
        //System.out.println(tickets);
        return tickets;
    }

    // event event event event event event event event event event event


    // order order order order order order order order order order order


    // gets details of tickets for specific order
    @GetMapping("/tickets/order/{orderId}")
    public List<TicketOrderDTO> changeStatusCreated(@PathVariable Long orderId){

        List<TicketOrderDTO> tickets = ticketService.getTicketsForOrder(orderId);
        //System.out.println(tickets);
        return tickets;
    }

    // checks whether all ticketId are up for grab for an order.
    // If so, it changes Ticket state to Reserved and sets Ticket's orderId field to the provided in OrderTicketDTO
    @PostMapping("/tickets/order/check")
    public boolean checkOrder(@RequestBody OrderTicketDTO ticketIds){
        List<Boolean> states = ticketService.checkState(ticketIds.getTicketIds());
        boolean allUnreserved = states.get(0);
        boolean allExist = states.get(1);

        // RESERVE tickets
        if (allUnreserved) {
            ticketService.changeTicketReservationState(ticketIds.getTicketIds(), ticketIds.getTicketReservationState(), ticketIds.getOrderId());
            System.out.println("Reservation for order " + ticketIds.getOrderId() + " made");
        }

        // docelowo ResponseEntity... i jakieś InvalidValueException itp
        return allUnreserved;
    }

    // reservation timeout func - changes order_id back to null and ticket_state to unreserved (fields orderId and state dont matter for now:/
    @PostMapping("/tickets/order/remove")
    public void removeOrder(@RequestBody OrderTicketDTO ticketIds){
        ticketService.changeTicketReservationState(ticketIds.getTicketIds(), TicketReservationState.UNRESERVED, null);
    }

    //changes all tickets ticketStatus to PAID
    @PostMapping("/tickets/order/pay/{orderId}")
    public void payForOrder(@PathVariable Long orderId){
        ticketService.pay(orderId);
    }

    // order order order order order order order order order order order

}
