package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login2 extends AppCompatActivity {

    private Button btn, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);

        btn = findViewById(R.id.login);
        btn.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Login2.this, SubActivity.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

        btn2 = findViewById(R.id.login2);
        btn2.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Login2.this, SubActivity.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

    }
}
