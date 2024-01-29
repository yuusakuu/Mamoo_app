package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Enroll extends AppCompatActivity {
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll);
        btn = findViewById(R.id.find);
        btn.setOnClickListener(v -> {
//            str = "prac";
            Intent intent = new Intent( Enroll.this, Enroll2.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });


    }
}
