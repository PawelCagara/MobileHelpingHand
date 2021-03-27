package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.helpers.UserLoginCache;

import java.sql.SQLException;

public class ProfileUpdate extends AppCompatActivity {


    private EditText email;
    private EditText firstname;
    private EditText postcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);
        Button confirm = findViewById(R.id.buttonProfileUpdate);
        email = findViewById(R.id.editTextProfileEmial);;
        firstname = findViewById(R.id.editTextProfileFirstName);
        postcode = findViewById(R.id.editTextPostcode);


        Database db = new Database();
        String user = UserLoginCache.getLoggedUser();

        try {
            String emailProfile = db.readEmail(user);
            String firstnameProfile = db.readFirstname(user);
            String postcodeProfile = db.readPostcode(user);

            email.setText(emailProfile);
            firstname.setText(firstnameProfile);
            postcode.setText(postcodeProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        confirm.setOnClickListener(v-> {
            try {

                String first_name = firstname.getText().toString();
                String post_code = postcode.getText().toString();
                String email_address = email.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    Toast.makeText(ProfileUpdate.this, "Email invalid",
                            Toast.LENGTH_SHORT).show();
                } else{
                    db.updateUserProfile(user, email_address, first_name, post_code);
                    Intent updateSuccessful = new Intent(ProfileUpdate.this, HomePage.class);
                    startActivity(updateSuccessful);
                    Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_LONG).show();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });
    }
}


/*

package com.example.helpinghand;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.helpers.UserLoginCache;
import com.example.helpinghand.model.User;

import java.sql.SQLException;

import static com.google.android.material.internal.ContextUtils.getActivity;

public class Profile extends AppCompatActivity {


    private TextView wellcomeMessage;
    private EditText email;
    private EditText firstname;
    private EditText postcode;
    private EditText oldPassword;
    private EditText newPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_update);

        Button confirm = findViewById(R.id.buttonProfileUpdate);
        Button updatePassword = findViewById(R.id.buttonUpdatePassword);
        email = findViewById(R.id.editTextProfileEmial);;
        firstname = findViewById(R.id.editTextProfileFirstName);
        postcode = findViewById(R.id.editTextPostcode);
        wellcomeMessage = findViewById(R.id.textViewProfilePage);
        oldPassword = findViewById(R.id.editTextOldPassword);
        newPassword = findViewById(R.id.editTextNewPassword);


        Database db = new Database();

        String user = UserLoginCache.getLoggedUser();

        try {
            String emailProfile = db.readEmail(user);
            String firstnameProfile = db.readFirstname(user);
            String postcodeProfile = db.readPostcode(user);

            email.setText(emailProfile);
            firstname.setText(firstnameProfile);
            postcode.setText(postcodeProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        wellcomeMessage.setText("Hello "+user+", you can update your details here");


        confirm.setOnClickListener(v-> {
            try {

                String first_name = firstname.getText().toString();
                String post_code = postcode.getText().toString();
                String email_address = email.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    Toast.makeText(Profile.this, "Email invalid",
                            Toast.LENGTH_SHORT).show();
                } else{
                    db.updateUserProfile(user, email_address, first_name, post_code);
                    Intent updateSuccessful = new Intent(Profile.this, HomePage.class);
                    startActivity(updateSuccessful);
                    Toast.makeText(getApplicationContext(), "Update successful", Toast.LENGTH_LONG).show();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });

        updatePassword.setOnClickListener(v-> {
            String old_password = oldPassword.getText().toString();
            String new_password = newPassword.getText().toString();

            try {
                int c = db.checkUsernameAndPassword(user,old_password);
                if(c>0){
                    if(new_password.length()<6 || new_password.length()>100){
                        Toast.makeText(getApplicationContext(), "New password must be 6-100 characters long", Toast.LENGTH_LONG).show();
                    } else {
                        db.updatePassword(user, new_password);
                        Intent passwordUpdatedSuccessfuly = new Intent(Profile.this, HomePage.class);
                        startActivity(passwordUpdatedSuccessfuly);
                        Toast.makeText(getApplicationContext(), "Password update was successful", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(Profile.this, "Old password incorrect, try again",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

    }


}





 */