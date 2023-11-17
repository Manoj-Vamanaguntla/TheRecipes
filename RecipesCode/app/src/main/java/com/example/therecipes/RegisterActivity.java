package com.example.therecipes;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText pass;
    private EditText confirmPass;
    private EditText fullName;
    private EditText email;
    private Button registerBtn;
    private Button signInBtn;

    public static String object_id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameET);
        pass = findViewById(R.id.passwordET);
        confirmPass = findViewById(R.id.confirmET);
        fullName = findViewById(R.id.fullnameET);
        email = findViewById(R.id.emailET);
        registerBtn = findViewById(R.id.registerBtn);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("T4jyEZhrzfcSJQQ9ANSxMI6BuNzcjzzX6h6sDbNV")
                .clientKey("ksUIefM5sqiqUPY3r7bDMHIy4Od3phIOtFWuwEg8")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }
    public void gotoSuccessfulSignUpActivityAction(View v) {
        //Fullname Field validation
        final String fName = fullName.getText().toString();
        final String emailStr = email.getText().toString();
        final String user = username.getText().toString();
        final String p = pass.getText().toString();
        final String Cpass = confirmPass.getText().toString();
        if (fName.length() == 0) {
            fullName.requestFocus();
            fullName.setError("Name field cannot be empty!!");
        } else if (!fName.matches("[a-zA-Z ]+")) {
            fullName.requestFocus();
            fullName.setError("ENTER ONLY ALPHABETICAL CHARACTER");
        }

        //Mobile Number validation

        else if (!isValidEmail(emailStr)) {
            email.requestFocus();
            email.setError("Enter a valid email address");
        }

        //UserId validation

        else if (user.length() == 0) {
            username.requestFocus();
            username.setError("UserName field cannot be empty!!");
        }

        //Password validation
        else if (p.length() < 8 && !isValidPassword(p)) {
            pass.requestFocus();
            pass.setError("Enter Valid Password with atleast 1 capital letter, 1 small letter, 1 number and a symbol");
        }

        //Confirm Password Validation
        else if (!(Cpass.equals(p))) {
            confirmPass.requestFocus();
            confirmPass.setError("Password and Confirm Password does not match");
        } else {
            try {
                Intent toOtherIntent = new Intent(this, MainActivity.class);
                startActivity(toOtherIntent);
            } catch (Exception e) {
            }
        }

        Log.d("Parse","Register Start");

        final ParseUser registeruser = new ParseUser();
        try {
            registeruser.put("Fullname", fullName.getText().toString());
            registeruser.put("Email", (email.getText().toString()));
        }
        catch(Exception e){}
        registeruser.setUsername(username.getText().toString());
        registeruser.setPassword(pass.getText().toString());
        registeruser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    object_id = registeruser.getObjectId();
                    alertDisplayer("Sucessful Signup","Successfully signed up " + username.getText().toString() + "!");
                    Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_LONG).show();
                    Log.d("Parse", "Register Success");
                } else {
                    ParseUser.logOut();
                    Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_LONG).show();
                    Log.d("Parse", "Register Fail " + e);
                }
                Log.d("Parse", "Register Finish");
            }
        });
    }
    public static boolean isValidEmail(final String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPassword(final String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN="^(?=.[0-9])(?=.[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern= Pattern.compile(PASSWORD_PATTERN);
        matcher=pattern.matcher(password);
        return matcher.matches();
    }
    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
    public void gotoSignInAction(View v) {
        try {
            Intent toOtherIntent = new Intent(this, SignInActivity.class);
            startActivity(toOtherIntent);

        } catch (Exception e) {

        }
    }
}
