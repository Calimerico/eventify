package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TheaterEvent extends Event {
    private String genre;
}
