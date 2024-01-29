package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Introduce extends AppCompatActivity {

    private Button btn, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);

        btn = findViewById(R.id.test_stop);
        btn.setOnClickListener(v -> {
//            Intent intent = new Intent( SubActivity.this, Conv.class);
//            intent.putExtra("str", str);
//            startActivity(intent);
        });

        btn2 = findViewById(R.id.result);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent( Introduce.this, LevelResult.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });
    }
}
