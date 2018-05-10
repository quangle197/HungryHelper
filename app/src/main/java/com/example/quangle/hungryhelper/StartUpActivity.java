package com.example.quangle.hungryhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by QuangLe on 5/9/18.
 */

public class StartUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_page);

        Button go = findViewById(R.id.button);
        go.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                goToMain(view);
            }});


    }
    public void goToMain(View v)
    {
        startActivity(new Intent(this, MainActivity.class));
    }
}
