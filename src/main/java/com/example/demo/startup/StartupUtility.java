package com.example.demo.startup;

import com.example.demo.MqttBeans;
import com.example.demo.entities.City;
import com.example.demo.entities.DummyEntity;
import com.example.demo.repos.CityRepo;
import com.example.demo.repos.DummyRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Implementing CommandLineRunnerInterface to be init when proj is created
@Component
@Log
public class StartupUtility implements CommandLineRunner {


    @Value("${demo.json.string}") private String json;

    //@Autowired private CityRepo repo;

    @Autowired private DummyRepo repo;

    @Override
    public void run(String... args) throws Exception {
        // Init Obj Mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Avoiding failure in case of unrecognized fields during json mapping, a workaround may be the 'Mixin' features of the Jackson pckg
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Getting the json + Converting into the City Class
        DummyEntity value = mapper.readValue(json, DummyEntity.class); // input: json in string format, output: OpenAPI/City Class

        // Saving
        DummyEntity save = repo.save(value);

        // Checking the Saving process
        log.info(" Entity info " + save.toString());


    }
}
