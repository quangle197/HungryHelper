package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

    }

    public void gotoForgot(View v)
    {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }
}
