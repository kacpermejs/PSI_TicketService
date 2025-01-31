package com.PSI.TicketService.Service;

import com.PSI.TicketService.DTOs.Event.EventTicketDTO;
import com.PSI.TicketService.DTOs.Event.SchematicTicketDTO;
import com.PSI.TicketService.DTOs.TicketDTO;
import com.PSI.TicketService.DTOs.TicketOrderDTO;
import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Mappers.TicketMapper;
import com.PSI.TicketService.Models.TicketEntity;
import com.PSI.TicketService.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Value("${eventService.address}")
    private String eventAdddress;
    @Value("${eventService.port}")
    private String eventPort;

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;

    }

    public List<TicketDTO> getTicketsForEvent(Long eventId) {
        List<TicketEntity> tickets = ticketRepository.findAllByEventId(eventId);
        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(TicketMapper::MapTicketEntityToTicketDTO)
                .collect(Collectors.toList());

        return ticketDTOs;
    }

    public List<TicketDTO> getUnreservedTicketsForEvent(Long eventId) {
        List<TicketEntity> tickets = ticketRepository.findAllByEventIdAndTicketState(eventId, TicketReservationState.UNRESERVED);

        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(TicketMapper::MapTicketEntityToTicketDTO)
                .collect(Collectors.toList());

        return ticketDTOs;
    }


    public List<Boolean> checkState(List<Long> ticketIds){
        boolean allUnreserved = true;
        boolean allExist = true;

        for (Long id: ticketIds) {

            Optional<TicketEntity> ticket = ticketRepository.findById(id);

            if (ticket.isPresent()){
                TicketEntity ticketEntity = ticket.get();

                TicketReservationState ticketReservationState = ticketEntity.getTicketState();
                System.out.println(ticketReservationState);
                if (ticketReservationState != TicketReservationState.UNRESERVED){
                    System.out.println(ticketEntity);
                    allUnreserved = false;
                }
            }
            else {
                allExist = false;
            }
        }

        return List.of(allUnreserved, allExist);
    }

    public void changeTicketReservationState(List<Long> ticketIds, TicketReservationState ticketReservationState, Long orderId){
        for (Long id: ticketIds) {
            TicketEntity ticketEntity = ticketRepository.findById(id).get();

            ticketEntity.setTicketState(ticketReservationState);
            ticketEntity.setOrderId(orderId);

            ticketRepository.save(ticketEntity);
        }
    }

    // ORDER ORDER ORDER

    public List<TicketOrderDTO> getTicketsForOrder(Long orderId) {

        List<TicketEntity> tickets = ticketRepository.findAllByOrderId(orderId);
        List<TicketOrderDTO> ticketOrderDTOS = new ArrayList<>();

        Long eventId = tickets.getFirst().getEventId();
        EventTicketDTO event = getEvent(eventId);


        Map<Long, SchematicTicketDTO> schematicMap = event.getSchematicTicketDTOs()
                .stream()
                .collect(Collectors.toMap(SchematicTicketDTO::getId, Function.identity() ));


        for (TicketEntity ticket : tickets) {
            TicketOrderDTO ticketOrderDTO = TicketMapper.mapToSpecific(ticket, schematicMap, event, orderId);
            ticketOrderDTOS.add(ticketOrderDTO);
        }


        return ticketOrderDTOS;
    }

    public EventTicketDTO getEvent(long eventId){

        //String address = "localhost";

        String url = "http://" + eventAdddress +":" + eventPort + "/events/event_ticket/" + eventId;
        //System.out.println("URL" + url);
        RestTemplate restTemplate = new RestTemplate();

        EventTicketDTO event = restTemplate.getForObject(url, EventTicketDTO.class);
        return event;
    }

    public void pay(Long orderId) {

        List<TicketEntity> tickets = ticketRepository.findAllByOrderId(orderId);
        for (TicketEntity ticket: tickets){
            ticket.setTicketState(TicketReservationState.PAID);
            ticketRepository.save(ticket);
        }
    }

}
