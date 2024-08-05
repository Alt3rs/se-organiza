package com.alt3rs.seorganiza.domain.activity;

import com.alt3rs.seorganiza.exceptions.DomainException;
import com.alt3rs.seorganiza.domain.activity.type.TypeExpense;
import com.alt3rs.seorganiza.domain.activity.type.TypeTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "Activity")
@Table(name = "activities")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "date", nullable = false)
    private Instant date;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "value", nullable = false)
    private Double value;
    @Column(name = "type_transaction", nullable = false)
    private TypeTransaction typeTransaction;
    @Column(name = "type_expense", nullable = false)
    private TypeExpense typeExpense;

    private Activity(String id, Instant date, String description, Double value,
                     TypeTransaction typeTransaction, TypeExpense typeExpense) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.typeTransaction = typeTransaction;
        this.typeExpense = typeExpense;


    }

    public static Activity create(String id, Instant date, String description, Double value,
                                  TypeTransaction typeTransaction, TypeExpense typeExpense) {
        validate(id, date, description, value, typeTransaction, typeExpense);
        return new Activity(id, date, description, value, typeTransaction, typeExpense);

    }

    public static Activity with(String id, Instant date, String description,
                                Double value,  TypeTransaction typeTransaction, TypeExpense typeExpense) {

        return new Activity(
                id,
                date,
                description,
                value,
                typeTransaction,
                typeExpense);
    }

    private  static void validate(String id, Instant date, String description, Double value,
                          TypeTransaction typeTransaction, TypeExpense typeExpense) {
        if (id.isBlank()) {
            throw new DomainException("Activity's id should not be blank.");
        } else if (id.length() != 36) {
            throw new DomainException("Activity's id should be a valid UUID.");
        } else if (description.isBlank()) {
            throw new DomainException("Activity's description should not be blank.");
        } else if (description.length() < 3) {
            throw new DomainException("Activity's description should have at least 3 characters.");
        } else if (typeTransaction != TypeTransaction.EXPENSE && typeTransaction != TypeTransaction.REVENUE) {
            throw new DomainException("Activity's type should be either expense or revenue.");
        } else if (typeExpense != TypeExpense.BILL && typeExpense != TypeExpense.EDUCATION &&
                typeExpense != TypeExpense.ENTERTAINMENT &&
                typeExpense != TypeExpense.FOOD &&
                typeExpense != TypeExpense.OTHERS &&
                typeExpense != TypeExpense.NULL) {
            throw new DomainException("Activity's type should be bill, education, entertainment, food, others or null.");
        } else if (value == null || value < 0.01) {
            throw new DomainException("Activity's value should be greater than zero.");
        }
    }
}
