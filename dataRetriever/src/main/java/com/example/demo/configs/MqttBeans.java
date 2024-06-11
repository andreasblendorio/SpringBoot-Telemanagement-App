package com.example.demo.configs;

import com.example.demo.entities.CityEntity;
import com.example.demo.repos.CityWeatherRepo;
import com.example.demo.utils.MessageParser;
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
 * Sets up:
 * the MQTT Client connection configurations
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

        // Plugs the options in the factory obj
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

    // Wires City repo
    @Autowired
    private CityWeatherRepo repo;

    // Msg Handler
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {

            // Handles msg, once it's received
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                // Topic
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString(); // retrieving the topic from the message header
                if (topic.equals("weather-data")) {
                    System.out.println("The topic related to this message is: \n" + topic); // printing out the topic
                }
                // Payload
                String payload = message.getPayload().toString();
                System.out.println("The message payload is: \n" + payload); // printing out any msg that comes in the ch
                try {
                    // Parse + Save
                    CityEntity value = MessageParser.parse(payload);
                    CityEntity save = repo.save(value);

                    // Checks the Saving process
                    log.info("\nEntity infos for the parsed payload: \n" + save.toString());
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


