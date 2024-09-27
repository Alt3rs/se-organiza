package com.alt3rs.seorganiza.dto;

import com.alt3rs.seorganiza.domain.activity.Activity;
import com.alt3rs.seorganiza.domain.type.Type;

import java.time.Instant;

public record ActivityResponseDTO(String id, Instant date, String description, Double value, Type type, String userId) {

    public ActivityResponseDTO(Activity activity) {
        this(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getType(),
                activity.getUser() != null ? activity.getUser().getId() : null
        );
    }
}