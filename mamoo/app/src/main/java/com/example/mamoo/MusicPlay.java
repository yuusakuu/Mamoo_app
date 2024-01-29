package com.example.mamoo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.media.AudioManager;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

public class MusicPlay extends AppCompatActivity {
    private int resId;
    private Button btn_play;
    private Button btn_stop;

    MediaPlayer mediaPlayer;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;
    private Button bluetoothButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_play);
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("str");

        String str = "sample_music";

        resId = getResources().getIdentifier(str, "raw", getPackageName());


//        if (resId != 0) {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.start();


        btn_play = (Button) findViewById(R.id.btn_play);
        btn_play.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.start();
        });

        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(v -> {
            mediaPlayer.stop();
        });
    }
}
