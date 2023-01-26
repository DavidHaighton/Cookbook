package com.based.cooking;

import org.apache.commons.collections.IteratorUtils;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Log
@DataJpaTest
class RecipeTest {
    @Autowired
    private RecipeRepository repo;
    private Recipe r1, r2, r3;

    @BeforeEach
    void setUp() {
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 1);
            Date date = calendar.getTime();
            Recipe.RecipeBuilder builder = Recipe.builder();
            builder.name("Banana Bread");
            builder.author("David");
            builder.creationDate(date);
            builder.tags(Set.of("Difficult","Unhealthy"));
            builder.ingredients("Bananas\nBread");
            builder.instructions("Mix Bananas and Bread\nPut in Oven");
            builder.notes("Yummy");
            r1 = builder.build();
        }
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.JANUARY, 2);
            Date date = calendar.getTime();
            Recipe.RecipeBuilder builder = Recipe.builder();
            builder.name("Chicken and Rice");
            builder.author("David");
            builder.creationDate(date);
            builder.tags(Set.of("Easy","Body Builder", "Simple"));
            builder.ingredients("Chicken\nRice");
            builder.instructions("Cook Chicken\nCook Rice\nCombine");
            builder.notes("Strong");
            r2 = builder.build();
        }
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2022, Calendar.MARCH, 1);
            Date date = calendar.getTime();
            Recipe.RecipeBuilder builder = Recipe.builder();
            builder.name("Salmon");
            builder.author("David");
            builder.creationDate(date);
            builder.tags(Set.of("Simple","Seafood"));
            builder.ingredients("Salmon");
            builder.instructions("Cook Salmon");
            builder.notes("Fish");
            r3 = builder.build();
        }
        repo.save(r1);
        repo.save(r2);
        repo.save(r3);
    }


    @Test
    public void testGetTags(){
        List<Recipe> result = repo.getRecipesByTagsContaining("Simple");
        assertEquals(2, result.size());
        assertTrue(result.containsAll(List.of(r2,r3)));
    }

    @Test
    public void testGetName(){
        List<Recipe> result = repo.getRecipesByNameContaining("Salm");
        assertEquals(1, result.size());
        assertEquals(r3, result.get(0));
    }
    @Test
    public void testGetAuthor(){
        List<Recipe> result = repo.getRecipesByAuthorContains("David");
        assertEquals(3, result.size());
        assertTrue(result.containsAll(List.of(r1,r2,r3)));
    }
    @Test
    public void testRemoveId(){
        long id = repo.findAll().iterator().next().getId();
        repo.removeRecipeById(id);
        Iterable<Recipe> result = repo.findAll();
        var list = IteratorUtils.toList(result.iterator());
        assertEquals(2, list.size());

    }
}