package com.cns.assignment.controller.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Set;

public record ProjectDto(
        Long id,

        @NotEmpty
        String name,

        @NotEmpty
        Integer status,
        LocalDate startDate,
        LocalDate endDate,
        Set<Long> memberId
) {
}
