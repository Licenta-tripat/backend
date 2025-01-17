package com.example.tripit.places.mappers.factory;

import com.example.tripit.core.persistance.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PreferenceFactoryImpl implements PreferenceFactory{

    @Override
    public String getPreferencesFromList(List<Category> preferences) {
        return preferences.stream().reduce("", (s, category) -> s + category.getId() + ",", (s, s2) -> s + s2);
    }
}
