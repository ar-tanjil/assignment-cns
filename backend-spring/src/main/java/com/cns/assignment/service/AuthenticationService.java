package com.cns.assignment.service;

import com.cns.assignment.controller.dto.AuthRequestDto;
import com.cns.assignment.controller.dto.AuthResponseDto;
import com.cns.assignment.controller.dto.UserRegisterDto;
import com.cns.assignment.enums.Role;
import com.cns.assignment.model.User;
import com.cns.assignment.repository.UserRepository;
import com.cns.assignment.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(UserRegisterDto request) {
            var user = User.builder()
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .username(request.getUsername())
                    .role(Role.valueOf(request.getRole().toUpperCase()))
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            repository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return AuthResponseDto.builder()
                    .token(jwtToken)
                    .build();
    }

    public AuthResponseDto authenticate(AuthRequestDto request) {

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmail(),
                       request.getPassword()
               )
       );
       var user = repository.findByUsernameOrEmail(request.getEmail(), request.getEmail())
               .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDto.builder()
                .token(jwtToken)
                .build();

    }
}
