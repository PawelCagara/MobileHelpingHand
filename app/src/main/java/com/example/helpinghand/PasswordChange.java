package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.helpers.UserLoginCache;

import java.sql.SQLException;

public class PasswordChange extends AppCompatActivity {

    private EditText oldPassword;
    private EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change);
        Button updatePassword = findViewById(R.id.buttonUpdatePassword);

        oldPassword = findViewById(R.id.editTextOldPassword);
        newPassword = findViewById(R.id.editTextNewPassword);

        Database db = new Database();
        String user = UserLoginCache.getLoggedUser();

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
                        Intent passwordUpdatedSuccessfuly = new Intent(PasswordChange.this, HomePage.class);
                        startActivity(passwordUpdatedSuccessfuly);
                        Toast.makeText(getApplicationContext(), "Password update was successful", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(PasswordChange.this, "Old password incorrect, try again",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
    }
}
