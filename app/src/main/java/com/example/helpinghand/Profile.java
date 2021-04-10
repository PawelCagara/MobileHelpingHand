package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.helpers.UserLoginCache;

public class Profile extends AppCompatActivity {

    private TextView wellcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        wellcomeMessage = findViewById(R.id.textViewProfilePage);
        Button updateProfile = findViewById(R.id.buttonChangeProfile);
        Button changePassword = findViewById(R.id.buttonChangePassword);
        Button changeGroup = findViewById(R.id.buttonProfileChangeGroup);

        String user = UserLoginCache.getLoggedUser();
        wellcomeMessage.setText("Hello "+user+", you can update your details here");

        updateProfile.setOnClickListener(v->{
            Intent update = new Intent(Profile.this, ProfileUpdate.class);
            startActivity(update);
        });

        changePassword.setOnClickListener(v->{
            Intent updatePassword = new Intent(Profile.this, PasswordChange.class);
            startActivity(updatePassword);
        });

        changeGroup.setOnClickListener(v-> {
            Intent updateGroup = new Intent(Profile.this, ChangeGroup.class);
            startActivity(updateGroup);
        });
    }


}