package com.example.airbnblike.rental.service;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;


import java.io.IOException;

import java.time.Instant;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


public class CustomDeserializer extends JsonDeserializer<Instant> {


	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss").withZone(ZoneOffset.UTC);

	@Override
	public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return Instant.from(fmt.parse(p.getText()));
	}
}

