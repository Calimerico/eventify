package com.eventify.dataseeder.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static com.eventify.dataseeder.deserializers.CollectionDeserializerUtil.addValueToCollection;

public class IntegerListToUUIDListDeserializer extends JsonDeserializer<List<UUID>> {


    @Override
    public List<UUID> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        List<UUID> ids = new LinkedList<>();
        addValueToCollection(jsonParser, ids);
        return ids;
    }
}
