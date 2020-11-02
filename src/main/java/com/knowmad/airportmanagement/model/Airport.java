package com.knowmad.airportmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    private String id;
    private String name;
    private String city;
    private int passengersServed;
}
