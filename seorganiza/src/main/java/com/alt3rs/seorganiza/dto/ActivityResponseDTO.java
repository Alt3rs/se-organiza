package com.alt3rs.seorganiza.dto;

import com.alt3rs.seorganiza.domain.activity.Activity;
import com.alt3rs.seorganiza.domain.activity.type.TypeExpense;
import com.alt3rs.seorganiza.domain.activity.type.TypeTransaction;

import java.time.Instant;

public record ActivityResponseDTO(String id, Instant data, String description, Double value, TypeTransaction typeTransaction, TypeExpense typeExpense) {

    public ActivityResponseDTO(Activity activity){
        this(activity.getId(), activity.getDate(), activity.getDescription(), activity.getValue(), activity.getTypeTransaction(), activity.getTypeExpense());
    }
}
