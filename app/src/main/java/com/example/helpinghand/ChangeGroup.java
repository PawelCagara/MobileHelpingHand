
package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.helpers.UserLoginCache;

import java.sql.SQLException;

public class ChangeGroup extends AppCompatActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_change_group);

        Database db = new Database();
        String user = UserLoginCache.getLoggedUser();

        Button helpProviders = findViewById(R.id.buttonChangeToGroup1);
        Button helpRecivers = findViewById(R.id.buttonChangeToGroup2);

        helpProviders.setOnClickListener(v->{
            int setGroup = 1;
            try {
                db.setAsGiverOrReciver(user,setGroup);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Intent helper = new Intent(ChangeGroup.this, HomeHelpers.class);
            startActivity(helper);
        });

        helpRecivers.setOnClickListener(v ->{
            int setGroup = 2;
            try {
                db.setAsGiverOrReciver(user,setGroup);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Intent inNeedOfHelper = new Intent(ChangeGroup.this, HomeInNeed.class);
            startActivity(inNeedOfHelper);
        });
    }

}
