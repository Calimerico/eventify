package com.eventify.dataseeder.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.*;

import static com.eventify.dataseeder.deserializers.CollectionDeserializerUtil.addValueToCollection;

public class IntegerSetToUUIDSetDeserializer extends JsonDeserializer<Set<UUID>> {

    @Override
    public Set<UUID> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Set<UUID> ids = new HashSet<>();
        addValueToCollection(jsonParser, ids);
        return ids;
    }
}
