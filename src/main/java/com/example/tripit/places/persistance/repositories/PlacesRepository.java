package com.example.tripit.places.persistance.repositories;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacesRepository extends JpaRepository<Place, Integer> {

    @Query("Select p from Place p where p.id = :itineraryId ")
    List<Place> findByItineraryId(Integer itineraryId);

    void deletePlacesByItineraryId(Integer itineraryId);
}
