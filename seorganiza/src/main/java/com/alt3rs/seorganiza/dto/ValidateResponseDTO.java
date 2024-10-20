package com.alt3rs.seorganiza.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidateResponseDTO(
        @JsonProperty("is_valid") boolean isValid
) {}
