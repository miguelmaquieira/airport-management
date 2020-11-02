package com.knowmad.airportmanagement.queries;

import com.knowmad.airportmanagement.model.FlightInformation;
import com.knowmad.airportmanagement.model.FlightType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInformationQueries {

    private final MongoTemplate mongoTemplate;

    public List<FlightInformation> findAll(String fieldSort1,
                                           String fieldSort2,
                                           int pageNb,
                                           int pageSize) {
        Query allPagedAndOrdered = new Query()
                .with(Sort.by(Sort.Direction.ASC, fieldSort1).and(Sort.by(Sort.Direction.ASC, fieldSort2)))
                .with(PageRequest.of(pageNb, pageSize));

        return this.mongoTemplate.find(allPagedAndOrdered, FlightInformation.class);
    }

    public FlightInformation findSingleById(String id) {
        return this.mongoTemplate.findById(id, FlightInformation.class);
    }

    public long countInternational() {
        Query international = Query.query(Criteria.where("type").is(FlightType.International));

        return this.mongoTemplate.count(international, FlightInformation.class);
    }

    public List<FlightInformation> findByDeparture(String departure) {
        Query byDeparture = new Query()
                .addCriteria(Criteria.where("departureCity").is(departure));

        return this.mongoTemplate.find(byDeparture, FlightInformation.class);
    }

    public List<FlightInformation> findDelayedAtDeparture(String departure) {
        Query delayedAtDeparture = Query.query(Criteria
                .where("isDelayed").is(true)
                .and("departureCity").is(departure));

        return this.mongoTemplate.find(delayedAtDeparture, FlightInformation.class);
    }

    public List<FlightInformation> findByDurationBetween(int minMinutes,
                                                         int maxMinutes) {
        Query byDurationBetween = Query
                .query(Criteria.where("durationMin")
                        .gte(minMinutes)
                        .lte(maxMinutes))
                .with(Sort.by(Sort.Direction.DESC, "durationMin"));

        return this.mongoTemplate.find(byDurationBetween, FlightInformation.class);
    }

    public List<FlightInformation> findRelatedToCityAndNotDelayed(String city) {
        Query byCity = Query.query(
                new Criteria()
                        .orOperator(
                                Criteria.where("departureCity").is(city),
                                Criteria.where("destinationCity").is(city))
                        .andOperator(
                                Criteria.where("isDelayed").is(false)
                        )
        );

        return this.mongoTemplate.find(byCity, FlightInformation.class);
    }

    public List<FlightInformation> findByAircraft(String aircraft) {
        Query byAircraft = Query.query(Criteria.where("aircraft.model").is(aircraft));

        return this.mongoTemplate.find(byAircraft, FlightInformation.class);
    }

    public List<FlightInformation> findByFreeText(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);

        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore()
                .with(PageRequest.of(0, 3));

        return this.mongoTemplate.find(byFreeText, FlightInformation.class);
    }

    public void saveFlight(FlightInformation flight) {
        this.mongoTemplate.save(flight);
    }

    public void insertAll(List<FlightInformation> flights) {
        mongoTemplate.insertAll(flights);
    }

    public void removeAll() {
        mongoTemplate.remove(new Query(), FlightInformation.class);
    }

    public void removeFlightsWithDurationLessThanOneHour() {
        Query lessThanTwoHours = Query.query(Criteria.where("durationMin").lt(60));
        mongoTemplate.findAllAndRemove(lessThanTwoHours, FlightInformation.class);
    }

    public void markAllFlightsToRomeAsDelayed() {
        Query departingFromRome = Query.query(Criteria.where("destinationCity").is("Rome"));
        Update setDelayed = Update.update("isDelayed", true);

        mongoTemplate.updateMulti(
                departingFromRome,
                setDelayed,
                FlightInformation.class);
    }
}
