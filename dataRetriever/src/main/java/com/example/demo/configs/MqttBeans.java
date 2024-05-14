package com.example.demo.configs;

import com.example.demo.entities.CityEntity;
import com.example.demo.repos.CityWeatherRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * Setting up MQTT Client connection configurations
 * the Client Factory
 * the Channels: Inbound + Outbound
 * the Message Handler
 */

@Configuration
@Log
public class MqttBeans {

    // Client Factory (factory configs)
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        // Options Settings
        options.setServerURIs(new String[] {"tcp://localhost:1883"});
        //options.setUserName("postgres");
        //String password = "admin123";
        //options.setPassword(password.toCharArray());
        options.setCleanSession(true);

        // Plugging the options in the factory obj
        factory.setConnectionOptions(options);

        return factory;
    }

    // Inbound Ch (Subscribing)
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn", mqttClientFactory(),"#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputChannel());

        return adapter;
    }

    // Wiring City repo
    @Autowired
    private CityWeatherRepo repo;

    // Parsing fn
    public void Parser(String msg_payload) throws Exception{

        // Init Obj Mapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Avoiding failure in case of unrecognized fields during json mapping, a workaround may be the 'Mixin' features of the Jackson pckg
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Getting the json + Converting into the City Class
        CityEntity value = mapper.readValue(msg_payload, CityEntity.class); // replace json with payload // input: json in string format, output: OpenAPI/CityEntity (class)

        // Saving
        CityEntity save = repo.save(value);

        // Checking the Saving process
        log.info(" Entity info " + save.toString());
    }

    // Msg Handler
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            // Once a message is received, it has to be handled
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                // Topic
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString(); // retrieving the topic from the message header
                if (topic.equals("weather-data")) {
                    System.out.println("Here's the topic: " + topic); // printing out the topic
                }
                // Payload
                String payload = message.getPayload().toString();
                System.out.println("Here's the payload: " + payload); // printing out any msg that comes in the ch
                try {
                    Parser(payload);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    // Outbound Ch (Publishing)
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    // Msg Handler
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttClientFactory());
        messageHandler.setAsync(true); // so that the client will always be up and listening
        messageHandler.setDefaultTopic("weather-data");
        messageHandler.setDefaultRetained(false);

        return messageHandler;
    }
}


