package com.example.tripit.places.mappers.translator;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItineraryTranslatorImpl implements ItineraryTranslator {

    @Override
    public RetrieveItineraryDTO translateToRetrievePlaceDTO(List<Place> places, Itinerary itinerary) {
        RetrieveItineraryDTO retrieveItineraryDTO = new RetrieveItineraryDTO();
        retrieveItineraryDTO.setId(itinerary.getId());
        retrieveItineraryDTO.setNoDays(itinerary.getNoDays());
        retrieveItineraryDTO.setLocationName(itinerary.getLocationName());
        retrieveItineraryDTO.setPlaces(places);
        retrieveItineraryDTO.setStartingDate(itinerary.getStartDate());
        return retrieveItineraryDTO;
    }
}
