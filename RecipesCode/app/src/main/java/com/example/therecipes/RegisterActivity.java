package com.example.therecipes;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText fullNameEditText;
    private EditText emailEditText;
    private Button registerButton;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameET);
        passwordEditText = findViewById(R.id.PasswordET);
        confirmPasswordEditText = findViewById(R.id.ConfirmET);
        fullNameEditText = findViewById(R.id.fullnameTV);
        emailEditText = findViewById(R.id.emailET);
        registerButton = findViewById(R.id.registerBtn1);
        signInButton = findViewById(R.id.signInBtn1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String fullName = fullNameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                // Add user registration logic here
                if (isValidRegistration(username, password, confirmPassword, fullName, email)) {
                    // Registration is successful, you can store the user data
                    // and navigate to the Sign-In activity
                    Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    // Display an error message
                    Toast.makeText(RegisterActivity.this, "Registration failed. Please check your input.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the Sign-In activity
                Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    // Implement your user registration validation logic here
    private boolean isValidRegistration(String username, String password, String confirmPassword, String fullName, String email) {
        // Check if the registration details are valid
        // You should add more robust validation and potentially use a database to store user information
        return !username.isEmpty() && !password.isEmpty() && password.equals(confirmPassword) && !fullName.isEmpty() && !email.isEmpty();
    }
}
