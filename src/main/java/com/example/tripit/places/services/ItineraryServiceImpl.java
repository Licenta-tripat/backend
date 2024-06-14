package com.example.tripit.places.services;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.persistance.repositories.ItineraryRepository;
import com.example.tripit.places.dtos.CreateItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;
import com.example.tripit.places.mappers.factory.ItineraryFactory;
import com.example.tripit.places.mappers.translator.ItineraryTranslator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService{

    private final PlacesService placeService;

    private final ItineraryRepository itineraryRepository;

    private final ItineraryFactory itineraryFactory;

    private final ItineraryTranslator itineraryTranslator;

    private final WebServiceFacade webServiceFacade;

    @Override
    public Integer createItinerary(CreateItineraryDTO itineraryDTO, Long userId) {
        User user = webServiceFacade.getUserById(userId);
        if(user == null) {
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
        if(itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        List<Place> places = placeService.getPlacesByItinerary(itinerary);
        if (places.isEmpty()) {
            throw new RuntimeException("Places not found!");
        }
        return itineraryTranslator.translateToRetrievePlaceDTO(places, itinerary);
    }

    @Override
    @Transactional
    public void deleteItinerary(Integer id) {
        Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
        if(itinerary == null) {
            throw new RuntimeException("Itinerary not found!");
        }
        placeService.deletePlacesByItinerary(itinerary);
        itineraryRepository.delete(itinerary);
    }
}