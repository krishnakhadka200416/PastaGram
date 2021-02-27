package com.example.pastagram;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;

    private EditText etPasword;
    private Button btLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(ParseUser.getCurrentUser() !=null){
            goMainActivity();
        }


        etEmail = findViewById(R.id.etEmail);
        etPasword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btnSignUp =findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Sign Up");
                String username = etEmail.getText().toString();
                String password = etPasword.getText().toString();
                ParseUser user = new ParseUser();

                user.setPassword(password);
                user.setUsername(username);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(LoginActivity.this, "Sign Up Successful", Toast.LENGTH_LONG ).show();
                            goMainActivity();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_LONG ).show();
                        }
                    }
                });
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "login pressed");
                String username = etEmail.getText().toString();
                String password = etPasword.getText().toString();
                LoginUser(username, password);
            }
        });


    }

    private void LoginUser(String username, String password) {
        Log.i("LoginActivity", "Attemped login" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                     if(e != null){
                         Log.e("LoginActivity","Issue with login", e);
                         Toast.makeText(LoginActivity.this, "" +e.getMessage(), Toast.LENGTH_SHORT).show();

                         return;
                     }
                     
                     goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}