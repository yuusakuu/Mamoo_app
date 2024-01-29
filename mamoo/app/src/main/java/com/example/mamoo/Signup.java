package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    private Button btn, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btn = findViewById(R.id.signup);
        btn.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Signup.this, SubActivity.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

        btn2 = findViewById(R.id.login);
        btn2.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Signup.this, Login.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });
    }
}
