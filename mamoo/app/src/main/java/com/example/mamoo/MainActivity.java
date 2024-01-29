package com.example.mamoo;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts;              // TTS 변수 선언
    private EditText editText;
    private Button button01, button02, button03, button04, button05, STTbutton;
    private Button btn_move, btn_test, btn_play, btn_video, bluetooth;


    private String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list_page = findViewById(R.id.list);
        //str = et_test.getText().toString();
//        btn_move = (Button) findViewById(R.id.startButton);
//        btn_move.setOnClickListener(v -> {
//                    str = "prac";
//                    Intent intent = new Intent( MainActivity.this, STTActivity.class);
//                    intent.putExtra("str", str);
//                    startActivity(intent);
//                }
//        );
        btn_move = (Button) findViewById(R.id.startButton);
        btn_move.setOnClickListener(v -> {
                    str = "prac";
                    Intent intent = new Intent( MainActivity.this, SubActivity.class);
                    intent.putExtra("str", str);
                    startActivity(intent);
                }
        );


//        btn_test = (Button) findViewById(R.id.testButton);
//        btn_test.setOnClickListener(v -> {
//                    str = "test";
//                    Intent intent = new Intent( MainActivity.this, TTSActivity.class);
//                    intent.putExtra("str", str);
//                    startActivity(intent);
//                });
//
//        btn_play = (Button) findViewById(R.id.playButton);
//        btn_play.setOnClickListener(v -> {
//                    str = "test";
//                    Intent intent = new Intent(MainActivity.this, MusicPlay.class);
//                    intent.putExtra("str", str);
//                    startActivity(intent);
//                }
//        );
//
//        btn_video = (Button) findViewById(R.id.videoButton);
//        btn_video.setOnClickListener(v -> {
//            str = "test";
//            Intent intent = new Intent(MainActivity.this, VideoPlay.class);
//            intent.putExtra("str", str);
//            startActivity(intent);
//        });
//
//        bluetooth = (Button) findViewById(R.id.bluetoothButton);
//        bluetooth.setOnClickListener(v -> {
//            str = "test";
//            Intent intent = new Intent(MainActivity.this, BluetoothMusic.class);
//            intent.putExtra("str", str);
//            startActivity(intent);
//        });
    }
}