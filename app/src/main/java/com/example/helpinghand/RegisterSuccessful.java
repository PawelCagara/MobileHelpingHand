
package com.example.helpinghand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterSuccessful extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_successful);

        Button login = findViewById(R.id.loginButton);

        login.setOnClickListener(v -> {
            Intent login1 = new Intent(RegisterSuccessful.this, Login.class);
            startActivity(login1);
        });
    }
}
