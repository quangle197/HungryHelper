package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by QuangLe on 5/6/2018.
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

    }

    public void gotoLogin(View v)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
