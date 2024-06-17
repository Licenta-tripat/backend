package com.example.tripit.places.mappers.factory;

import com.example.tripit.places.persistance.models.Itinerary;
import com.example.tripit.places.persistance.models.Place;
import com.example.tripit.places.dtos.entities.PlaceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceFactoryImpl implements PlaceFactory{

    @Override
    public List<Place> getPlacesFromPlacesDTO(List<PlaceDTO> placesDto, Itinerary itinerary) {
        List<Place> places = placesDto.stream().map(placeDTO -> {
            Place place = new Place();
            place.setName(placeDTO.getPoi().getName());
            place.setAddress(placeDTO.getAddress().getFreeformAddress());
            place.setLat(placeDTO.getPosition().getLat());
            place.setLon(placeDTO.getPosition().getLon());
            place.setPhone(placeDTO.getPoi().getPhone());
            place.setType(placeDTO.getType());
            place.setItinerary(itinerary);
            place.setPlaceId(placeDTO.getId());
            place.setWebsite(placeDTO.getPoi().getUrl());
            place.setDuration(generateDuration() + 15); // Add 15 minutes for the distance between places
            return place;
        }).sorted((p1, p2) -> p2.getDuration().compareTo(p1.getDuration())).collect(Collectors.toList());

        List<List<Place>> days = new ArrayList<>();
        int[] dayDurations = new int[itinerary.getNoDays()];
        for (int i = 0; i < itinerary.getNoDays(); i++) {
            days.add(new ArrayList<>());
        }

        for (Place place : places) {
            int minDayIndex = 0;
            for (int i = 1; i < dayDurations.length; i++) {
                if (dayDurations[i] < dayDurations[minDayIndex]) {
                    minDayIndex = i;
                }
            }

            place.setDay(minDayIndex + 1);
            place.setPosition(days.get(minDayIndex).size());
            days.get(minDayIndex).add(place);
            dayDurations[minDayIndex] += place.getDuration();
        }

        return places;
    }

    private int generateDuration() {
        Random random = new Random();
        int num = 4 + random.nextInt(7);
        return num * 10;
    }



}
