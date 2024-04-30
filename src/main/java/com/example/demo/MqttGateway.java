package com.example.demo;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

// Msg Gateway set up for msg publishing (just in case)
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sentToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
