package com.example.playvideo;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
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
    private ImageView play;
    private VideoView videoArea;
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /**
     * @description:对所有控件进行初始化
     * @author: CLJZ
     * @date: 2019/11/9  16:38
     * @param: []
     * @return: void
     */
    private void init() {
        play = findViewById(R.id.play);
        videoPath = findViewById(R.id.videoPath);
        videoArea = findViewById(R.id.videoArea);
        mediaController = new MediaController(this);
        videoArea.setMediaController(mediaController);
        play.setOnClickListener(this);
    }
    /**
     * @description:按钮点击后播放
     * @author: CLJZ
     * @date: 2019/11/9  16:40
     * @param: [view]
     * @return: void
     */
    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.play)) {
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
        //如果播放区域有内容，并且正准备播放，设置播放按钮为准备播放，播放的状态为未播放
        if (videoArea != null && videoArea.isPlaying()) {
            play.setImageResource(android.R.drawable.ic_media_play);
            videoArea.stopPlayback();
            return;
        }
        //设置播放的路径为设置的路径
        videoArea.setVideoPath(videoPath.getText().toString());
        //开始播放
        videoArea.start();
        //设置播放的按键为粘贴模式
        play.setImageResource(android.R.drawable.ic_media_pause);
        //设置播放完成之后的操作
        videoArea.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                play.setImageResource(android.R.drawable.ic_media_play);
            }
        });
    }
}
