package com.example.tripit.places.dtos.entities.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EntryPoint {

    @JsonProperty("type")
    private String type;
    @JsonProperty("position")
    private GeoBias position;
}
