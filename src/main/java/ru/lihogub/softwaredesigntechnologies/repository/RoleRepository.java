package ru.lihogub.softwaredesigntechnologies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lihogub.softwaredesigntechnologies.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
