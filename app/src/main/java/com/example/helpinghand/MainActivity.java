package com.example.helpinghand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button login =  findViewById(R.id.loginButton);
        Button register =  findViewById(R.id.registerButton);

        login.setOnClickListener(v -> {
            Intent login1 = new Intent(MainActivity.this, Login.class);
            startActivity(login1);
        });



        register.setOnClickListener(v -> {
            Intent register1 = new Intent(MainActivity.this, Register.class);
            startActivity(register1);
        });

    }
}