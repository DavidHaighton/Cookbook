package com.based.cooking;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> getRecipesByTagsContaining(String tag);
    List<Recipe> getRecipesByNameContaining(String name);
    List<Recipe> getRecipesByAuthorContains(String name);

    void removeRecipeById(long id);


}
