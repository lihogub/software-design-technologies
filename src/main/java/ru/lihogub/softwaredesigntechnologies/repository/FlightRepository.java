package ru.lihogub.softwaredesigntechnologies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lihogub.softwaredesigntechnologies.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
