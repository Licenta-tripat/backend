package com.example.tripit.places.dtos.entities;

import com.example.tripit.places.dtos.entities.utils.GeoBias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Summary {

    @JsonProperty("queryType")
    private String queryType;

    @JsonProperty("numResults")
    private int numResults;

    @JsonProperty("fuzzyLevel")
    private int fuzzyLevel;

    @JsonProperty("geoBias")
    private GeoBias geoBias;
}
