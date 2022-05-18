package ru.lihogub.softwaredesigntechnologies.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lihogub.softwaredesigntechnologies.entity.Airport;
import ru.lihogub.softwaredesigntechnologies.repository.AirportRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class AirportController {
    private final AirportRepository airportRepository;

    @GetMapping
    ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportRepository.findAll());
    }
}
