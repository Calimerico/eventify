package com.eventify.dataseeder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class IntegerToUUIDDeserializer extends JsonDeserializer<UUID> {
    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getParsingContext().getCurrentName()) {
            case "placeId":
                return IdResolver.resolveId(p.getValueAsInt(), EntityType.PLACE);
            case "eventId":
                return IdResolver.resolveId(p.getValueAsInt(), EntityType.EVENT);
            case "userId":
                return IdResolver.resolveId(p.getValueAsInt(), EntityType.USER);
                default:
                    throw new IllegalStateException("There is no entity that have id " + p.getParsingContext().getCurrentName());
        }
    }
}
