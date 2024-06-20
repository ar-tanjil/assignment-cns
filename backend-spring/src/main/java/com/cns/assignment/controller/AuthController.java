package com.cns.assignment.controller;

import com.cns.assignment.controller.dto.AuthRequestDto;
import com.cns.assignment.controller.dto.AuthResponseDto;
import com.cns.assignment.controller.dto.UserRegisterDto;
import com.cns.assignment.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(
           @Valid @RequestBody UserRegisterDto request
    ){
    return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(
            @RequestBody AuthRequestDto request
    ){
    return ResponseEntity.ok(service.authenticate(request));
    }

}
