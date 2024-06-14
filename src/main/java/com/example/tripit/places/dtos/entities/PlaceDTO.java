package com.example.tripit.places.dtos.entities;

import com.example.tripit.places.dtos.entities.utils.Address;
import com.example.tripit.places.dtos.entities.utils.EntryPoint;
import com.example.tripit.places.dtos.entities.utils.GeoBias;
import com.example.tripit.places.dtos.entities.utils.POI;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDTO {

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;

    @JsonProperty("score")
    private double score;

    @JsonProperty("dist")
    private double dist;

    @JsonProperty("poi")
    private POI poi;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("position")
    private GeoBias position;

    @JsonProperty("entryPoints")
    private List<EntryPoint> entryPoints;


}
