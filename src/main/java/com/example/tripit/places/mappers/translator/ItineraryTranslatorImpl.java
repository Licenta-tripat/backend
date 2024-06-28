package com.example.tripit.places.mappers.translator;

import com.example.tripit.core.persistance.models.Role;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.RetrieveCommunityItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryListDTO;
import com.example.tripit.places.dtos.RetrievePlaceDTO;
import com.example.tripit.places.dtos.entities.PublisherDTO;
import com.example.tripit.places.persistance.models.CommunityItinerary;
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
    public RetrieveItineraryDTO translateToRetrieveItineraryDTO(List<RetrievePlaceDTO> places, Itinerary itinerary) {
        RetrieveItineraryDTO retrieveItineraryDTO = new RetrieveItineraryDTO();
        retrieveItineraryDTO.setId(itinerary.getId());
        retrieveItineraryDTO.setNoDays(itinerary.getNoDays());
        retrieveItineraryDTO.setLocationName(itinerary.getLocationName());
        retrieveItineraryDTO.setLocationId(itinerary.getLocationId());
        retrieveItineraryDTO.setPlaces(places);
        retrieveItineraryDTO.setStartingDate(itinerary.getStartDate());
        retrieveItineraryDTO.setPhotoReference(itinerary.getPhotoReference());
        retrieveItineraryDTO.setEndingDate(itinerary.getEndDate());
        retrieveItineraryDTO.setLocationsNumber(itinerary.getLocationsNumber());
        return retrieveItineraryDTO;
    }

    @Override
    public List<RetrieveItineraryListDTO> translateToRetrieveItineraryListDTO(List<Itinerary> itineraries) {
        return itineraries.stream()
                .map(this::translateSingleItinerary)
                .sorted(Comparator.comparing(RetrieveItineraryListDTO::getStartingDate))
                .toList();
    }

    @Override
    public List<RetrieveCommunityItineraryDTO> translateToDTO(List<CommunityItinerary> communityItineraries, List<Itinerary> itineraries) {
        return communityItineraries.stream().map((CommunityItinerary communityItinerary) -> {
            RetrieveCommunityItineraryDTO retrieveCommunityItineraryDTO = new RetrieveCommunityItineraryDTO();
            retrieveCommunityItineraryDTO.setId(communityItinerary.getId());
            retrieveCommunityItineraryDTO.setDescription(communityItinerary.getDescription());
            retrieveCommunityItineraryDTO.setPublishDate(communityItinerary.getPublishDate());

            // Find the corresponding Itinerary from the list
            itineraries.stream()
                    .filter(i -> i.getId().equals(communityItinerary.getItineraryId()))
                    .findFirst().ifPresent(itinerary -> retrieveCommunityItineraryDTO.setItinerary(translateSingleItinerary(itinerary)));

            retrieveCommunityItineraryDTO.setPublisher(userToPublisherDTO(communityItinerary.getPublisher()));
            return retrieveCommunityItineraryDTO;
        }).toList();
    }

    private PublisherDTO userToPublisherDTO(User user) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(user.getId());
        publisherDTO.setName(user.getUsername());

        Role firstRole = (Role) user.getRoles().toArray()[0];
        publisherDTO.setRole(firstRole.getName());

        return publisherDTO;
    }

    private RetrieveItineraryListDTO translateSingleItinerary(Itinerary itinerary) {
        RetrieveItineraryListDTO retrieveItineraryListDTO = new RetrieveItineraryListDTO();
        retrieveItineraryListDTO.setId(itinerary.getId());
        retrieveItineraryListDTO.setNoDays(itinerary.getNoDays());
        retrieveItineraryListDTO.setLocationName(itinerary.getLocationName());
        retrieveItineraryListDTO.setLocationId(itinerary.getLocationId());
        retrieveItineraryListDTO.setStartingDate(itinerary.getStartDate());
        retrieveItineraryListDTO.setPhotoReference(itinerary.getPhotoReference());
        retrieveItineraryListDTO.setEndingDate(itinerary.getEndDate());
        retrieveItineraryListDTO.setLocationsNumber(itinerary.getLocationsNumber());
        return retrieveItineraryListDTO;
    }
}
