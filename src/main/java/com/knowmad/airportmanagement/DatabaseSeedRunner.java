package com.knowmad.airportmanagement;

import com.knowmad.airportmanagement.model.*;
import com.knowmad.airportmanagement.repository.AirportRepository;
import com.knowmad.airportmanagement.repository.FlightInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class DatabaseSeedRunner implements CommandLineRunner {

    private final FlightInformationRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Feeding database...");
        empty();
        seed();
        System.out.println("Seeded finished...");
    }

    private void empty() {
        flightRepository.deleteAll();
        airportRepository.deleteAll();
    }

    private void seed() {
        // Airports
        Airport barcelona = new Airport("7ed990d2-6471-485d-931e-c77729dc0f29","Barcelona El Prat","Barcelona", 11423434);
        Airport bucharest = new Airport("7ed990d2-6471-485d-931e-c77729dc0f27","Bucharest Airport","Bucharest",11423434);
        Airport brussels = new Airport("7ed990d2-6471-485d-931e-c77729dc0f26","Brussels Airport South","Brussels",43423434);
        Airport copenhagen = new Airport("7ed990d2-6471-485d-931e-c77729dc0f24", "Copenhagen Airport", "Copenhagen", 30298531);
        Airport lasVegas = new Airport("7ed990d2-6471-485d-931e-c77729dc0f30","Las Vegas Airport","Las Vegas", 101423434);
        Airport madrid = new Airport("7ed990d2-6471-485d-931e-c77729dc0f28","Madrid Barajas","Madrid", 81423434);
        Airport newYork = new Airport("7ed990d2-6471-485d-931e-c77729dc0f25","New York Airport","New York",43433044);
        Airport paris = new Airport("d04a8c26-7527-407c-81ef-680e5de34cea", "Charles de Gaulle","Paris", 72229723);
        Airport rome = new Airport("1d1aab22-670b-48cb-a027-721e2055731f", "Leonardo da Vinci", "Rome", 42995119);
        Airport washington = new Airport("7ed990d2-6471-485d-931e-c77729dc0f31","Washington Airport","Washington", 101423434);

        // Data
        FlightInformation flightOne = new FlightInformation();
        flightOne.setDelayed(false);
        flightOne.setDeparture(rome);
        flightOne.setDepartureCity(rome.getCity());
        flightOne.setDestination(paris);
        flightOne.setDestinationCity(paris.getCity());
        flightOne.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 3, 12, 13, 30), ZoneOffset.UTC));
        flightOne.setType(FlightType.International);
        flightOne.setDurationMin(80);
        flightOne.setAircraft(new Aircraft("737", 180));
        flightOne.setDescription("Flight from Rome to Paris");

        FlightInformation flightTwo = new FlightInformation();
        flightTwo.setDelayed(false);
        flightTwo.setDeparture(newYork);
        flightTwo.setDestinationCity(newYork.getCity());
        flightTwo.setDestination(copenhagen);
        flightTwo.setDestinationCity(copenhagen.getCity());
        flightTwo.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 5, 11, 14, 45), ZoneOffset.UTC));
        flightTwo.setType(FlightType.International);
        flightTwo.setDurationMin(600);
        flightTwo.setAircraft(new Aircraft("747", 300));
        flightTwo.setDescription("Flight from NY to Copenhagen via Rome");

        FlightInformation flightThree = new FlightInformation();
        flightThree.setDelayed(true);
        flightThree.setDeparture(brussels);
        flightThree.setDepartureCity(brussels.getCity());
        flightThree.setDestination(bucharest);
        flightThree.setDestinationCity(bucharest.getCity());
        flightThree.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 12, 11, 25), ZoneOffset.UTC));
        flightThree.setType(FlightType.International);
        flightThree.setDurationMin(150);
        flightThree.setAircraft(new Aircraft("A320", 170));

        FlightInformation flightFour = new FlightInformation();
        flightFour.setDelayed(true);
        flightFour.setDeparture(madrid);
        flightFour.setDepartureCity(madrid.getCity());
        flightFour.setDestination(barcelona);
        flightFour.setDestinationCity(barcelona.getCity());
        flightFour.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 12, 12, 0), ZoneOffset.UTC));
        flightFour.setType(FlightType.Internal);
        flightFour.setDurationMin(120);
        flightFour.setAircraft(new Aircraft("A319", 150));

        FlightInformation flightFive = new FlightInformation();
        flightFive.setDelayed(false);
        flightFive.setDeparture(lasVegas);
        flightFive.setDepartureCity(lasVegas.getCity());
        flightFive.setDestination(washington);
        flightFive.setDestinationCity(washington.getCity());
        flightFive.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 10, 12, 0), ZoneOffset.UTC));
        flightFive.setType(FlightType.Internal);
        flightFive.setDurationMin(400);
        flightFive.setAircraft(new Aircraft("A319", 150));
        flightTwo.setDescription("Flight from LA to Washington via Paris");

        FlightInformation flightSix = new FlightInformation();
        flightSix.setDelayed(false);
        flightSix.setDeparture(bucharest);
        flightSix.setDepartureCity(bucharest.getCity());
        flightSix.setDestination(rome);
        flightSix.setDestinationCity(rome.getCity());
        flightSix.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 13, 23, 0), ZoneOffset.UTC));
        flightSix.setType(FlightType.International);
        flightSix.setDurationMin(110);
        flightSix.setAircraft(new Aircraft("A321 Neo", 200));

        FlightInformation flightSeven = new FlightInformation();
        flightSeven.setDelayed(true);
        flightSeven.setDeparture(brussels);
        flightSeven.setDepartureCity(brussels.getCity());
        flightSeven.setDestination(rome);
        flightSeven.setDestinationCity(rome.getCity());
        flightSeven.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 19, 23, 0), ZoneOffset.UTC));
        flightSeven.setType(FlightType.International);
        flightSeven.setDurationMin(50);
        flightSeven.setAircraft(new Aircraft("A320 Neo", 150));

        // Seed
        /*List<Airport> airports = Arrays.asList(
                barcelona,
                bucharest,
                brussels,
                copenhagen,
                lasVegas,
                newYork,
                madrid,
                paris,
                rome,
                washington
        );
        airportRepository.insert(airports);*/

        List<FlightInformation> flights = Arrays.asList(
                flightOne,
                flightTwo,
                flightThree,
                flightFour,
                flightFive,
                flightSix
        );
        flightRepository.insert(flights);

        // Count
        long count = flightRepository.count();
        System.out.println("Total flight in database: " + count);

        // Print
        List<FlightInformation> flightsInDB = flightRepository.findAll(Sort.by("departureCity").ascending());
        FlightPrinter.print(flightsInDB);
    }
}
