package com.PSI.TicketService.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {

    @GetMapping("/tickets/events_availability/{eventId}")
    public String checkAvailability(@PathVariable String eventId) {
        return "1";
    }

    @PostMapping("/tickets/generate_tickets")
    public String createTickets(@RequestBody String event){
        return "2";
    }

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

}
