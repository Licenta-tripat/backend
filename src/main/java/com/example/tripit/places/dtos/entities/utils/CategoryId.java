package com.example.tripit.places.dtos.entities.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryId {

    @JsonProperty("id")
    private Integer id;
}
