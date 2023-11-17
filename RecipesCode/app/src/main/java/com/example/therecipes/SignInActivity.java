package com.example.therecipes;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    public static String username=null;
    public static String object_id = null;
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

        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
    }

    public void gotoSignInAction(View v) {
        final String user=usernameEditText.getText().toString();
        final String p=passwordEditText.getText().toString();
        if(user.length()==0){
            usernameEditText.requestFocus();
            usernameEditText.setError("Name field cannot be empty!!");
        }

        else if(p.length()<9&&!isValidPassword(p)){
            passwordEditText.requestFocus();
            passwordEditText.setError("Enter Valid Password with atleast min 8 Chars with atleast 1 capital letter, 1 small letter, 1 number and a symbol");
        }
        else {
            final ProgressDialog dlg = new ProgressDialog(SignInActivity.this);
            dlg.setTitle("Please, wait a moment.");
            dlg.setMessage("Logging in...");
            dlg.show();

            ParseUser.logInInBackground(
                    usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (parseUser != null) {
                                dlg.dismiss();
                                Toast.makeText(SignInActivity.this, "Sucessful Login", Toast.LENGTH_LONG).show();
                                username=parseUser.getString("Fullname");
                                object_id = parseUser.getObjectId();
                                try {
                                    Intent toOtherIntent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(toOtherIntent);
                                } catch (Exception e1) {
                                }
                                alertDisplayer("Successful Login","Welcome back " + usernameEditText.getText().toString() + "!");

                            } else {
                                dlg.dismiss();
                                ParseUser.logOut();
                                Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void gotoSignUpAction(View v) {
        try {
            Intent toOtherIntent = new Intent(this, RegisterActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    private void alertDisplayer(String title,String message) {
        if (usernameEditText != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
            AlertDialog ok = builder.create();
            ok.show();
        }
    }
}