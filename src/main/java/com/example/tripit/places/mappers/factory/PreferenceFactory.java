package com.example.tripit.places.mappers.factory;

import com.example.tripit.core.persistance.models.Category;

import java.util.List;

public interface PreferenceFactory {

    String getPreferencesFromList(List<Category> preferences);
}
