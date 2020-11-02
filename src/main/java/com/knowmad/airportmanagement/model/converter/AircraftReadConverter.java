package com.knowmad.airportmanagement.model.converter;

import com.knowmad.airportmanagement.model.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftReadConverter implements Converter<String, Aircraft> {
    @Override
    public Aircraft convert(String aircraftStr) {
        String[] parts = aircraftStr.split("/");
        return new Aircraft(parts[0], Integer.parseInt(parts[1]));
    }
}
