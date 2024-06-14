package com.example.tripit.places.services;

import com.example.tripit.places.dtos.CreateItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;

public interface ItineraryService {

    Integer createItinerary(CreateItineraryDTO itineraryDTO, Long userId);

    RetrieveItineraryDTO getItinerary(Integer id);

    void deleteItinerary(Integer id);
}