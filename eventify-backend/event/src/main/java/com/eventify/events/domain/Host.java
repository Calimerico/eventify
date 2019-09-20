package com.eventify.events.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by spasoje on 03-Feb-19.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ElementCollection
    private List<String> names;
}
