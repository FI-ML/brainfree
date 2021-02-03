package de.brf.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomJacksonObjectMapper extends ObjectMapper {

    public CustomJacksonObjectMapper() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        registerModule(javaTimeModule);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
