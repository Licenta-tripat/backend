package com.example.tripit.places.dtos;

import com.example.tripit.places.dtos.entities.PublisherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveCommunityItineraryDTO {

    private Integer id;

    private RetrieveItineraryListDTO itinerary;

    private String publishDate;

    private PublisherDTO publisher;

    private String description;
}
