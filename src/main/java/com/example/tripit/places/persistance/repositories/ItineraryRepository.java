package com.example.tripit.places.persistance.repositories;

import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.persistance.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    List<Itinerary> findByStartDateAfterAndOwner(String startDate, User owner);
}