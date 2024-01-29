package com.example.mamoo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoPlay  extends AppCompatActivity {

    private Button vid_play, vid_stop, playButton;
    private Integer resId;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);
        String str = "sample_video";
        VideoView videoView = findViewById(R.id.videoView);

//
//        // 동영상 파일의 경로를 지정합니다. R.raw 폴더에 있는 동영상의 이름을 사용합니다.
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.sample_video;
//
//        // VideoView에 동영상 파일을 설정합니다.
        videoView.setVideoURI(Uri.parse(videoPath));
//
//        // 동영상 컨트롤러를 추가합니다.
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
//
//        // 동영상 재생을 시작합니다.
//        videoView.start();

//        resId = getResources().getIdentifier(str, "raw", getPackageName());

//        if (resId != 0) {
//            mediaPlayer = MediaPlayer.create(this, resId);
//            mediaPlayer.start();
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlaying) {
                    // 동영상이 재생 중이면 일시정지
                    videoView.pause();
                    playButton.setText("재생");
                } else {
                    // 동영상이 일시정지 상태면 재생
                    videoView.start();
                    playButton.setText("일시정지");
                }
                isPlaying = !isPlaying;
            }

//        vid_play = (Button) findViewById(R.id.btn_play);
//        vid_play.setOnClickListener(v -> {
//            videoView.start();
//        });
//
//        vid_stop = (Button) findViewById(R.id.btn_stop);
//        vid_stop.setOnClickListener(v -> {
//            videoView.pause();
        });


    }
}
