package com.knowmad.airportmanagement.model.converter;

import com.knowmad.airportmanagement.model.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftWriteConverter implements Converter<Aircraft, String> {
    @Override
    public String convert(Aircraft aircraft) {
        return aircraft.getModel() + "/" + aircraft.getNbSeats();
    }
}
