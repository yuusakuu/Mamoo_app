package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LevelTest extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_test);

        btn = findViewById(R.id.test);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent( LevelTest.this, Introduce.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });
    }
}
