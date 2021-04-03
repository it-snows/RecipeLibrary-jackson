package com.example;

public class Main {

    public static void main(String[] args) {
        var rl = new RecipeLibrary();

        //to read json file in console
//        var recipes = rl.load();
//        for (Recipe recipe : recipes) {
//            System.out.println(recipe);
//        }

         var recipes = rl.load();

//         rl.loadRecipesIntoDb();


        System.out.println(rl.getRecipeIdFromDB());
        System.out.println(rl.getCourseIdFromDB());

    }
}
