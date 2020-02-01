package com.example.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;

import java.util.function.Function;

@SpringBootApplication
@EnableBinding({Processor.class})
public class RabbitApplicationIntegrationFlow{

	public static void main(String[] args) {
		SpringApplication.run(RabbitApplicationIntegrationFlow.class, args);
	}

	@Bean
	public StandardIntegrationFlow getAndSendMessage(
			Function<String, String> uppercase
	){
		return IntegrationFlows.from(Processor.INPUT)
				.handle(uppercase)
				.channel(Processor.OUTPUT)
				.get();
	}

	@Bean
	public Function<String, String> uppercase() {
		return s -> "\"" + s.toUpperCase() + "\"";
	}

}
