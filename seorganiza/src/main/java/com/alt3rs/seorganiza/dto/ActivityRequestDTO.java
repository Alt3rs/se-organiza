package com.alt3rs.seorganiza.dto;

import com.alt3rs.seorganiza.domain.type.Type;

import java.time.Instant;

public record ActivityRequestDTO(Instant date, String description, Double value, Type type) {
}
