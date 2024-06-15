package com.example.tripit.places.mappers.translator;


import com.example.tripit.places.dtos.RetrieveItineraryListDTO;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;

import java.util.List;

public interface ItineraryTranslator {
    RetrieveItineraryDTO translateToRetrievePlaceDTO(List<Place> places, Itinerary itinerary);

    List<RetrieveItineraryListDTO> translateToRetrieveItineraryListDTO(List<Itinerary> itineraries);
}

