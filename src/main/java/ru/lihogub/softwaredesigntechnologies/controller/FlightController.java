package ru.lihogub.softwaredesigntechnologies.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lihogub.softwaredesigntechnologies.entity.Flight;
import ru.lihogub.softwaredesigntechnologies.repository.FlightRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/flights")
public class FlightController {
    private final FlightRepository flightRepository;

    @GetMapping
    ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightRepository.findAll());
    }

    @GetMapping("/{flightId}")
    ResponseEntity<Flight> getFlightByFlightId(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightRepository.getById(flightId));
    }
}