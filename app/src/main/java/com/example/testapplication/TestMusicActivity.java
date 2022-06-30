package com.example.testapplication;
import java.io.IOException;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TestMusicActivity extends Activity {
    /** Called when the activity is first created. */

    private SeekBar SoundseekBar,ProceseekBar2;
    private Button button;
    private MediaPlayer mediaPlayer;
    private TextView nowPlayTime,allTime,volumeView,maxVolumeTextView;
    private AudioManager audioManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prossbar);


        ProceseekBar2=(SeekBar)findViewById(R.id.seekBar1);
       // SoundseekBar=(SeekBar)findViewById(R.id.seekBar2);
        button=(Button)findViewById(R.id.button1);
        nowPlayTime=(TextView)findViewById(R.id.textView1);
        allTime=(TextView)findViewById(R.id.textView2);
        //volumeView=(TextView)findViewById(R.id.textView3);
        //maxVolumeTextView=(TextView)findViewById(R.id.textView4);
        button.setOnClickListener(new ButtonListener());

        mediaPlayer=new MediaPlayer();
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);//获取音量服务
        int MaxSound=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//获取系统音量最大值
        maxVolumeTextView.setText(String.valueOf(MaxSound));
        //SoundseekBar.setMax(MaxSound);//音量控制Bar的最大值设置为系统音量最大值
        //int currentSount=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);//获取当前音量
        //SoundseekBar.setProgress(currentSount);//音量控制Bar的当前值设置为系统音量当前值
        //SoundseekBar.setOnSeekBarChangeListener(new SeekBarListener());
        ProceseekBar2.setOnSeekBarChangeListener(new ProcessBarListener());

    }


    class ButtonListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            else {
                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource("/sdcard/test.mp3");
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    StrartbarUpdate();
                    int Alltime= mediaPlayer.getDuration();
                    allTime.setText(ShowTime(Alltime));
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }


//播放进度条

    class ProcessBarListener implements OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
            if (fromUser==true) {
                mediaPlayer.seekTo(progress);
                nowPlayTime.setText(ShowTime(progress));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }


    }

//音量进度条

    class SeekBarListener implements OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
            if (fromUser) {
                int SeekPosition=seekBar.getProgress();
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, SeekPosition, 0);
            }
            volumeView.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

    }
    //时间显示函数,我们获得音乐信息的是以毫秒为单位的，把把转换成我们熟悉的00:00格式
    public String ShowTime(int time){
        time/=1000;
        int minute=time/60;
        int hour=minute/60;
        int second=time%60;
        minute%=60;
        return String.format("%02d:%02d", minute, second);
    }
    Handler handler=new Handler();
    public void StrartbarUpdate(){
        handler.post(r);
    }
    Runnable r=new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int CurrentPosition=mediaPlayer.getCurrentPosition();
            nowPlayTime.setText(ShowTime(CurrentPosition));
            int mMax=mediaPlayer.getDuration();
            ProceseekBar2.setMax(mMax);
            ProceseekBar2.setProgress(CurrentPosition);
            handler.postDelayed(r, 100);
        }
    };



}