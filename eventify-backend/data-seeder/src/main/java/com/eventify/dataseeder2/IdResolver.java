package com.eventify.dataseeder2;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
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
        UUID uuid = null;
        try {
            uuid = (UUID) responseEntity.getClass().getField("id").get(responseEntity);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();//TODO
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ids.get(entityType).put(id, uuid);
    }

    public static UUID resolveId(int id, EntityType entityType) {
        return ids.get(entityType).get(id);
    }
}
