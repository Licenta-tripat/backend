package com.example.tripit.places.persistance.repositories;

import com.example.tripit.core.persistance.models.User;
import com.example.tripit.places.persistance.models.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {
    List<Itinerary> findByEndDateAfterAndOwner(String date, User owner);

    List<Itinerary> findByEndDateBeforeAndOwner(String date, User owner);
}