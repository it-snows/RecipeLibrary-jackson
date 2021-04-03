package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeLibrary {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/recipedb?serverTimezone=UTC";
    public static final String FILE_PATH = "src/main/java/com/example/recipes.json";
    public static final String FILE_PATH2 = "src/main/java/com/example/testingJsonNewRecipeStyle.json";

    // deserialization
    public List<Recipe> load() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File(FILE_PATH), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void loadRecipesIntoDb() {
        Connection con = null;
        try {
            con = getConnection();

            var recipes = load();
            String sqlStatement = "INSERT INTO recipes (recipe_title, course, total_time, number_of_servings," +
                    "instructions, picture_link) values (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sqlStatement);

            Path path = Paths.get(FILE_PATH);

            var lines = Files.readAllLines(path);

            for (Recipe recipe : recipes) {
                statement.setString(1, recipe.getTitle());
                statement.setString(2, recipe.getCourse());
                statement.setInt(3, recipe.getTotalTime());
                statement.setInt(4, recipe.getNumberOfServings());
//                statement.setString(5, Arrays.toString(recipe.getIngredients()));
                statement.setString(5, recipe.getInstructions());
                statement.setString(6, recipe.getPictureLink());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Integer> getCourseIdFromDB() {
        List<Integer> courseIds = new ArrayList<>();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select course_id from courses");
            while (rs.next()) {
                Integer id = rs.getInt("course_id");
                courseIds.add(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courseIds;
    }


    public List<Integer> getRecipeIdFromDB() {
        List<Integer> recipeIds = new ArrayList<>();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select recipe_id from recipes");
            while (rs.next()) {
                Integer id = rs.getInt("recipe_id");
                recipeIds.add(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recipeIds;
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionUrl, "test", "test123");
    }
}
