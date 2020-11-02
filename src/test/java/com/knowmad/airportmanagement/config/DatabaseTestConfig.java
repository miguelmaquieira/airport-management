package com.knowmad.airportmanagement.config;

import com.knowmad.airportmanagement.repository.listener.GenericCascadeListener;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class DatabaseTestConfig {

    @Bean
    GenericCascadeListener genericCascadeListener(MongoTemplate mongoTemplate){
        return new GenericCascadeListener(mongoTemplate);
    }
}
