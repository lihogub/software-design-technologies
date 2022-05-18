package ru.lihogub.softwaredesigntechnologies.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserResponseDto;
import ru.lihogub.softwaredesigntechnologies.entity.User;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UserNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UsernameAlreadyExistsException;
import ru.lihogub.softwaredesigntechnologies.mapper.UserMapper;
import ru.lihogub.softwaredesigntechnologies.repository.UserRepository;
import ru.lihogub.softwaredesigntechnologies.service.UserService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws UsernameAlreadyExistsException, RoleNotFoundException {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new UsernameAlreadyExistsException("username: " + userRequestDto.getUsername());
        }
        User newUser = userMapper.userRequestDtoToUser(userRequestDto);
        User savedUser = userRepository.save(newUser);
        return userMapper.userToUserResponseDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("id: " + userId));

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("username: " + username));

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userMapper.userListToUserResponseDtoList(userRepository.findAll());
    }
}
