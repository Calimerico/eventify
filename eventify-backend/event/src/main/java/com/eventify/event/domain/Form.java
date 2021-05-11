package com.eventify.event.domain;

import com.eventify.shared.ddd.UUIDEntity;

import javax.persistence.Entity;

@Entity
class Form extends UUIDEntity {
    private String description;
}
