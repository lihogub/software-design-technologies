package ru.lihogub.softwaredesigntechnologies.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserResponseDto;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.entity.User;
import ru.lihogub.softwaredesigntechnologies.repository.RoleRepository;


import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    RoleRepository roleRepository;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", source = "role.name")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "middlename", source = "middlename")
    @Mapping(target = "lastname", source = "lastname")
    public abstract UserResponseDto userToUserResponseDto(User user);

    public abstract List<UserResponseDto> userListToUserResponseDtoList(List<User> userList);

    public User userRequestDtoToUser(UserRequestDto userRequestDto) throws RoleNotFoundException {
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setPassword(userRequestDto.getPassword());
        user.setFirstname(userRequestDto.getFirstname());
        user.setMiddlename(userRequestDto.getMiddlename());
        user.setLastname(userRequestDto.getLastname());
        user.setRole(
                roleRepository
                        .findByName("USER")
                        .orElseThrow(() -> new RoleNotFoundException("name: USER"))
        );
        return user;
    }
}
