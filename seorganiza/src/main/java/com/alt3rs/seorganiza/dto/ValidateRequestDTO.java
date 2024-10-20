package com.alt3rs.seorganiza.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRequestDTO (
        @NotBlank(message = "Token should not be blank") String token
) {}
