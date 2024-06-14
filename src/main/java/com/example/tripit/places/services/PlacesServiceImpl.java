package com.example.tripit.places.services;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.persistance.repositories.PlacesRepository;
import com.example.tripit.exceptions.NearbyTomTomException;
import com.example.tripit.places.dtos.PlacesDTO;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.places.dtos.entities.PlaceDTO;
import com.example.tripit.places.dtos.entities.utils.GeoBias;
import com.example.tripit.places.mappers.factory.PlaceFactory;
import com.example.tripit.places.mappers.factory.PreferenceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService{

    private final WebClient webClient;

    private final PreferenceFactory preferenceFactory;

    private final PlacesRepository placesRepository;

    private final PlaceFactory placeFactory;

    @Value("${tomtom.key}")
    private String tomtomKey;

    public Mono<PlacesDTO> getAllPlaces(GeoBias geoBias) {
        String url = String.format("https://api.tomtom.com/search/2/nearbySearch/.json?key=%s&lat=%s&lon=%s", tomtomKey, geoBias.getLat(), geoBias.getLon());
        return webClient.method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class).flatMap(error ->
                        Mono.error(new NearbyTomTomException("Error with TomTom Nearby API: " + error))
                ))
                .bodyToMono(PlacesDTO.class);
    }

    @Override
    public Mono<PlacesDTO> getAllByCategory(String category, GeoBias geoBias) {
        String url = String.format("https://api.tomtom.com/search/2/nearbySearch/.json?key=%s&lat=%s&lon=%s&categorySet=%s", tomtomKey, geoBias.getLat(), geoBias.getLon(), category);
        return webClient.method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class).flatMap(error ->
                        Mono.error(new NearbyTomTomException("Error with TomTom Nearby API: " + error))
                ))
                .bodyToMono(PlacesDTO.class);
    }

    @Override
    public Mono<PlacesDTO> getRecommendations(List<Category> preferences, GeoBias geoBias) {
        String categories = preferenceFactory.getPreferencesFromList(preferences);
        return getAllByCategory(categories, geoBias);
    }

    @Override
    public void checkIfCategoryExists(String category) {

    }

    @Override
    public void savePlacesFromItinerary(List<PlaceDTO> placesDTO, Itinerary itinerary) {
        List<Place> places = placeFactory.getPlacesFromPlacesDTO(placesDTO, itinerary);
        placesRepository.saveAll(places);
    }

    @Override
    public List<Place> getPlacesByItinerary(Itinerary itinerary) {
        return placesRepository.findByItineraryId(itinerary.getId());
    }

    @Override
    public void deletePlacesByItinerary(Itinerary itinerary) {
        placesRepository.deletePlacesByItineraryId(itinerary.getId());
    }

}
