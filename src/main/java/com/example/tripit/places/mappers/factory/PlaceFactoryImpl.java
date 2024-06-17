package com.example.tripit.places.mappers.factory;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.entities.PlaceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceFactoryImpl implements PlaceFactory{
    @Override
    public List<Place> getPlacesFromPlacesDTO(List<PlaceDTO> places, Itinerary itinerary) {
        return places.stream().map(placeDTO -> {
            Place place = new Place();
            place.setName(placeDTO.getPoi().getName());
            place.setAddress(placeDTO.getAddress().getFreeformAddress());
            place.setLat(placeDTO.getPosition().getLat());
            place.setLon(placeDTO.getPosition().getLon());
            place.setPhone(placeDTO.getPoi().getPhone());
            place.setType(placeDTO.getType());
            place.setItinerary(itinerary);
            place.setPlaceId(placeDTO.getId());
            place.setWebsite(placeDTO.getPoi().getUrl());
            return place;
        }).toList();
    }
}
