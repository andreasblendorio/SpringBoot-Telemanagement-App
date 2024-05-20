package com.example.demo.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Sets up:
 * the Gateway for the Outbound Channel, to enable msg publishing (just in case)
 */

// Msg Gateway
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    void sentToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
