package com.example.therecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAbout(View v){
        try {
            Intent toOtherIntent = new Intent(this,AboutActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }
    public void goToContactUs(View v){
        try {
            Intent toOtherIntent = new Intent(this,ContactusActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }

    public void gotoSearchRecipe(View v) {
        try {
            Intent toOtherIntent = new Intent(this,RecipesListView.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }

    public void gotoAddNewRecipe(View view) {
        Intent toOtherIntent = new Intent(this, AddNewRecipe.class);
        startActivity(toOtherIntent);
    }

    public void gotosignoutAction(View v) {
        try {
            Intent toOtherIntent = new Intent(this, SignInActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }
}