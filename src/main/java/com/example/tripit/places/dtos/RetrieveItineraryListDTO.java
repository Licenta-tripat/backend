package com.example.tripit.places.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveItineraryListDTO {
    private Integer id;

    private Integer noDays;

    private String locationName;

    private String locationId;

    private String startingDate;
}
