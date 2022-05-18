package ru.lihogub.softwaredesigntechnologies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.lihogub.softwaredesigntechnologies.dto.AccessTokenDto;
import ru.lihogub.softwaredesigntechnologies.dto.AuthRequestDto;
import ru.lihogub.softwaredesigntechnologies.dto.UserRequestDto;
import ru.lihogub.softwaredesigntechnologies.exception.RoleNotFoundException;
import ru.lihogub.softwaredesigntechnologies.exception.UsernameAlreadyExistsException;
import ru.lihogub.softwaredesigntechnologies.service.AuthService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<AccessTokenDto> login(@RequestBody AuthRequestDto authRequestDto)
            throws AuthenticationException {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setAccessToken(authService.authenticate(authRequestDto));
        return ResponseEntity.ok(accessTokenDto);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody UserRequestDto userRequestDto)
            throws UsernameAlreadyExistsException, RoleNotFoundException {
        return ResponseEntity.ok(authService.register(userRequestDto));
    }
}
