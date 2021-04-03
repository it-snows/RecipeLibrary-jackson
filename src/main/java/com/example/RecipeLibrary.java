package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeLibrary {

    public static final String FILE_PATH = "src/main/java/com/example/recipes.json";
    public static final String FILE_PATH2 = "src/main/java/com/example/testingJsonNewRecipeStyle.json";

    // deserialization
    public List<Recipe> load() {
        ObjectMapper mapper = new ObjectMapper();

        try {
          return mapper.readValue(new File(FILE_PATH2), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    //to save a recipe in json file
    public void save(List<Recipe> recipes) {
        var mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("src/main/java/com/example/test.json"), recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
