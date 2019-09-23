package com.eventify.place.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Place extends UUIDEntity {
    private String name;
    private String city;
    private double latitude;
    private double longitude;
}
