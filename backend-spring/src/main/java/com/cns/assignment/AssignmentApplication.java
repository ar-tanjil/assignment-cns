package com.cns.assignment;

import com.cns.assignment.controller.dto.UserRegisterDto;
import com.cns.assignment.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);
    }

    @Autowired
    private AuthenticationService service;

    @Bean
    CommandLineRunner commandLineRunner(){
        return (r) -> {
            UserRegisterDto admin = UserRegisterDto.builder()
                    .firstname("admin")
                    .lastname("tanjil")
                    .username("admin")
                    .role("ROLE_ADMIN")
                    .email("ar@cns.com")
                    .password("123")
                    .build();

            UserRegisterDto user = UserRegisterDto.builder()
                    .firstname("user")
                    .lastname("ashiq")
                    .role("ROLE_USER")
                    .username("user")
                    .email("user@cns.com")
                    .password("123")
                    .build();
    log.info("com-------------------------------");
            service.register(admin);
            service.register(user);
        };
    }

}
