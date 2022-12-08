package com.example.musicconnectionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button playBtn, pauseBtn, replayBtn, endBtn;
    String TAG = "[MainActivity]";
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer mediaPlayer;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "재생 버튼 클릭");
                Toast.makeText(getApplicationContext(), "음악 재생", Toast.LENGTH_SHORT).show();
                // 재생 시키면 로그로 파일 이름 찍고, 음악 스트리밍 할 것임

                playAudio();

            }
        });

        pauseBtn = findViewById(R.id.pauseBtn);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pauseAudio();

            }
        });

        replayBtn = findViewById(R.id.rePlayBtn);
        replayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resumeAudio();

            }
        });

        endBtn = findViewById(R.id.endBtn);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopAudio();

            }
        });
    }

    private void playAudio() {
        try {
            closePlayer();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

            Toast.makeText(getApplicationContext(),"재생 시작", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pauseAudio() {
        if (mediaPlayer != null) {
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();

            Toast.makeText(getApplicationContext(),"일시정지", Toast.LENGTH_SHORT).show();

        }
    }

    private void resumeAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
            mediaPlayer.start();

            Toast.makeText(getApplicationContext(),"재생 재시작", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            closePlayer();
            Toast.makeText(getApplicationContext(),"재생 종료", Toast.LENGTH_SHORT).show();
        }

    }

    public void closePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}