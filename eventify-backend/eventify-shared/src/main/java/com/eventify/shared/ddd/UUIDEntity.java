package com.eventify.shared.ddd;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class UUIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UUIDEntity that = (UUIDEntity) o;
        return id.equals(that.id);
    }

    protected void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
