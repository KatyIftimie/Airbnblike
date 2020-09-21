package com.example.airbnblike.rental.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class CustomDeserializer extends JsonDeserializer<Instant> {
	@SneakyThrows
	@Override
	public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;

		System.out.println(jsonParser.getText());
		date = df.parse(jsonParser.getText());
		//System.out.println(df.format(date));
		return date.toInstant();
	}
}
