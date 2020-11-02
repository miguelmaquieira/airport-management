package com.knowmad.airportmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Document(collection = "flights")
@Data
@AllArgsConstructor
public class FlightInformation {

    @Id
    private String id;

    @Indexed(unique = true)
    private String internalId;

    @Indexed
    private String departureCity;

    @DBRef
    private Airport departure;

    @Indexed
    private String destinationCity;

    @DBRef
    private Airport destination;

    private FlightType type;
    private boolean isDelayed;
    private int durationMin;
    @Field("departure_date")
    private ZonedDateTime departureDate;
    private Aircraft aircraft;

    @TextIndexed(weight = 2)
    private String description;

    @Transient
    private LocalDate createdAt = LocalDate.now();

    public FlightInformation() {
        this.internalId = UUID.randomUUID().toString();
    }

}
