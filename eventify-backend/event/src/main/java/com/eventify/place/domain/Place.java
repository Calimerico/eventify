package com.eventify.place.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@NoArgsConstructor(access = PRIVATE)
@Getter
public class Place extends UUIDEntity {
    private String name;
    private String city;
    private double latitude;
    private double longitude;

    public Place(UUID id, String name, String city, double latitude, double longitude) {
        update(id,name,city,latitude,longitude);
    }

    public void update(UUID id, String name, String city, double latitude, double longitude) {
        setId(id);
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
