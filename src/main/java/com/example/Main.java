package com.example;

public class Main {

    public static void main (String []args){
        var rl = new RecipeLibrary();
        var recipes = rl.load();

        for (Recipe recipe : recipes)
        {
            System.out.println(recipe);
        }
    }
}
