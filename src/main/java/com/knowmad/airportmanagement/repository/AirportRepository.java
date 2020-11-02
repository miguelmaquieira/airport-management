package com.knowmad.airportmanagement.repository;

import com.knowmad.airportmanagement.model.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
}
