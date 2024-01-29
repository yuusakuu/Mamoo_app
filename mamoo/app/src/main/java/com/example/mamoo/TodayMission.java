package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TodayMission extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_mission);

        btn1 = findViewById(R.id.test1);
        btn1.setOnClickListener(v -> {
            Intent intent = new Intent( TodayMission.this, LevelTest.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });


        btn2 = findViewById(R.id.test2);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent( TodayMission.this, LevelTest.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

        btn3 = findViewById(R.id.test3);
        btn3.setOnClickListener(v -> {
            Intent intent = new Intent( TodayMission.this, LevelTest.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

    }
}
