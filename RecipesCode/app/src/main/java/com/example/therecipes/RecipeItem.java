package com.example.therecipes;

public class RecipeItem {
    private String name,ingredients,process;

    public RecipeItem(String name, String ingredients, String process) {
        this.name = name;
        this.ingredients = ingredients;
        this.process = process;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcess() {
        return process;
    }
}
