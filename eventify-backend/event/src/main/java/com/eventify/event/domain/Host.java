package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by spasoje on 03-Feb-19.
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Host extends UUIDEntity {

    @ElementCollection
    private List<String> names;

    public List<String> getNames() {
        return names;
    }
}
