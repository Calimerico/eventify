package com.eventify.events.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 03-Feb-19.
 */
@Entity
@Data
@Builder
public class Host {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
    private List<String> names;
}
