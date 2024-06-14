package com.example.tripit.places.mappers.factory;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.entities.PlaceDTO;

import java.util.List;

public interface PlaceFactory {
    List<Place> getPlacesFromPlacesDTO(List<PlaceDTO> places, Itinerary itinerary);
}
