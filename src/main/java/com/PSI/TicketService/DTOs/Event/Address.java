package com.PSI.TicketService.DTOs.Event;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Address {

    private long id;
    private String city;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String postalCode;
    private String stateOrRegion;
}
