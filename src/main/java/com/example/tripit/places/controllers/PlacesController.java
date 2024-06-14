package com.example.tripit.places.controllers;

import com.example.tripit.places.dtos.PlacesDTO;
import com.example.tripit.places.dtos.entities.utils.GeoBias;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.places.services.WebServiceFacadeImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlacesController {

    private final WebServiceFacadeImpl webClientService;

    @GetMapping("/nearby")
    public ResponseEntity<Mono<PlacesDTO>> getAllNearbyPlaces(@RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        return ResponseEntity.ok(webClientService.getAllPlaces(new GeoBias(lat, lon)));
    }

    @GetMapping("/nearby/{category}")
    public ResponseEntity<Mono<PlacesDTO>> getAllNearbyPlacesByCategory(@PathVariable("category") String category, @RequestParam("lat") double lat, @RequestParam("lon") double lon) {

        return ResponseEntity.ok(webClientService.getAllPlacesByCategory(category, new GeoBias(lat, lon)));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {

        return ResponseEntity.ok(webClientService.getAllCategories());
    }

    @PostMapping("/categories")
    public void updateCategories() {
        webClientService.updateCategories();
    }

    @GetMapping("/{id}/nearby")
    public ResponseEntity<Mono<PlacesDTO>> getRecommendations(@PathVariable("id") Long id, @RequestParam("lat") double lat, @RequestParam("lon") double lon) {
        return ResponseEntity.ok(webClientService.getRecommendations(id, new GeoBias(lat, lon)));
    }
}
