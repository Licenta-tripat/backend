package com.example.tripit.places.services;

import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.dtos.PlacesDTO;
import com.example.tripit.places.dtos.entities.utils.GeoBias;
import com.example.tripit.core.persistance.models.Category;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WebServiceFacade {

    void updateCategories();

    Mono<PlacesDTO> getAllPlaces(GeoBias geoBias);

    Mono<PlacesDTO> getAllPlacesByCategory(String category, GeoBias geoBias);

    List<Category> getAllCategories();

    Mono<PlacesDTO> getRecommendations(Long id, GeoBias geoBias);

    User getUserById(Long userId);
}
