package com.alt3rs.seorganiza.dto;

import com.alt3rs.seorganiza.domain.activity.type.TypeExpense;
import com.alt3rs.seorganiza.domain.activity.type.TypeTransaction;

import java.time.Instant;

public record ActivityRequestDTO(Instant date, String description, Double value, TypeTransaction typeTransaction, TypeExpense typeExpense) {
}
