package com.knowmad.airportmanagement.repository;

import com.knowmad.airportmanagement.model.FlightInformation;
import com.knowmad.airportmanagement.model.FlightType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

    List<FlightInformation> findByDepartureCityAndDestinationCity(String departure, String destination);

    List<FlightInformation> findByDelayedTrue();

    List<FlightInformation> findByType(FlightType flightType);

    @Query("{'aircraft.nbSeats': {$gte: ?0}}")
    List<FlightInformation> findByMinAircraftSeats(int seats);
}



