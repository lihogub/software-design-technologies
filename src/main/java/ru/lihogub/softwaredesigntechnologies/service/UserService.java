package ru.lihogub.softwaredesigntechnologies.service;

import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserResponseDto;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UserNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UsernameAlreadyExistsException;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto) throws UsernameAlreadyExistsException, RoleNotFoundException;

    @Transactional(readOnly = true)
    UserResponseDto getUserById(Long userId) throws UserNotFoundException, UserNotFoundException;

    @Transactional(readOnly = true)
    UserResponseDto getUserByUsername(String username) throws UserNotFoundException;

    @Transactional(readOnly = true)
    List<UserResponseDto> getAllUsers();
}
