package com.cns.assignment.controller.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {

    @NotBlank(message = "First name can't be empty")
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty(message = "User name mustn't null or empty")
    private String username;
    private String role;
    @Email(message = "Not valid Email")
    @NotBlank(message = "Provide a email")
    private String email;
    @NotEmpty
    @Size(min = 8, message = "Password have to 8 character long")
    private String password;

}
