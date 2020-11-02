package com.knowmad.airportmanagement.config;

import com.knowmad.airportmanagement.model.converter.ZonedDateTimeReadConverter;
import com.knowmad.airportmanagement.model.converter.ZonedDateTimeWriteConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        /*converters.add(new AircraftReadConverter());
        converters.add(new AircraftWriteConverter());*/
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}
