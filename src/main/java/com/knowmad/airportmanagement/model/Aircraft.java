package com.knowmad.airportmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Aircraft {

    private String model;
    private int nbSeats;
}
