package com.example.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Function;

@SpringBootApplication
@EnableBinding({Processor.class})
public class RabbitApplicationProcessor {

	@Autowired
	Processor processor;

	public static void main(String[] args) {
		SpringApplication.run(RabbitApplicationProcessor.class, args);
	}

	@StreamListener(Processor.INPUT)
	public void uppercase(String input) {
		processor.output().send(MessageBuilder.withPayload(input.toUpperCase()).build());
	}
}
