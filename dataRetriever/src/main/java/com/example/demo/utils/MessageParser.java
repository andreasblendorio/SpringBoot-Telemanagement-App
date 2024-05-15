package com.example.demo.utils;

import com.example.demo.entities.CityEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageParser {

    /**
     * NOTE: It is good practice to include in the method name the type of obj that the method will return/handle.
     * In this case, the 'parse' method accepts a message payload and returns a CityEntity.
     * Thereby, the 'CityEntity' obj will then be manipulated by the message handler of the 'MqttBeans' class
     */

    // Parsing fn
    public static CityEntity parse(String msg_payload) throws Exception {

        // Init Obj Mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Avoiding failure in case of unrecognized fields during json mapping, a workaround may be the 'Mixin' feature from the Jackson pckg
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Retrieving the payload + Converting into the CityEntity class
        return mapper.readValue(msg_payload, CityEntity.class);
    }
}
