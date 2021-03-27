package com.example.helpinghand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.helpers.UserLoginCache;

import java.sql.SQLException;

public class HomePage extends AppCompatActivity {

    private Button logout;
    private Button profile;
    private Button helpProviders;
    private Button helpRevicers;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);


        Database db = new Database();
        String user = UserLoginCache.getLoggedUser();

        /*
        try {
            if(db.checkUserGroup(user)==0) {
                setContentView(R.layout.home_page);
            } else{
                setContentView(R.layout.profile);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        
         */
        logout =  findViewById(R.id.buttonLogout);
        profile =  findViewById(R.id.buttonProfile);
        helpProviders =  findViewById(R.id.buttonHelpers);
        helpRevicers =  findViewById(R.id.buttonNeedHelper);




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

        helpProviders.setOnClickListener(v -> {
            int setGroup = 1;
            try {
                db.setAsGiverOrReciver(user,setGroup);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Intent helper = new Intent(HomePage.this, Helpers.class);
            startActivity(helper);
        });

        helpRevicers.setOnClickListener(v ->{
            Intent InNeedOfHelper = new Intent(HomePage.this, InNeedGroup.class);
            startActivity(InNeedOfHelper);
        });

    }
}
