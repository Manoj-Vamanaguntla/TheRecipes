package com.example.therecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button aboutBTN = findViewById(R.id.aboutBTN);
        Button contactusBTN = findViewById(R.id.contactusBTN);

        // Navigate to the About page
        aboutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, AboutActivity.class));
            }
        });

        // Navigate to the Contact Us page
        contactusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, ContactusActivity.class));
            }
        });
    }
}