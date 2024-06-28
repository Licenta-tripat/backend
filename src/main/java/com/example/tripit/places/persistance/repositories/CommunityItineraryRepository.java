package com.example.tripit.places.persistance.repositories;

import com.example.tripit.places.persistance.models.CommunityItinerary;
import com.example.tripit.places.persistance.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityItineraryRepository extends JpaRepository<CommunityItinerary, Integer> {

}
