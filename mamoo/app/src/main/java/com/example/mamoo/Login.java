package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
//                    str = "prac";
                    Intent intent = new Intent( Login.this, Login2.class);
//                    intent.putExtra("str", str);
                    startActivity(intent);
                }
        );

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Login.this, Signup.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

    }


}
