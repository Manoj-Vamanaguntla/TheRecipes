package com.example.therecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        TextView aboutTV = findViewById(R.id.aboutTV);

        // Display app information in the about page

        String aboutInfo = "The Recipe App\n\nVersion 1.0\n\n" +
                "\nDeveloped by Recipe Team\n" +
                "\n© 2023 Northwest Missouri State University\n"+
                "\nAt Recipe app, we are passionate about bringing the joy of cooking to your fingertips.\nOur team of enthusiasts curates and creates recipes to suit every taste and occasion.\n"+
                "\nIn this app you can add a new recipe ,update it and also you can delete your recipe.";
        aboutTV.setText(aboutInfo);

        Button HomeBTN = findViewById(R.id.HomeBTN);

        HomeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the About page
                startActivity(new Intent(AboutActivity.this, HomeActivity.class));
            }
        });
    }
}