package com.example;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;

public class RecipeLibraryTests {
// works with List<Ingredient> ingredients only
    @Test
    public void save_recipes() {
        var rl = new RecipeLibrary();
        var ing1 = new Ingredient(0, 0, "eggs");
        var ing2 = new Ingredient(0, 0, "milk");
        var rec1 = new Recipe(0, "test pudding", "dessert", 15, 2, Arrays.asList(ing1, ing2), "do everything", "src/img");
        rl.save(Arrays.asList(rec1));
    }

    @Test
    public void load_recipes() {
        var rl = new RecipeLibrary();
        var result = rl.load();
        Assert.assertTrue(result.size() > 0);
    }
}
