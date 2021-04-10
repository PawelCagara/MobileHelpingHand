package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HomeInNeed extends AppCompatActivity {

    private Button logout;
    private Button profile;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_in_need);

        logout = findViewById(R.id.buttonLogoutInNeed);
        profile = findViewById(R.id.buttonProfileInNeed);

        logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeInNeed.this);
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
            Intent profile = new Intent(HomeInNeed.this, Profile.class);
            startActivity(profile);
        });
    }
}
