package ru.lihogub.softwaredesigntechnologies.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(
            min = 5,
            max = 32,
            message = "Length of username must be from {min} to {max} characters"
    )
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(
            min = 5,
            max = 32,
            message = "Length of password must be from {min} to {max} characters"
    )
    private String password;

    @NotBlank(message = "Firstname cannot be blank")
    private String firstname;

    @NotEmpty(message = "Middlename cannot be blank")
    private String middlename;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastname;
}
