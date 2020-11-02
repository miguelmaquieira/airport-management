package com.knowmad.airportmanagement;

import com.knowmad.airportmanagement.model.Aircraft;
import com.knowmad.airportmanagement.model.FlightInformation;
import com.knowmad.airportmanagement.model.FlightPrinter;
import com.knowmad.airportmanagement.model.FlightType;
import com.knowmad.airportmanagement.queries.FlightInformationQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(4)
public class ApplicationRunner implements CommandLineRunner {

    private final FlightInformationQueries flightInformationQueries;

    @Override
    public void run(String... args) throws Exception {
        //initFlightInformation();
        //queries();

        System.out.println("-----\nQUERY: Duration between 60 and 120 minutes");
        List<FlightInformation> durationBetweenOneAndTwoHours = this.flightInformationQueries
                .findByDurationBetween(0, 120);
        FlightPrinter.print(durationBetweenOneAndTwoHours);

        System.out.println("Application running...");
    }

    private void queries() {
        System.out.println("-----\nQUERY: All flights ordered by departure");
        List<FlightInformation> allFlightsOrdered = this.flightInformationQueries
                .findAll("departure", "destination", 0, 10);
        FlightPrinter.print(allFlightsOrdered);

        System.out.println("-----\nQUERY: Depart at Barcelona");
        List<FlightInformation> BCNDepartures = this.flightInformationQueries
                .findByDeparture("Barcelona");
        FlightPrinter.print(BCNDepartures);

        System.out.println("-----\nQUERY: Delayed departures at Barcelona");
        List<FlightInformation> BCNDelayedDepartures = this.flightInformationQueries
                .findDelayedAtDeparture("Barcelona");
        FlightPrinter.print(BCNDelayedDepartures);

        System.out.println("-----\nQUERY: Duration between 60 and 120 minutes");
        List<FlightInformation> durationBetweenOneAndTwoHours = this.flightInformationQueries
                .findByDurationBetween(60, 120);
        FlightPrinter.print(durationBetweenOneAndTwoHours);

        System.out.println("-----\nQUERY: Using  A319/150 aircraft");
        List<FlightInformation> usingA319 = this.flightInformationQueries
                .findByAircraft("A319/150");
        FlightPrinter.print(usingA319);

        System.out.println("-----\nQUERY: Free search text: Madrid");
        List<FlightInformation> freeSearchTextMadrid = this.flightInformationQueries
                .findByFreeText("Madrid");
        FlightPrinter.print(freeSearchTextMadrid);
    }

    private void initFlightInformation() {
        FlightInformation BCNMAD = new FlightInformation();
        BCNMAD.setAircraft(new Aircraft("A319/150", 230));
        BCNMAD.setDepartureCity("Barcelona");
        BCNMAD.setDestinationCity("Madrid");
        BCNMAD.setDescription("Flight from Barcelona to Madrid");
        BCNMAD.setDelayed(false);
        BCNMAD.setType(FlightType.Internal);
        BCNMAD.setDurationMin(110);
        BCNMAD.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 12, 11, 0), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(BCNMAD);

        FlightInformation BUCROM = new FlightInformation();
        BUCROM.setAircraft(new Aircraft("A239/143", 200));
        BUCROM.setDepartureCity("Bucharest");
        BUCROM.setDelayed(false);
        BUCROM.setDestinationCity("Rome");
        BUCROM.setDescription("Flight from Bucharest to Rome via Brussels");
        BUCROM.setType(FlightType.International);
        BUCROM.setDurationMin(250);
        BUCROM.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 14, 12, 15), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(BUCROM);

        FlightInformation WASNYC = new FlightInformation();
        WASNYC.setAircraft(new Aircraft("B239/120", 300));
        WASNYC.setDepartureCity("Washington");
        WASNYC.setDestinationCity("New York");
        WASNYC.setDescription("Flight from Washington to New York via Seattle");
        WASNYC.setDelayed(true);
        WASNYC.setType(FlightType.Internal);
        WASNYC.setDurationMin(140);
        WASNYC.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 18, 13, 15), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(WASNYC);

        FlightInformation BCNVGO = new FlightInformation();
        BCNVGO.setAircraft(new Aircraft("A120/120", 50));
        BCNVGO.setDepartureCity("Barcelona");
        BCNVGO.setDestinationCity("Vigo");
        BCNVGO.setDescription("Flight from Barcelona to Vigo via Madrid");
        BCNVGO.setType(FlightType.Internal);
        BCNVGO.setDelayed(true);
        BCNVGO.setDurationMin(100);
        BCNVGO.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 19, 4, 30), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(BCNVGO);

        FlightInformation BCNOPO = new FlightInformation();
        BCNOPO.setAircraft(new Aircraft("A120/120", 50));
        BCNOPO.setDepartureCity("Barcelona");
        BCNOPO.setDestinationCity("Oporto");
        BCNOPO.setDescription("Flight from Barcelona to Porto via Madrid");
        BCNOPO.setType(FlightType.International);
        BCNOPO.setDelayed(false);
        BCNOPO.setDurationMin(130);
        BCNOPO.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 19, 11, 0), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(BCNOPO);

        FlightInformation BCNMAL = new FlightInformation();
        BCNMAL.setAircraft(new Aircraft("A200/140", 200));
        BCNMAL.setDepartureCity("Barcelona");
        BCNMAL.setDestinationCity("Malaga");
        BCNMAL.setType(FlightType.Internal);
        BCNMAL.setDelayed(false);
        BCNMAL.setDurationMin(90);
        BCNMAL.setDepartureDate(ZonedDateTime.of(LocalDateTime.of(2019, 6, 19, 15, 30), ZoneOffset.UTC));

        flightInformationQueries.saveFlight(BCNMAL);
    }
}
