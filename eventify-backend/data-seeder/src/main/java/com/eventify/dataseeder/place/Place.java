package com.eventify.dataseeder.place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(value = "id", allowSetters = true)
public class Place {
    private int id;
    private double latitude;
    private double longitude;
    private String name;
    private String city;
}
