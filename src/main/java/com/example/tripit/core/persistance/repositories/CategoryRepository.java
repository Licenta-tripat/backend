package com.example.tripit.core.persistance.repositories;

import com.example.tripit.core.persistance.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE c.id IN :ids")
    List<Category> findAllCategoriesById(List<Integer> ids);

}
