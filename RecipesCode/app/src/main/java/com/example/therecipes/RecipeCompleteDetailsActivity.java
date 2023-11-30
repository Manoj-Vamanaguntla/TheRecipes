package com.example.therecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class RecipeCompleteDetailsActivity extends AppCompatActivity {
    public static final String RECIPE_OBJECT = "RecipeName";
    public ParseQuery<ParseObject> query;
    private TextView recipeNameTV;
    private TextView ingredientsTV;
    private TextView procedureTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_complete_details);
        recipeNameTV = findViewById(R.id.recipeNameTV2);
        ingredientsTV = findViewById(R.id.ingredientsTextView2);
        procedureTV= findViewById(R.id.proceduresTextView2);

        Intent intent=getIntent();
        recipeNameTV.setText(intent.getStringExtra("RecipeName"));
        ingredientsTV.setText(intent.getStringExtra("Ingredients"));
        procedureTV.setText(intent.getStringExtra("Procedures"));
        String recipeObjectId = intent.getStringExtra(RecipeCompleteDetailsActivity.RECIPE_OBJECT);
        Log.d("RecipeDetails", "Recipe ObjectId: " + recipeObjectId);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        query= ParseQuery.getQuery("Recipe");
        query.whereEqualTo("RecipeName", recipeObjectId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    // Assuming there is only one matching recipe, as objectId should be unique
                    ParseObject recipe = objects.get(0);

                    // Retrieve data from ParseObject and update TextViews
                    String recipeName = recipe.getString("RecipeName");
                    String ingredients = recipe.getString("Ingredients");
                    String procedures = recipe.getString("Procedures");

                    recipeNameTV.setText(recipeName);
                    ingredientsTV.setText(ingredients);
                    procedureTV.setText(procedures);
                } else {
                    Log.e("RecipeDetails", "Error: " + e);
                }
            }
        });
    }

    public void goToMainActivity(View v) {
        try {
            Intent toOtherIntent = new Intent(this, MainActivity.class);
            startActivity(toOtherIntent);
        } catch (Exception e) {
        }
    }

    public void goToSignOutAction(View v){
        try {
            Intent toOtherIntent = new Intent(this, SignInActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }
}
