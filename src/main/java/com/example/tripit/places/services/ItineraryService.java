package com.example.tripit.places.services;

import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.*;
import com.example.tripit.places.persistance.models.Itinerary;

import java.util.List;

public interface ItineraryService {

    Integer createItinerary(CreateItineraryDTO itineraryDTO, Long userId);

    RetrieveItineraryDTO getItinerary(Integer id);

    void deleteItinerary(Integer id);

    List<RetrieveItineraryListDTO> getItinerariesAfterCurrentDate(String currDate, Long userId);

    List<RetrieveItineraryListDTO> getItinerariesBeforeCurrentDate(String currDate, Long userId);

    void saveCommunityItinerary(CommunityItineraryDTO communityItineraryDTO);

    List<RetrieveCommunityItineraryDTO> getCommunityItineraries();
}