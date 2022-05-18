package ru.lihogub.softwaredesigntechnologies.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lihogub.softwaredesigntechnologies.entity.Flight;
import ru.lihogub.softwaredesigntechnologies.entity.Ticket;
import ru.lihogub.softwaredesigntechnologies.repository.TicketRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private final TicketRepository ticketRepository;

    @GetMapping
    ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    @GetMapping("/{ticketId}")
    ResponseEntity<Ticket> getTicketByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(ticketRepository.getById(ticketId));
    }
}