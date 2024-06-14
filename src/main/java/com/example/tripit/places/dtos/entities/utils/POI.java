package com.example.tripit.places.dtos.entities.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class POI {

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("url")
    private String url;

    @JsonProperty("categorySet")
    List<CategoryId> categorySet;

    @JsonProperty("categories")
    List<String> categories;
}
