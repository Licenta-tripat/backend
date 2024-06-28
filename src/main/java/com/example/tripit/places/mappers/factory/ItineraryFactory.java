package com.example.tripit.places.mappers.factory;

import com.example.tripit.places.dtos.CommunityItineraryDTO;
import com.example.tripit.places.persistance.models.CommunityItinerary;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.CreateItineraryDTO;

public interface ItineraryFactory {
    Itinerary getItineraryFromItineraryDTO(CreateItineraryDTO itineraryDTO, User user);

    CommunityItinerary getCommunityItineraryFromCommunityItineraryDTO(CommunityItineraryDTO communityItineraryDTO, User user);
}
