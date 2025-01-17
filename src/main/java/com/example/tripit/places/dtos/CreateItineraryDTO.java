package com.example.tripit.places.dtos;

import com.example.tripit.places.dtos.entities.PlaceDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateItineraryDTO {

    @JsonProperty("days")
    private Integer noDays;

    @JsonProperty("location")
    private String locationName;

    @JsonProperty("places")
    private List<PlaceDTO> places;

    @JsonProperty("starting_date")
    private String startingDate;
}
