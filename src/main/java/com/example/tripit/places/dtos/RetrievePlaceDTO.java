package com.example.tripit.places.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrievePlaceDTO {

    private Integer id;

    private String placeId;

    private String name;

    private String address;

    private double lat;

    private double lon;

    private boolean visited;

    private String website;

    private Integer duration;

    private Integer day;

    private Integer position;
}
