package com.example.tripit.places.dtos;

import com.example.tripit.places.persistance.models.Place;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveItineraryDTO {

    private Integer id;

    private Integer noDays;

    private String locationName;

    private String locationId;

    private String startingDate;

    private String endingDate;

    private List<Place> places;

    private String photoReference;
}
