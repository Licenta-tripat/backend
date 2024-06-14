package com.example.tripit.places.persistance.repositories;

import com.example.tripit.places.persistance.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
}