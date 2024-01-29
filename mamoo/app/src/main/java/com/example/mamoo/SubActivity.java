package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {

    private Button level_test, msn, conv1, conv2, conv3, conv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        level_test = findViewById(R.id.level_test);
        level_test.setOnClickListener(v -> {
                    Intent intent = new Intent( SubActivity.this, LevelTest.class);
//            intent.putExtra("str", str);
                    startActivity(intent);
        });


        msn = findViewById(R.id.today);
        msn.setOnClickListener(v -> {
            Intent intent = new Intent( SubActivity.this, TodayMission.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

        conv1 = findViewById(R.id.conv1);
        conv1.setOnClickListener(v -> {
            Intent intent = new Intent( SubActivity.this, Conv.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });

        conv2 = findViewById(R.id.conv2);
        conv2.setOnClickListener(v -> {
            Intent intent = new Intent( SubActivity.this, Conv.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });
        conv3 = findViewById(R.id.conv3);
        conv3.setOnClickListener(v -> {
            Intent intent = new Intent( SubActivity.this, Conv.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });
        conv4 = findViewById(R.id.conv4);
        conv4.setOnClickListener(v -> {
            Intent intent = new Intent( SubActivity.this, Conv.class);
//            intent.putExtra("str", str);
            startActivity(intent);
        });



    }
}
