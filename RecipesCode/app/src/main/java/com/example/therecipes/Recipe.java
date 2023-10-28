package com.example.therecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //Display the recipe
        TextView recipeNameTV = findViewById(R.id.recipeNameTV);
        recipeNameTV.setText("Tea");

        TextView servesTV = findViewById(R.id.servesTV);
        servesTV.setText("Serves for 1 Person");

        TextView descriptionTV = findViewById(R.id.descriptionTV);
        descriptionTV.setText("Tea is a popular and diverse beverage  made by infusing dried tea leaves or herbs in hot water. " +
                "It has been consumed for centuries and comes in various types and flavors.");

        TextView ingredientTV = findViewById(R.id.ingredientTV);
        ingredientTV.setText("1.Milk" +
                "2.Hot Water" +
                "3.Tea Powder"+
                "4.Granulated Sugar"
        );

        TextView procedureTV = findViewById(R.id.procedureTV);
        procedureTV.setText("Boil water and let it cool slightly for green or white tea, or bring it to a rolling boil for black tea.\n" +
                "\n" +
                "Place the tea leaves or tea bag in a teapot or a cup.\n" +
                "\n" +
                "Pour the hot water over the tea leaves or tea bag.\n" +
                "\n" +
                "Let the tea steep for the recommended time (varies by tea type), typically 2-5 minutes.\n" +
                "\n" +
                "Remove the tea leaves or tea bag.\n" +
                "\n" +
                "Add granulated sugar, milk.\n" +
                "\n" +
                "Stir and enjoy!");

        // Saving the recipe when saveRecipe button is clicked
        Button saveButton = findViewById(R.id.saveRecipeBTN);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Data saved!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}