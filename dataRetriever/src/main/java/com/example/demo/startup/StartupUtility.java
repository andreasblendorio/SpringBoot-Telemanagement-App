package com.example.demo.startup;
import com.example.demo.entities.CityEntity;
import com.example.demo.repos.CityWeatherRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

// Implementing CommandLineRunnerInterface to be init when proj is created
@Component
@Log
public class StartupUtility implements CommandLineRunner {

    // Passing the json as a value to test the parsing logic
    @Value("${demo.json.string}") private String json;

    // Wiring the Inbound ch
    @Autowired
    private MessageChannel mqttInputChannel;

    // Wiring the Repo
    @Autowired private CityWeatherRepo repo;

    @Override
    public void run(String... args) throws Exception {

        // Init Obj Mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Avoiding failure in case of unrecognized fields during json mapping, a workaround may be the 'Mixin' features of the Jackson pckg
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Getting the json + Converting into the City Class
        CityEntity value = mapper.readValue(json, CityEntity.class); // replace json with payload // input: json in string format, output: OpenAPI/CityEntity (class)

        // Saving
        CityEntity save = repo.save(value);

        // Checking the Saving process
        log.info(" Entity info " + save.toString());
    }
}





