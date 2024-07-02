package com.example.tripit.places.controllers;

import com.example.tripit.places.dtos.CommunityItineraryDTO;
import com.example.tripit.places.dtos.RetrieveCommunityItineraryDTO;
import com.example.tripit.places.services.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final ItineraryService itineraryService;

    @PostMapping
    public ResponseEntity<Void> saveCommunityItinerary(@RequestBody CommunityItineraryDTO communityItineraryDTO) {
        itineraryService.saveCommunityItinerary(communityItineraryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RetrieveCommunityItineraryDTO>> getCommunityItineraries() {
        return ResponseEntity.ok(itineraryService.getCommunityItineraries());
    }
}
