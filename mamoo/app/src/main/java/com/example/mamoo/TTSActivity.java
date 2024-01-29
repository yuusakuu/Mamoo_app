package com.example.mamoo;


import static android.content.ContentValues.TAG;
import static android.speech.tts.TextToSpeech.ERROR;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TTSActivity extends AppCompatActivity {

    private TextToSpeech tts;              // TTS 변수 선언
    private EditText editText;
    private Button button01, button02, button03, button04, button05, button06, readbook, STTbutton;
    private String book;
    private String Id;
    private TextView book_text;
    private String line;

    public TTSActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts_activity);


        editText = (EditText) findViewById(R.id.editText);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button03 = (Button) findViewById(R.id.button03);
        button04 = (Button) findViewById(R.id.button04);
        button05 = (Button) findViewById(R.id.button05);
        button06 = (Button) findViewById(R.id.button06);
        readbook = (Button) findViewById(R.id.readbook);
        STTbutton = (Button) findViewById(R.id.STTbutton);

//        center_img = findViewById(R.id.correct_img);




        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // editText에 있는 문장을 읽는다.
                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(2.0f);         // 음성 톤을 2.0배 올려준다.
                tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
                // editText에 있는 문장을 읽는다.
                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(0.5f);         // 음성 톤을 0.5배 내려준다.
                tts.setSpeechRate(1.0f);    // 읽는 속도는 기본 설정
                // editText에 있는 문장을 읽는다.
                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        button04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(1.0f);         // 음성 톤은 기본 설정
                tts.setSpeechRate(2.0f);    // 읽는 속도를 2배 빠르기로 설정
                // editText에 있는 문장을 읽는다.
                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        button05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setPitch(1.0f);         // 음성 톤은 기본 설정
                tts.setSpeechRate(0.5f);    // 읽는 속도를 0.5빠르기로 설정
                // editText에 있는 문장을 읽는다.
                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        button06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book = "book";
                tts.speak(book, TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                book = "book";
//                tts.speak(book_str,TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(book,TextToSpeech.QUEUE_FLUSH, null);
//                Id = "apple";
//                book = "book";
                StringBuilder builder = new StringBuilder();
                try {
                    String txtName = "fairy_tale_preface";
                    int resourceId = getResources().getIdentifier(txtName, "raw", getPackageName());
                    Id = Integer.toString(resourceId);

                    InputStream inputStream = getResources().openRawResource(resourceId);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                        builder.append('\n');
                    }
                    reader.close();
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error reading text file from resource: " + e.getMessage());
                }
//
                String book_str = builder.toString();
                book_text = findViewById(R.id.book_str);
                book_text.setText(book_str);
//                book_text.setText("apple");



//                String imageName = str;
//                int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
//                if (resourceId != 0) {
//                    book.setImageResource(resourceId);

                // editText에 있는 문장을 읽는다.
//                book = "book";
//                String Ide = "Integer";
//                tts.speak(Id,TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(book_str,TextToSpeech.QUEUE_FLUSH, null);
                tts.speak(book_str,TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(Ide,TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(book,TextToSpeech.QUEUE_FLUSH, null);
//                tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        STTbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TTSActivity.this, STTActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}
