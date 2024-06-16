package com.example.tripit.places.mappers.translator;

import com.example.tripit.places.dtos.RetrieveItineraryListDTO;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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
        retrieveItineraryDTO.setLocationId(itinerary.getLocationId());
        retrieveItineraryDTO.setPlaces(places);
        retrieveItineraryDTO.setStartingDate(itinerary.getStartDate());
        retrieveItineraryDTO.setPhotoReference(itinerary.getPhotoReference());
        return retrieveItineraryDTO;
    }

    @Override
    public List<RetrieveItineraryListDTO> translateToRetrieveItineraryListDTO(List<Itinerary> itineraries) {
        return itineraries.stream()
                .map(itinerary -> {
                    RetrieveItineraryListDTO retrieveItineraryListDTO = new RetrieveItineraryListDTO();
                    retrieveItineraryListDTO.setId(itinerary.getId());
                    retrieveItineraryListDTO.setNoDays(itinerary.getNoDays());
                    retrieveItineraryListDTO.setLocationName(itinerary.getLocationName());
                    retrieveItineraryListDTO.setLocationId(itinerary.getLocationId());
                    retrieveItineraryListDTO.setStartingDate(itinerary.getStartDate());
                    retrieveItineraryListDTO.setPhotoReference(itinerary.getPhotoReference());
                    return retrieveItineraryListDTO;
                })
                .sorted(Comparator.comparing(RetrieveItineraryListDTO::getStartingDate))
                .toList();
    }
}
