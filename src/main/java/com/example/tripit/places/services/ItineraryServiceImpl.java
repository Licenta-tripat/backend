package com.example.tripit.places.services;

import com.example.tripit.places.dtos.*;
import com.example.tripit.places.mappers.translator.PlaceTranslator;
import com.example.tripit.places.persistance.models.CommunityItinerary;
import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.persistance.repositories.CommunityItineraryRepository;
import com.example.tripit.places.persistance.repositories.ItineraryRepository;
import com.example.tripit.places.mappers.factory.ItineraryFactory;
import com.example.tripit.places.mappers.translator.ItineraryTranslator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    private final PlacesService placeService;

    private final ItineraryRepository itineraryRepository;

    private final ItineraryFactory itineraryFactory;

    private final ItineraryTranslator itineraryTranslator;

    private final WebServiceFacade webServiceFacade;

    private final PlaceTranslator placeTranslator;

    private final CommunityItineraryRepository communityItineraryRepository;

    @Override
    public Integer createItinerary(CreateItineraryDTO itineraryDTO, Long userId) {
        User user = webServiceFacade.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        try {
            Itinerary itinerary = itineraryFactory.getItineraryFromItineraryDTO(itineraryDTO, user);
            Itinerary itineraryResult = itineraryRepository.save(itinerary);
            placeService.savePlacesFromItinerary(itineraryDTO.getPlaces(), itineraryResult);
            return itineraryResult.getId();
        } catch (Exception e) {
            throw new RuntimeException("Error saving itinerary!: " + e.getMessage());
        }
    }

    @Override
    public RetrieveItineraryDTO getItinerary(Integer id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if (itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        List<Place> places = placeService.getPlacesByItinerary(itinerary);
        if (places.isEmpty()) {
            throw new RuntimeException("Places not found!");
        }
        List<RetrievePlaceDTO> placesDto = placeTranslator.translateToRetrievePlaceDTO(places);
        return itineraryTranslator.translateToRetrieveItineraryDTO(placesDto, itinerary);
    }

    @Override
    @Transactional
    public void deleteItinerary(Integer id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if (itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        placeService.deletePlacesByItinerary(itinerary);
        itineraryRepository.delete(itinerary);
    }

    @Override
    public List<RetrieveItineraryListDTO> getItinerariesAfterCurrentDate(String currDate, Long userId) {
        User user = webServiceFacade.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        return itineraryTranslator.translateToRetrieveItineraryListDTO(itineraryRepository.findByEndDateAfterAndOwner(currDate, user));
    }

    @Override
    public void saveCommunityItinerary(CommunityItineraryDTO communityItineraryDTO) {
        User user = webServiceFacade.getUserById(communityItineraryDTO.getPublisherId());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        CommunityItinerary communityItinerary = itineraryFactory.getCommunityItineraryFromCommunityItineraryDTO(communityItineraryDTO, user);

        communityItineraryRepository.save(communityItinerary);
    }

    @Override
    public List<RetrieveCommunityItineraryDTO> getCommunityItineraries() {
        List<CommunityItinerary> communityItineraries = communityItineraryRepository.findAll().stream().sorted(Comparator.comparing(CommunityItinerary::getPublishDate).reversed())
                .collect(Collectors.toList());
        if (communityItineraries.isEmpty()) {
            throw new RuntimeException("Community itineraries not found!");
        }

        List<Integer> itineraryIds = communityItineraries.stream()
                .map(CommunityItinerary::getItineraryId)
                .toList();

        List<Itinerary> itineraries = itineraryRepository.findAll().stream()
                .filter(itinerary -> itineraryIds.contains(itinerary.getId()))
                .toList();

        return itineraryTranslator.translateToDTO(communityItineraries, itineraries);
    }
}