package ru.lihogub.softwaredesigntechnologies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lihogub.softwaredesigntechnologies.entity.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
