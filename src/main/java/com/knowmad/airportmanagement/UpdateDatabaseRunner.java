package com.knowmad.airportmanagement;

import com.knowmad.airportmanagement.queries.FlightInformationQueries;
import com.knowmad.airportmanagement.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class UpdateDatabaseRunner implements CommandLineRunner {

    private final FlightInformationQueries flightInformationQueries;
    private final AirportRepository airportRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Updating database...");
        airportRepository.findById("1d1aab22-670b-48cb-a027-721e2055731f")
                .ifPresent( (airport) -> {
                    airport.setName("Leonardo Da Vinci (Fiumicino)");
                    airportRepository.save(airport);
                });
        flightInformationQueries.markAllFlightsToRomeAsDelayed();
        flightInformationQueries.removeFlightsWithDurationLessThanOneHour();
    }
}
