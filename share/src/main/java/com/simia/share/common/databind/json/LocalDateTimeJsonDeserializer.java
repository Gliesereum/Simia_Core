package com.simia.share.common.databind.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public class LocalDateTimeJsonDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        long millis = p.getValueAsLong();
        return Instant.ofEpochMilli(millis).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}
