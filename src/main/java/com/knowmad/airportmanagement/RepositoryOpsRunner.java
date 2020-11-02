package com.knowmad.airportmanagement;

import com.knowmad.airportmanagement.model.FlightInformation;
import com.knowmad.airportmanagement.model.FlightPrinter;
import com.knowmad.airportmanagement.repository.FlightInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Order(2)
public class RepositoryOpsRunner implements CommandLineRunner {

    private final FlightInformationRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<FlightInformation> flights = repository.findAll();
        Random random = new Random();
        int randomIdx = random.nextInt(flights.size());
        FlightInformation flight = flights.get(randomIdx);

        // printById
        printById(flight.getId());

        // delayFlight
        delayFlight(flight.getId(), 30);

        // remove flight
        removeFlight(flight.getId());

        // print by departure and destination
        printByDepartureAndDestination("Madrid", "Barcelona");

        // print by seats
        printByMinNbSeats(200);
    }

    private void printById(String id) {
        System.out.println("Print Flight " + id);

        repository.findById(id).ifPresent( (flight) -> {
            FlightPrinter.print(Collections.singletonList(flight));
        });
    }

    private void delayFlight(String id, int duration) {
        System.out.println("Updated flight id: " + id + " duration: " + duration + "\n");
        repository.findById(id).ifPresent( (flight) -> {
            flight.setDurationMin(flight.getDurationMin() + duration);
            repository.save(flight);
        });
    }

    private void removeFlight(String id) {
        System.out.println("Delete flight " + id + "\n");
        repository.deleteById(id);
    }

    private void printByDepartureAndDestination(String dep, String dst) {
        System.out.println("Flights from " + dep + " to " + dst);

        List<FlightInformation> flights = repository.findByDepartureCityAndDestinationCity(dep, dst);

        FlightPrinter.print(flights);
    }

    private void printByMinNbSeats(int minNbSeats) {
        System.out.println("Flights by min nb seats " + minNbSeats);

        List<FlightInformation> flights = this.repository.findByMinAircraftSeats(200);

        FlightPrinter.print(flights);
    }
}
