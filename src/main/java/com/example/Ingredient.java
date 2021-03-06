package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Ingredient {
//    private int ingredientId;
//    private int recipeId;
    private String ingredient;
    private double amount;
    private String unit;

}

