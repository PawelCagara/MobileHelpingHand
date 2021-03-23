package com.example.helpinghand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    Button logout;
    Button profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        logout = (Button) findViewById(R.id.buttonLogout);
        profile = (Button) findViewById(R.id.buttonProfile);


        logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
            builder.setTitle("Logout confirmation").
                    setMessage("Are you sure you want to logout?");
            builder.setPositiveButton("Yes",
                    (dialog, id) -> {
                        Intent i = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(i);
                    });
            builder.setNegativeButton("No",
                    (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder.create();
            alert11.show();
        });


        profile.setOnClickListener(v -> {
            Intent profile = new Intent(HomePage.this, Profile.class);
            startActivity(profile);
        });

    }
}
