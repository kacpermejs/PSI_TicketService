package com.PSI.TicketService.DTOs.Event;

import lombok.Data;

@Data
public class SchematicTicketDTO {
    private long id;
    private String name;
    private String label;
    private String seatRow;
    private String seatColumn;
    private String sectionLabel;;
}
