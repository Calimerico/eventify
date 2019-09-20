package com.eventify.place.domain;

import com.eventify.shared.ddd.UUIDAggregate;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Place extends UUIDAggregate {

    private double latitude;
    private double longitude;
    @ElementCollection
    private Set<String> names;
    private String city;
}
