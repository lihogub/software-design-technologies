package ru.lihogub.softwaredesigntechnologies.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import ru.lihogub.softwaredesigntechnologies.dto.AuthRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserResponseDto;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UsernameAlreadyExistsException;

@Service
public interface AuthService {
    String authenticate(AuthRequestDto authRequestDto) throws AuthenticationException;
    UserResponseDto register(UserRequestDto userRequestDto) throws UsernameAlreadyExistsException, RoleNotFoundException;
}
