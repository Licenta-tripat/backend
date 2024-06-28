package com.example.tripit.places.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityItineraryDTO {

    @JsonProperty("itinerary_id")
    private Integer itineraryId;

    @JsonProperty("publish_date")
    private String publishDate;

    @JsonProperty("publisher_id")
    private Long publisherId;

    @JsonProperty("description")
    private String description;
}
