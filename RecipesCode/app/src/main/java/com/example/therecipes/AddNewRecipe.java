package com.example.therecipes;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class AddNewRecipe extends AppCompatActivity {

    private EditText recipeNameET;
    private EditText ingredientsET;
    private EditText procedureET;
    private Button saveRecipeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);

        recipeNameET = findViewById(R.id.recipeNameET);
        ingredientsET = findViewById(R.id.ingredientsET);
        procedureET = findViewById(R.id.procedureET);
        saveRecipeBtn = findViewById(R.id.saveRecipeBtn);

        Parse.initialize(this);

        saveRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the new recipe data
                saveNewRecipe();
            }
        });
    }

    private void saveNewRecipe() {
        String recipeName = recipeNameET.getText().toString().trim();
        String ingredients = ingredientsET.getText().toString().trim();
        String procedure = procedureET.getText().toString().trim();

        if (!recipeName.isEmpty() && !ingredients.isEmpty() && !procedure.isEmpty()) {
            ParseObject recipe = new ParseObject("Recipe");
            recipe.put("RecipeName", recipeName);
            recipe.put("Ingredients", ingredients);
            recipe.put("Procedures", procedure);

            recipe.saveInBackground(new SaveCallback() {
                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        // Successfully saved the new recipe
                        goToRecipeDetailsActivity();
                    } else {
                        // Handle error
                    }
                }
            });
        } else {
            // Show a message or alert that all fields must be filled
        }
    }

    private void goToRecipeDetailsActivity() {
        Intent intent = new Intent(this, RecipeCompleteDetailsActivity.class);
        startActivity(intent);
        finish(); // Optional: Close this activity to avoid stacking on the back stack
    }
}