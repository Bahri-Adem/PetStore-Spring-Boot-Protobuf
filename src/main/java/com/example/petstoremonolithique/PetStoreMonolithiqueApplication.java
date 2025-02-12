package com.example.petstoremonolithique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@SpringBootApplication
public class PetStoreMonolithiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreMonolithiqueApplication.class, args);
	}
	@Bean
	ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}
}
