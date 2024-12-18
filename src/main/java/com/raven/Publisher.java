package com.raven;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Publisher {
    private static final String HOST = "localhost";
    private static final String QUEUE = "OWL-POST";
    private static final String MESSAGE = "Happy birthday Hermione!";

    public static void main(String[] args) {
        // CREATE A CONNECTION FACTORY
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);

        // CREATE A CONNECTION FROM FACTORY
        try (Connection connection = connectionFactory.newConnection()) {
            // CREATE A CONNECTION FROM FACTORY
//            connection = connectionFactory.newConnection();

            // GET CHANNEL FROM CONNECTION
            Channel channel = connection.createChannel();

            // ASSIGN A QUEUE TO CHANNEL
            channel.queueDeclare(QUEUE, false, false, false, null);

            // PUBLISH A MESSAGE TO CHANNEL
            channel.basicPublish("", QUEUE, null, MESSAGE.getBytes(StandardCharsets.UTF_8));
            System.out.println(" Message sent : '" + MESSAGE + "'");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ", " + e.getStackTrace());
        }
    }
}
