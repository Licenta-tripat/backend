package com.example.tripit.places.dtos.entities.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoBias {

    @JsonProperty("lat")
    private double lat;

    @JsonProperty("lon")
    private double lon;
}
