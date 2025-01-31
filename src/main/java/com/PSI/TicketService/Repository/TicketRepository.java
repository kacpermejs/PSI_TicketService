package com.PSI.TicketService.Repository;

import com.PSI.TicketService.Enums.TicketReservationState;
import com.PSI.TicketService.Models.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    List<TicketEntity> findAllByEventId(Long eventId);
    List<TicketEntity> findAllByEventIdAndTicketState(Long eventId, TicketReservationState ticketReservationState);
    List<TicketEntity> findAllByOrderId(Long orderId);
    //List<TicketEntity> findById(Long Id);
}
