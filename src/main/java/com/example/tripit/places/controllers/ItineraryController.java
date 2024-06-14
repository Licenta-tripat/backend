package com.example.tripit.places.controllers;

import com.example.tripit.places.dtos.CreateItineraryDTO;
import com.example.tripit.places.dtos.RetrieveItineraryDTO;
import com.example.tripit.places.services.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping("/{id}")
    public ResponseEntity<Integer> createItinerary(@PathVariable("id") Long id, @RequestBody CreateItineraryDTO itineraryDTO) {
        return ResponseEntity.ok(itineraryService.createItinerary(itineraryDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RetrieveItineraryDTO> getItinerary(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(itineraryService.getItinerary(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable("id") Integer id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.ok().build();
    }

}