package com.eventify.events.domain;

import com.eventify.shared.ddd.UUIDEntity;
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
class Host extends UUIDEntity {

    @ElementCollection
    private List<String> names;
}
