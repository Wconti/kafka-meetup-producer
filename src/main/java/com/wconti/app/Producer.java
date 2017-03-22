package com.wconti.app;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Properties;

/**
 * Created by ippon on 22/03/17.
 */
public class Producer {
    public static void main(String[] args) throws MalformedURLException,IOException{
        URL url = new URL("http://stream.meetup.com/2/rsvps");
        URLConnection connection = url.openConnection();

        JsonFactory jsonFactory = new JsonFactory(new ObjectMapper());
        JsonParser parser =  jsonFactory.createParser(connection.getInputStream());

 		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(props);

        while(parser.nextToken()!=null)
        {
            String record = parser.readValueAsTree().toString();
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String, String>("test",record);
            kafkaProducer.send(producerRecord);
        }

        kafkaProducer.close();
    }

}
