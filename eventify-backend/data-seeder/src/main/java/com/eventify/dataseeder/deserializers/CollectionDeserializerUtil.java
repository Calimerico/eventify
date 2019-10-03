package com.eventify.dataseeder.deserializers;

import com.eventify.dataseeder.idresolver.IdResolver;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import static com.eventify.dataseeder.idresolver.EntityType.USER;

public class CollectionDeserializerUtil {
    public static void addValueToCollection(JsonParser jsonParser, Collection<UUID> ids) throws IOException {
        switch (jsonParser.getCurrentName()) {
            case "hostIds":
            case "userIds":
                Iterator<Integer> hostIds = jsonParser.getCodec().readValues(jsonParser,Integer.class);
                while (hostIds.hasNext()) {
                    ids.add(IdResolver.resolveId(hostIds.next(), USER));
                }
                break;
            default:
                throw new IllegalArgumentException("BOOM! xD");
        }
    }
}
