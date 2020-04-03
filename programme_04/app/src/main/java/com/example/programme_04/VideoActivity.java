package com.example.programme_04;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        //初始化控件
        videoView =findViewById(R.id.video);
        //设置播放地址
//        String URL = "http://223.110.243.162/PLTV/3/224/3221225604/index.m3u8";
        Intent intent = getIntent();
//        String URL = "http://www.chachaba.com/news/uploads/media/190415/6055-1Z415135934.mp4";
        videoView.setVideoPath(intent.getStringExtra("video_url"));
        //添加控制组件
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);


        //提前准本，因为有些视频很大
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //开始
                videoView.start();
            }
        });

        //播放完成监听
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(VideoActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
