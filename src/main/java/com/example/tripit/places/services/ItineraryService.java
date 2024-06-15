package com.example.tripit.places.services;

import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.CreateItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryListDTO;
import com.example.tripit.places.persistance.models.Itinerary;

import java.util.List;

public interface ItineraryService {

    Integer createItinerary(CreateItineraryDTO itineraryDTO, Long userId);

    RetrieveItineraryDTO getItinerary(Integer id);

    void deleteItinerary(Integer id);

    List<RetrieveItineraryListDTO> getItinerariesAfterCurrentDate(String date, Long userId);
}