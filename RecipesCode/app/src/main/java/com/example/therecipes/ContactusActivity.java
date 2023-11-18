package com.example.therecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactusActivity extends AppCompatActivity {

    private EditText nameEditText, phoneNumberEditText, emailEditText;
    private Button submitButton;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        nameEditText = findViewById(R.id.nameET);
        phoneNumberEditText = findViewById(R.id.phonenumberET);
        emailEditText = findViewById(R.id.emailET);
        submitButton = findViewById(R.id.submitBTN);
        outputTextView = findViewById(R.id.resultsTV);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitContact();
            }
        });

        Button HomeBTN = findViewById(R.id.HomeBTN);

        HomeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the About page
                startActivity(new Intent(ContactusActivity.this, HomeActivity.class));
            }
        });
    }

    private void submitContact() {
        String name = nameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
            // Display the entered data in the output section
            String output = "Name: " + name + "\n" +
                    "Phone Number: " + phoneNumber + "\n" +
                    "Email: " + email;

            outputTextView.setText(output);

            // Optionally, clear the input fields
            nameEditText.getText().clear();
            phoneNumberEditText.getText().clear();
            emailEditText.getText().clear();
        } else {
            // Display an error message if any of the input fields is empty
            outputTextView.setText("Please enter all details.");
        }
    }
}