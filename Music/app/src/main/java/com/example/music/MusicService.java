package com.example.music;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import java.io.IOException;
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    public MediaPlayer mediaPlayer;
    public MusicService() {
    }

    class MyBinder extends Binder {
        //播放音乐
        public void play(String path) {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(path);

                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            //开始播放
                    mediaPlayer.start();
                        }
                    });
                } else {
                    int position = getCurrentProgress();
                    mediaPlayer.seekTo(position);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //暂停播放
        public void pause() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else if (mediaPlayer != null && (!mediaPlayer.isPlaying())) {
                mediaPlayer.start();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getCurrentProgress() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            return mediaPlayer.getCurrentPosition();
        } else if (mediaPlayer != null && (!mediaPlayer.isPlaying())) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }



    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}
