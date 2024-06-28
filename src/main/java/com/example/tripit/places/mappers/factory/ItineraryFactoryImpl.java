package com.example.tripit.places.mappers.factory;

import com.example.tripit.places.dtos.CommunityItineraryDTO;
import com.example.tripit.places.persistance.models.CommunityItinerary;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.CreateItineraryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItineraryFactoryImpl implements ItineraryFactory {
    @Override
    public Itinerary getItineraryFromItineraryDTO(CreateItineraryDTO itineraryDTO, User user) {
        Itinerary itinerary = new Itinerary();
        itinerary.setNoDays(itineraryDTO.getNoDays());
        itinerary.setOwner(user);
        itinerary.setLocationName(itineraryDTO.getLocationName());
        itinerary.setLocationId(itineraryDTO.getLocationId());
        itinerary.setStartDate(itineraryDTO.getStartingDate());
        itinerary.setPhotoReference(itineraryDTO.getPhotoReference());
        itinerary.setEndDate(itineraryDTO.getEndingDate());
        itinerary.setLocationsNumber(itineraryDTO.getPlaces().size());
        return itinerary;
    }

    @Override
    public CommunityItinerary getCommunityItineraryFromCommunityItineraryDTO(CommunityItineraryDTO communityItineraryDTO, User user) {
        CommunityItinerary communityItinerary = new CommunityItinerary();
        communityItinerary.setDescription(communityItineraryDTO.getDescription());
        communityItinerary.setPublisher(user);
        communityItinerary.setItineraryId(communityItineraryDTO.getItineraryId());
        communityItinerary.setPublishDate(communityItineraryDTO.getPublishDate());
        return communityItinerary;
    }
}
