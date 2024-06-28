package com.example.tripit.places.controllers;

import com.example.tripit.places.dtos.*;
import com.example.tripit.places.services.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/after/{date}/{id}")
    public ResponseEntity<List<RetrieveItineraryListDTO>> getItinerariesAfterCurrentDate(@PathVariable("date") String date, @PathVariable("id") Long id) {
        return ResponseEntity.ok(itineraryService.getItinerariesAfterCurrentDate(date, id));
    }

    @PostMapping("/community")
    public ResponseEntity<Void> saveCommunityItinerary(@RequestBody CommunityItineraryDTO communityItineraryDTO) {
        itineraryService.saveCommunityItinerary(communityItineraryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/community")
    public ResponseEntity<List<RetrieveCommunityItineraryDTO>> getCommunityItineraries() {
        return ResponseEntity.ok(itineraryService.getCommunityItineraries());
    }
}