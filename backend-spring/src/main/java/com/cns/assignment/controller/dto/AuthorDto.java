package com.cns.assignment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String firstName;
    private String title;


}
