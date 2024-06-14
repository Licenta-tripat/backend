package com.example.tripit.core.services;

import com.example.tripit.exceptions.CategoryTomTomException;
import com.example.tripit.places.dtos.CategoryDTO;
import com.example.tripit.core.persistance.models.Category;
import com.example.tripit.core.persistance.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final WebClient webClient;

    @Value("${tomtom.key}")
    private String tomtomKey;

    @Override
    public void updateCategories() {
        String url = String.format("https://api.tomtom.com/search/2/poiCategories.json?key=%s", tomtomKey);
        webClient.method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(), clientResponse -> clientResponse.bodyToMono(String.class).flatMap(error ->
                        Mono.error(new CategoryTomTomException("Error with TomTom Categories API: " + error))
                ))
                .bodyToMono(CategoryDTO.class).subscribe(categoryDTO -> {
                    List<Category> newList = categoryDTO.getCategories().stream().filter(category -> category.getId().toString().length() == 4).toList();
                    categoryRepository.saveAll(newList);
                });
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        categories.sort(Comparator.comparing(Category::getName));
        return categories;
    }

    @Override
    public void checkIfCategoryExists(String category) {
        if(!categoryRepository.existsById(Integer.valueOf(category))){
            throw new CategoryTomTomException("Category does not exist!");
        }
    }
}
