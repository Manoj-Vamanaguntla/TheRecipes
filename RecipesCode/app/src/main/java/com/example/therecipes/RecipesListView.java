package com.example.therecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class RecipesListView extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecipeItem> recipeList;

    private String name;
    private String ingredients, process;
    private List<ParseObject> lastResult = new ArrayList<>();
    private Button backButton;
    private int reqcode = 5;
    private ParseQuery<ParseObject> query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        setContentView(R.layout.activity_recipes_list_view);

        query = ParseQuery.getQuery("Recipe");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                lastResult = objects;
                recipeList = new ArrayList<>();
                for (ParseObject po : objects) {
                    name = po.getString("RecipeName");
                    ingredients = po.getString("Ingredients");
                    process = po.getString("Process");
                    recipeList.add(new RecipeItem(name, ingredients, process));
                    mRecyclerView = findViewById(R.id.recyclerView);
                    mRecyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(RecipesListView.this);
                    adapter = new ItemAdapter(recipeList, RecipesListView.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mRecyclerView.setAdapter(adapter);
                }
            }
        });

        // Initialize backButton and set OnClickListener
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start MainActivity when the back button is clicked
                Intent intent = new Intent(RecipesListView.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        String userInput = query.toLowerCase();
        ArrayList<RecipeItem> newList = new ArrayList<>();

        for (RecipeItem item : recipeList) {
            if (item.getName().toLowerCase().contains(userInput)) {
                newList.add(item);
            }
        }
        adapter = new ItemAdapter(newList, RecipesListView.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return true;
    }
}
