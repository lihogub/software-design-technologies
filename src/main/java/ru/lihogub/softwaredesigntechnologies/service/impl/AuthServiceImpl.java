package ru.lihogub.softwaredesigntechnologies.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lihogub.softwaredesigntechnologies.dto.AuthRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserResponseDto;
import ru.lihogub.softwaredesigntechnologies.entity.UserDetailsImpl;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UsernameAlreadyExistsException;
import ru.lihogub.softwaredesigntechnologies.security.JwtTokenProvider;
import ru.lihogub.softwaredesigntechnologies.service.AuthService;
import ru.lihogub.softwaredesigntechnologies.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public String authenticate(AuthRequestDto authRequestDto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword())
        );
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
        return jwtTokenProvider.createToken(
                userDetailsImpl.getId(),
                userDetailsImpl.getUsername(),
                userDetailsImpl
                        .getAuthorities()
                        .stream()
                        .findFirst()
                        .map(GrantedAuthority::getAuthority)
                        .orElse("ROLE_USER")
        );
    }

    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) throws UsernameAlreadyExistsException, RoleNotFoundException {
        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userService.createUser(userRequestDto);
    }
}
