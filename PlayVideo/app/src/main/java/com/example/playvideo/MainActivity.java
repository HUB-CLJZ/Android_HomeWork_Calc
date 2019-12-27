package com.example.playvideo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

/**
  * @Package:        com.example.playvideo
  * @ClassName:      MainActivity
  * @Description:    播放视频的类，安卓4
  * @Author:         CLJZ
  * @CreateDate:     2019/11/9 17:06
  * @UpdateUser:     CLJZ
  * @UpdateDate:     2019/11/9 17:06
  * @UpdateRemark:   更新说明：
  * @Version:        1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText videoPath;
    PlayerView play;

    private VideoView videoArea;
    private MediaController mediaController;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*videoArea = findViewById(R.id.play);
        Uri uri = Uri.parse("http://www.chachaba.com/news/uploads/media/190415/6055-1Z415135934.mp4");
        videoArea.setVideoURI(uri);
        // 开始播放视频
        videoArea.start();*/

        String url="http://www.chachaba.com/news/uploads/media/190415/6055-1Z415135934.mp4";
        play = new PlayerView(this)
                .setTitle("坤坤打篮球")//视频名称
                .setScaleType(PlayStateParams.fillparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(url);
        play.startPlay();

    }
    /**
     * @description:对所有控件进行初始化
     * @author: CLJZ
     * @date: 2019/11/9  16:38
     * @param: []
     * @return: void
     */
//    private void init() {
//        play = findViewById(R.id.play);
//        videoArea = findViewById(R.id.videoArea);
//        //mediaController = new MediaController(this);
//        //videoArea.setMediaController(mediaController);
//        //play.setOnClickListener(this);
//    }
    /**
     * @description:按钮点击后播放
     * @author: CLJZ
     * @date: 2019/11/9  16:40
     * @param: [view]
     * @return: void
     */
    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.start)) {
            play();
        }
    }
    /**
     * @description:播放方法的具体实现
     * @author: CLJZ
     * @date: 2019/11/9  16:40
     * @param: []
     * @return: void
     */
    private void play() {
//        //如果播放区域有内容，并且正准备播放，设置播放按钮为准备播放，播放的状态为未播放
//        if (videoArea != null && videoArea.isPlaying()) {
//            play.setImageResource(android.R.drawable.ic_media_play);
//            videoArea.stopPlayback();
//            return;
//        }
        //设置播放的路径为设置的路径
        Uri uri = Uri.parse("http://www.chachaba.com/news/uploads/media/190415/6055-1Z415135934.mp4");
        Uri uri1 = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        videoArea.setMediaController(new MediaController(this));
        videoArea.setVideoURI(uri1);
        //videoArea.requestFocus();
        //videoArea.setVideoPath(videoPath.getText().toString());
        //开始播放
        videoArea.start();
        //设置播放的按键为粘贴模式
//        play.setImageResource(android.R.drawable.ic_media_pause);
        //设置播放完成之后的操作
//        videoArea.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                play.setImageResource(android.R.drawable.ic_media_play);
//            }
//        });
    }

}
