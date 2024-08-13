package com.alt3rs.seorganiza.domain;

import com.alt3rs.seorganiza.exceptions.DomainException;
import com.alt3rs.seorganiza.domain.type.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

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

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;


    private Activity(String id, Instant date, String description, Double value, Type type) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.value = value;
        this.type = type;

    }

    public static Activity create(String id, Instant date, String description, Double value,
                                  Type type) {
        validate(description, value, type);
        return new Activity(id, date, description, value, type);

    }

    public static Activity with(String id, Instant date, String description,
                                Double value, Type type) {

        return new Activity(
                id,
                date,
                description,
                value,
                type);
    }

    private  static void validate(String description, Double value, Type type) {
       if (description == null || description.isBlank()) {
            throw new DomainException("Activity's description should not be blank.");
        } else if (description.length() < 3) {
            throw new DomainException("Activity's description should have at least 3 characters.");
        } else if (type != Type.EXPENSE && type != Type.REVENUE) {
            throw new DomainException("Activity's type should be either expense or revenue.");
        } else if (value == null || value < 0.01) {
            throw new DomainException("Activity's value should be greater than zero.");
        }
    }
}
