package ru.lihogub.softwaredesigntechnologies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lihogub.softwaredesigntechnologies.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
