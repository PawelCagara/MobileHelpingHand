package com.example.helpinghand;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;
import com.example.helpinghand.model.User;

import java.sql.SQLException;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        Button login = (Button) findViewById(R.id.loginButton);
        Button register = (Button) findViewById(R.id.registerUserButton);
        username = (EditText) findViewById(R.id.registerUsername);
        password = (EditText) findViewById(R.id.registerPassword);
        email = (EditText) findViewById(R.id.registerEmail);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user_name = username.getText().toString().trim();
                String txt_password = password.getText().toString().trim();
                String email_address = email.getText().toString().trim();
                Database user = new Database();

                if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(email_address) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(Register.this, "All fields are required",
                            Toast.LENGTH_SHORT).show();
                } else {

                    if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                        Toast.makeText(Register.this, "Email invalid",
                                Toast.LENGTH_SHORT).show();
                    } else{
                        try {
                            int c = user.checkIfUserExist(user_name);
                            if(c>0){
                                Toast.makeText(getApplicationContext(), "USER ALREADY EXITS", Toast.LENGTH_LONG).show();
                            } else{
                                user.addUser(user_name,email_address,txt_password);
                                Intent registrationSuccessful = new Intent(Register.this, RegisterSuccessful.class);
                                startActivity(registrationSuccessful);
                            }




                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }



                }



            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Register.this, Login.class);
                startActivity(login);
            }
        });


    }


}
