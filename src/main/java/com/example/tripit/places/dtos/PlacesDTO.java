package com.example.tripit.places.dtos;

import com.example.tripit.places.dtos.entities.PlaceDTO;
import com.example.tripit.places.dtos.entities.Summary;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlacesDTO {

    @JsonProperty("summary")
    private Summary summary;

    @JsonProperty("results")
    private List<PlaceDTO> results;
}
