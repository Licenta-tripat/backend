package com.example.tripit.places.mappers.translator;

import com.example.tripit.places.dtos.RetrievePlaceDTO;
import com.example.tripit.places.persistance.models.Place;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaceTranslatorImpl implements PlaceTranslator{
    @Override
    public List<RetrievePlaceDTO> translateToRetrievePlaceDTO(List<Place> places) {
        return places.stream().map(place -> {
            RetrievePlaceDTO retrievePlaceDTO = new RetrievePlaceDTO();
            retrievePlaceDTO.setId(place.getId());
            retrievePlaceDTO.setPlaceId(place.getPlaceId());
            retrievePlaceDTO.setAddress(place.getAddress());
            retrievePlaceDTO.setName(place.getName());
            retrievePlaceDTO.setLon(place.getLon());
            retrievePlaceDTO.setLat(place.getLat());
            retrievePlaceDTO.setVisited(place.isVisited());
            retrievePlaceDTO.setDuration(place.getDuration());
            retrievePlaceDTO.setDay(place.getDay());
            retrievePlaceDTO.setPosition(place.getPosition());
            return retrievePlaceDTO;
        }).toList();
    }
}
