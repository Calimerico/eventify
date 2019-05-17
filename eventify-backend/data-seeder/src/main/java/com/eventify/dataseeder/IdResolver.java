package com.eventify.dataseeder;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
public class IdResolver {
    private static Map<EntityType, Map<Integer, UUID>> ids;

    static {
        ids = new HashMap<>();
        ids.put(EntityType.USER, new HashMap<>());
        ids.put(EntityType.EVENT, new HashMap<>());
        ids.put(EntityType.PLACE, new HashMap<>());
    }

    public void linkId(EntityType entityType, Object responseEntity, Integer id) {
        ids.get(entityType).put(id, UUID.fromString((String) ((Map) responseEntity).get("id")));
    }

    public static UUID resolveId(int id, EntityType entityType) {
        return ids.get(entityType).get(id);
    }
}
