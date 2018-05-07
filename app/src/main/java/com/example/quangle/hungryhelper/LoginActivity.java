package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity{
    EditText usernameText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        usernameText = findViewById(R.id.loginPage_Username);
        passwordText = findViewById(R.id.loginPage_Password);
    }

    public void gotoForgot(View v)
    {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
    public void gotoSignUp(View v)
    {
        startActivity(new Intent(this, SignupActivity.class));
    }
}
