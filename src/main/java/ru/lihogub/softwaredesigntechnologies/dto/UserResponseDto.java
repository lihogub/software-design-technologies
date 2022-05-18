package ru.lihogub.softwaredesigntechnologies.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String role;
    private String firstname;
    private String middlename;
    private String lastname;
}
