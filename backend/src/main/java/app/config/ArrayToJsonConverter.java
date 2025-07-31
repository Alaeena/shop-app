package app.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class ArrayToJsonConverter implements AttributeConverter<long[], String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(long[] attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert long[] to JSON string.", e);
        }
    }

    @Override
    public long[] convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, long[].class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON string to long[].", e);
        }
    }
}

