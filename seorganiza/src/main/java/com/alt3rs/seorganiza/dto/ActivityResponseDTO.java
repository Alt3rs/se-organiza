package com.alt3rs.seorganiza.dto;

import com.alt3rs.seorganiza.domain.Activity;
import com.alt3rs.seorganiza.domain.type.Type;

import java.time.Instant;

public record ActivityResponseDTO(String id, Instant data, String description, Double value, Type type) {

    public ActivityResponseDTO(Activity activity){
        this(activity.getId(), activity.getDate(), activity.getDescription(), activity.getValue(), activity.getType());
    }
}
