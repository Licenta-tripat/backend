package com.example.tripit.places.mappers.translator;


import com.example.tripit.places.dtos.RetrieveCommunityItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryListDTO;
import com.example.tripit.places.dtos.RetrievePlaceDTO;
import com.example.tripit.places.persistance.models.CommunityItinerary;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;

import java.util.List;

public interface ItineraryTranslator {
    RetrieveItineraryDTO translateToRetrieveItineraryDTO(List<RetrievePlaceDTO> places, Itinerary itinerary);

    List<RetrieveItineraryListDTO> translateToRetrieveItineraryListDTO(List<Itinerary> itineraries);

    public List<RetrieveCommunityItineraryDTO> translateToDTO(List<CommunityItinerary> communityItineraries, List<Itinerary> itineraries);
}

