package com.eventify.dataseeder.idresolver;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public abstract class IdResolver {
    private static Map<EntityType, Map<Integer, UUID>> ids;

    static {
        ids = new HashMap<>();
        ids.put(EntityType.USER, new HashMap<>());
        ids.put(EntityType.EVENT, new HashMap<>());
        ids.put(EntityType.PLACE, new HashMap<>());
    }

    public static void linkId(EntityType entityType, Object responseEntity, Integer id) {
        ids.get(entityType).put(id, UUID.fromString((String) ((Map) responseEntity).get("id")));
    }

    public static UUID resolveId(int id, EntityType entityType) {
        return ids.get(entityType).get(id);
    }
}
