package com.example.therecipes;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initialize UI components
        usernameEditText = findViewById(R.id.usernameET);
        passwordEditText = findViewById(R.id.passwordET);
        signInButton = findViewById(R.id.signInBtn);
        registerButton = findViewById(R.id.registerBtn);

        // Set a click listener for the signInButton
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View v) {        
                 // Create an Intent to navigate to the MainActivity        
                 Intent intent = new Intent(SignInActivity.this, MainActivity.class);     
                     startActivity(intent);    
                      } }); 
                     registerButton.setOnClickListener(new View.OnClickListener() {    
                         @Override    public void onClick(View v) {         
                          Create an Intent to navigate to the RegisterActivity        
                         Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);       
                           startActivity(intent);     } });
    }
}
