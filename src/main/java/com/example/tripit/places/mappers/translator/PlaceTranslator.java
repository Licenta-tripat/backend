package com.example.tripit.places.mappers.translator;

import com.example.tripit.places.dtos.RetrievePlaceDTO;
import com.example.tripit.places.persistance.models.Place;

import java.util.List;

public interface PlaceTranslator {

    List<RetrievePlaceDTO> translateToRetrievePlaceDTO(List<Place> places);
}
