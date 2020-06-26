package andoid.example.mypiano;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Recording code
    private Button startbtn, stopbtn, playbtn, stopplay;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;



    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    int mcurrentVideoPosition;

    private SoundPool soundPool;
    private RelativeLayout layout;

    private Button left_navigation,right_navigation,recordbutton,playbutton;

    private int c3,c3black,d3,d3black,e3,f3,f3black,g3,g3black,a3,a3black,b3;
    private int c4,c4black,d4,d4black,e4,f4,f4black,g4,g4black,a4,a4black,b4;
    private int c5,c5black,d5,d5black,e5,f5,f5black,g5,g5black,a5,a5black,b5;
    private int c6,c6black,d6,d6black,e6,f6,f6black,g6,g6black,a6,a6black,b6;
    private int c7,c7black,d7,d7black,e7,f7,f7black,g7,g7black,a7,a7black, b7;


    private HorizontalScrollView scrollView;

    private Button buttonC3;
    private Button buttonC3black;

    private Button buttonD3;
    private Button buttonD3black;

    private Button buttonE3;

    private Button buttonF3;
    private Button buttonF3black;

    private Button buttonG3;
    private Button buttonG3black;

    private Button buttonA3;
    private Button buttonA3black;

    private Button buttonB3;

    private Button buttonC4;
    private Button buttonC4black;

    private Button buttonD4;
    private Button buttonD4black;

    private Button buttonE4;

    private Button buttonF4;
    private Button buttonF4black;

    private Button buttonG4;
    private Button buttonG4black;

    private Button buttonA4;
    private Button buttonA4black;

    private Button buttonB4;

    private Button buttonC5;
    private Button buttonC5black;

    private Button buttonD5;
    private Button buttonD5black;

    private Button buttonE5;

    private Button buttonF5;
    private Button buttonF5black;

    private Button buttonG5;
    private Button buttonG5black;

    private Button buttonA5;
    private Button buttonA5black;

    private Button buttonB5;

    private Button buttonC6;
    private Button buttonC6black;

    private Button buttonD6;
    private Button buttonD6black;

    private Button buttonE6;

    private Button buttonF6;
    private Button buttonF6black;

    private Button buttonG6;
    private Button buttonG6black;

    private Button buttonA6;
    private Button buttonA6black;

    private Button buttonB6;

    private Button buttonC7;
    private Button buttonC7black;

    private Button buttonD7;
    private Button buttonD7black;

    private Button buttonE7;

    private Button buttonF7;
    private Button buttonF7black;

    private Button buttonG7;
    private Button buttonG7black;

    private Button buttonA7;
    private Button buttonA7black;

    private Button buttonB7;

//    text view parameters
    private TextView tc3;
    private TextView td3;
    private TextView te3;
    private TextView tf3;
    private TextView tg3;
    private TextView ta3;
    private TextView tb3;

    private TextView tc4;
    private TextView td4;
    private TextView te4;
    private TextView tf4;
    private TextView tg4;
    private TextView ta4;
    private TextView tb4;

    private TextView tc5;
    private TextView td5;
    private TextView te5;
    private TextView tf5;
    private TextView tg5;
    private TextView ta5;
    private TextView tb5;

    private TextView tc6;
    private TextView td6;
    private TextView te6;
    private TextView tf6;
    private TextView tg6;
    private TextView ta6;
    private TextView tb6;

    private TextView tc7;
    private TextView td7;
    private TextView te7;
    private TextView tf7;
    private TextView tg7;
    private TextView ta7;
    private TextView tb7;

    private InterstitialAd mInterstitialAd;

    private Button musicbtn;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        musicbtn = (Button)findViewById(R.id.btnMusic);
        musicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });



        //Recording fuction
        startbtn = (Button)findViewById(R.id.btnRecord);
        stopbtn = (Button)findViewById(R.id.btnStop);
        playbtn = (Button)findViewById(R.id.btnPlay);
        stopplay = (Button)findViewById(R.id.btnStopPlay);
        stopbtn.setEnabled(false);
        playbtn.setEnabled(false);
        stopplay.setEnabled(false);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/AudioRecording.3gp";

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckPermissions()) {
                    stopbtn.setEnabled(true);
                    startbtn.setEnabled(false);
                    playbtn.setEnabled(false);
                    stopplay.setEnabled(false);
                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder.setOutputFile(mFileName);
                    try {
                        mRecorder.prepare();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "prepare() failed");
                    }
                    mRecorder.start();
                    Toast.makeText(getApplicationContext(), "Recording Started", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestPermissions();
                }
            }
        });
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(true);
                stopplay.setEnabled(true);
                mRecorder.stop();
                mRecorder.release();
                mRecorder = null;
                Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
            }
        });
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(false);
                stopplay.setEnabled(true);
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(mFileName);
                    mPlayer.prepare();
                    mPlayer.start();
                    Toast.makeText(getApplicationContext(), "Recording Started Playing", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "prepare() failed");
                }
            }
        });
        stopplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.release();
                mPlayer = null;
                stopbtn.setEnabled(false);
                startbtn.setEnabled(true);
                playbtn.setEnabled(true);
                stopplay.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Playing Audio Stopped", Toast.LENGTH_SHORT).show();
            }
        });

//
//        random color background
        //layout= findViewById(R.id.layoutback);

        //scroll view
        scrollView = findViewById(R.id.scrollView);

        //initalize button method
        AllButtons();

//        intialize text view
        Textviewpiano();

//        code for soundpool
        soundPool = new SoundPool.Builder().setMaxStreams(6).build();

        c3 = soundPool.load(this,R.raw.c3,1);
        c3black = soundPool.load(this,R.raw.c3black,1);
        d3 = soundPool.load(this,R.raw.d3,1);
        d3black = soundPool.load(this,R.raw.d3black,1);
        e3 = soundPool.load(this,R.raw.e3,1);
        f3 = soundPool.load(this,R.raw.f3,1);
        f3black = soundPool.load(this,R.raw.f3black,1);
        g3 = soundPool.load(this,R.raw.g3,1);
        g3black = soundPool.load(this,R.raw.g3black,1);
        a3 = soundPool.load(this,R.raw.a3,1);
        a3black = soundPool.load(this,R.raw.a3black,1);
        b3 = soundPool.load(this,R.raw.b3,1);

        c4 = soundPool.load(this,R.raw.c4,1);
        c4black = soundPool.load(this,R.raw.c4black,1);
        d4 = soundPool.load(this,R.raw.d4,1);
        d4black = soundPool.load(this,R.raw.d4black,1);
        e4 = soundPool.load(this,R.raw.e4,1);
        f4 = soundPool.load(this,R.raw.f4,1);
        f4black = soundPool.load(this,R.raw.f4black,1);
        g4 = soundPool.load(this,R.raw.g4,1);
        g4black = soundPool.load(this, R.raw.g4black, 1);
        a4 = soundPool.load(this,R.raw.a4,1);
        a4black = soundPool.load(this,R.raw.a4black,1);
        b4 = soundPool.load(this,R.raw.b4,1);

        c5 = soundPool.load(this,R.raw.c5,1);
        c5black = soundPool.load(this,R.raw.c5black,1);
        d5 = soundPool.load(this,R.raw.d5,1);
        d5black = soundPool.load(this,R.raw.d5black,1);
        e5 = soundPool.load(this,R.raw.e5,1);
        f5 = soundPool.load(this,R.raw.f5,1);
        f5black = soundPool.load(this,R.raw.f5black,1);
        g5 = soundPool.load(this,R.raw.g5,1);
        g5black = soundPool.load(this,R.raw.g5black,1);
        a5 = soundPool.load(this,R.raw.a5,1);
        a5black = soundPool.load(this,R.raw.a5black,1);
        b5 = soundPool.load(this,R.raw.b5,1);

        c6 = soundPool.load(this,R.raw.c6,1);
        c6black = soundPool.load(this,R.raw.c6black,1);
        d6 = soundPool.load(this,R.raw.d6,1);
        d6black = soundPool.load(this,R.raw.d6black,1);
        e6 = soundPool.load(this,R.raw.e6,1);
        f6 = soundPool.load(this,R.raw.f6,1);
        f6black = soundPool.load(this,R.raw.f6black,1);
        g6 = soundPool.load(this,R.raw.g6,1);
        g6black = soundPool.load(this,R.raw.g6black,1);
        a6 = soundPool.load(this,R.raw.a6,1);
        a6black = soundPool.load(this,R.raw.a6black,1);
        b6 = soundPool.load(this,R.raw.b6,1);

        c7 = soundPool.load(this,R.raw.c7,1);
        c7black = soundPool.load(this,R.raw.c7black,1);
        d7 = soundPool.load(this,R.raw.d7,1);
        d7black = soundPool.load(this,R.raw.d7black,1);
        e7 = soundPool.load(this,R.raw.e7,1);
        f7 = soundPool.load(this,R.raw.f7,1);
        f7black = soundPool.load(this,R.raw.f7black,1);
        g7 = soundPool.load(this,R.raw.g7,1);
        g7black = soundPool.load(this,R.raw.g7black,1);
        a7 = soundPool.load(this,R.raw.a7,1);
        a7black = soundPool.load(this,R.raw.a7black,1);
        b7 = soundPool.load(this,R.raw.b7,1);

        left_navigation = (Button) findViewById(R.id.bt_left_navigation);
        right_navigation = (Button) findViewById(R.id.bt_right_navigation);


        left_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo((int) scrollView.getScrollX() - 80, (int) scrollView.getScrollX());
            }
        });

        right_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo((int) scrollView.getScrollX()+80, (int) scrollView.getScrollX());
            }
        });

//backgroundvideo
        videoBG = (VideoView) findViewById(R.id.videoview);

        // Build your video Uri
        videoBG.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.pianoplay);
        // Start the VideoViewThe file name must end with .xml or .png
        videoBG.start();

        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setLooping(true);
                // We then seek to the current posistion if it has been set and play the video.
                if (mcurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mcurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });


    }





//background video.
    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
        mcurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the video when resuming the Activity
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
//    textview function
    void Textviewpiano() {
        tc3 = (TextView) findViewById(R.id.tc3);
        tc3.setOnClickListener(this);
        td3 = (TextView) findViewById(R.id.td3);
        tc3.setOnClickListener(this);
        te3 = (TextView) findViewById(R.id.te3);
        tc3.setOnClickListener(this);
        tf3 = (TextView) findViewById(R.id.tf3);
        tc3.setOnClickListener(this);
        tg3 = (TextView) findViewById(R.id.tg3);
        tc3.setOnClickListener(this);
        ta3 = (TextView) findViewById(R.id.ta3);
        tc3.setOnClickListener(this);
        tb3 = (TextView) findViewById(R.id.tb3);
        tc3.setOnClickListener(this);

        tc4 = (TextView) findViewById(R.id.tc4);
        tc4.setOnClickListener(this);
        td4 = (TextView) findViewById(R.id.td4);
        tc4.setOnClickListener(this);
        te4 = (TextView) findViewById(R.id.te4);
        tc4.setOnClickListener(this);
        tf4 = (TextView) findViewById(R.id.tf4);
        tc4.setOnClickListener(this);
        tg4 = (TextView) findViewById(R.id.tg4);
        tc4.setOnClickListener(this);
        ta4 = (TextView) findViewById(R.id.ta4);
        tc4.setOnClickListener(this);
        tb4 = (TextView) findViewById(R.id.tb4);
        tc4.setOnClickListener(this);

        tc5 = (TextView) findViewById(R.id.tc5);
        tc5.setOnClickListener(this);
        td5 = (TextView) findViewById(R.id.td5);
        tc5.setOnClickListener(this);
        te5 = (TextView) findViewById(R.id.te5);
        tc5.setOnClickListener(this);
        tf5 = (TextView) findViewById(R.id.tf5);
        tc5.setOnClickListener(this);
        tg5 = (TextView) findViewById(R.id.tg5);
        tc5.setOnClickListener(this);
        ta5 = (TextView) findViewById(R.id.ta5);
        tc5.setOnClickListener(this);
        tb5 = (TextView) findViewById(R.id.tb5);
        tc5.setOnClickListener(this);

        tc6 = (TextView) findViewById(R.id.tc6);
        tc6.setOnClickListener(this);
        td6 = (TextView) findViewById(R.id.td6);
        tc6.setOnClickListener(this);
        te6 = (TextView) findViewById(R.id.te6);
        tc6.setOnClickListener(this);
        tf6 = (TextView) findViewById(R.id.tf6);
        tc6.setOnClickListener(this);
        tg6 = (TextView) findViewById(R.id.tg6);
        tc6.setOnClickListener(this);
        ta6 = (TextView) findViewById(R.id.ta6);
        tc6.setOnClickListener(this);
        tb6 = (TextView) findViewById(R.id.tb6);
        tc6.setOnClickListener(this);

        tc7 = (TextView) findViewById(R.id.tc7);
        tc7.setOnClickListener(this);
        td7 = (TextView) findViewById(R.id.td7);
        tc7.setOnClickListener(this);
        te7 = (TextView) findViewById(R.id.te7);
        tc7.setOnClickListener(this);
        tf7 = (TextView) findViewById(R.id.tf7);
        tc7.setOnClickListener(this);
        tg7 = (TextView) findViewById(R.id.tg7);
        tc7.setOnClickListener(this);
        ta7 = (TextView) findViewById(R.id.ta7);
        tc7.setOnClickListener(this);
        tb7 = (TextView) findViewById(R.id.tb7);
        tc7.setOnClickListener(this);
    }






//buuton function
    void AllButtons(){

//        for button 3's
        buttonC3 = (Button)findViewById(R.id.p1);
        buttonC3.setOnClickListener(this);
        buttonC3black = (Button)findViewById(R.id.b1);
        buttonC3black.setOnClickListener(this);

        buttonD3 = (Button)findViewById(R.id.p2);
        buttonD3.setOnClickListener(this);
        buttonD3black = (Button)findViewById(R.id.b2);
        buttonD3black.setOnClickListener(this);

        buttonE3 = (Button)findViewById(R.id.p3);
        buttonE3.setOnClickListener(this);

        buttonF3 = (Button)findViewById(R.id.p4);
        buttonF3.setOnClickListener(this);
        buttonF3black = (Button)findViewById(R.id.b3);
        buttonF3black.setOnClickListener(this);

        buttonG3 = (Button)findViewById(R.id.p5);
        buttonG3.setOnClickListener(this);
        buttonG3black = (Button)findViewById(R.id.b4);
        buttonG3black.setOnClickListener(this);

        buttonA3 = (Button)findViewById(R.id.p6);
        buttonA3.setOnClickListener(this);
        buttonA3black = (Button)findViewById(R.id.b5);
        buttonA3black.setOnClickListener(this);

        buttonB3 = (Button)findViewById(R.id.p7);
        buttonB3.setOnClickListener(this);

//line for 4
        buttonC4 = (Button)findViewById(R.id.p8);
        buttonC4.setOnClickListener(this);
        buttonC4black = (Button)findViewById(R.id.b6);
        buttonC4black.setOnClickListener(this);

        buttonD4 = (Button)findViewById(R.id.p9);
        buttonD4.setOnClickListener(this);
        buttonD4black = (Button)findViewById(R.id.b7);
        buttonD4black.setOnClickListener(this);

        buttonE4 = (Button)findViewById(R.id.p10);
        buttonE4.setOnClickListener(this);

        buttonF4 = (Button)findViewById(R.id.p11);
        buttonF4.setOnClickListener(this);
        buttonF4black = (Button)findViewById(R.id.b8);
        buttonF4black.setOnClickListener(this);

        buttonG4 = (Button)findViewById(R.id.p12);
        buttonG4.setOnClickListener(this);
        buttonG4black = (Button)findViewById(R.id.b9);
        buttonG4black.setOnClickListener(this);

        buttonA4 = (Button)findViewById(R.id.p13);
        buttonA4.setOnClickListener(this);
        buttonA4black = (Button)findViewById(R.id.b10);
        buttonA4black.setOnClickListener(this);

        buttonB4 = (Button)findViewById(R.id.p14);
        buttonB4.setOnClickListener(this);


//        line for 5
        buttonC5 = (Button)findViewById(R.id.p15);
        buttonC5.setOnClickListener(this);
        buttonC5black = (Button)findViewById(R.id.b11);
        buttonC5black.setOnClickListener(this);

        buttonD5 = (Button)findViewById(R.id.p16);
        buttonD5.setOnClickListener(this);
        buttonD5black = (Button)findViewById(R.id.b12);
        buttonD5black.setOnClickListener(this);

        buttonE5 = (Button)findViewById(R.id.p17);
        buttonE5.setOnClickListener(this);

        buttonF5 = (Button)findViewById(R.id.p18);
        buttonF5.setOnClickListener(this);
        buttonF5black = (Button)findViewById(R.id.b13);
        buttonF5black.setOnClickListener(this);

        buttonG5 = (Button)findViewById(R.id.p19);
        buttonG5.setOnClickListener(this);
        buttonG5black = (Button)findViewById(R.id.b14);
        buttonG5black.setOnClickListener(this);

        buttonA5 = (Button)findViewById(R.id.p20);
        buttonA5.setOnClickListener(this);
        buttonA5black = (Button)findViewById(R.id.b15);
        buttonA5black.setOnClickListener(this);

        buttonB5 = (Button)findViewById(R.id.p21);
        buttonB5.setOnClickListener(this);


//        line for 6
        buttonC6 = (Button)findViewById(R.id.p22);
        buttonC6.setOnClickListener(this);
        buttonC6black = (Button)findViewById(R.id.b16);
        buttonC6black.setOnClickListener(this);

        buttonD6 = (Button)findViewById(R.id.p23);
        buttonD6.setOnClickListener(this);
        buttonD6black = (Button)findViewById(R.id.b17);
        buttonD6black.setOnClickListener(this);

        buttonE6 = (Button)findViewById(R.id.p24);
        buttonE6.setOnClickListener(this);

        buttonF6 = (Button)findViewById(R.id.p25);
        buttonF6.setOnClickListener(this);
        buttonF6black = (Button)findViewById(R.id.b18);
        buttonF6black.setOnClickListener(this);

        buttonG6 = (Button)findViewById(R.id.p26);
        buttonG6.setOnClickListener(this);
        buttonG6black = (Button)findViewById(R.id.b19);
        buttonG6black.setOnClickListener(this);

        buttonA6 = (Button)findViewById(R.id.p27);
        buttonA6.setOnClickListener(this);
        buttonA6black = (Button)findViewById(R.id.b20);
        buttonA6black.setOnClickListener(this);

        buttonB6 = (Button)findViewById(R.id.p28);
        buttonB6.setOnClickListener(this);


//        line for 7
        buttonC7 = (Button)findViewById(R.id.p29);
        buttonC7.setOnClickListener(this);
        buttonC7black = (Button)findViewById(R.id.b21);
        buttonC7black.setOnClickListener(this);

        buttonD7 = (Button)findViewById(R.id.p30);
        buttonD7.setOnClickListener(this);
        buttonD7black = (Button)findViewById(R.id.b22);
        buttonD7black.setOnClickListener(this);

        buttonE7 = (Button)findViewById(R.id.p31);
        buttonE7.setOnClickListener(this);

        buttonF7 = (Button)findViewById(R.id.p32);
        buttonF7.setOnClickListener(this);
        buttonF7black = (Button)findViewById(R.id.b23);
        buttonF7black.setOnClickListener(this);

        buttonG7 = (Button)findViewById(R.id.p33);
        buttonG7.setOnClickListener(this);
        buttonG7black = (Button)findViewById(R.id.b24);
        buttonG7black.setOnClickListener(this);

        buttonA7 = (Button)findViewById(R.id.p34);
        buttonA7.setOnClickListener(this);
        buttonA7black = (Button)findViewById(R.id.b25);
        buttonA7black.setOnClickListener(this);

        buttonB7 = (Button)findViewById(R.id.p35);
        buttonB7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

//            for the 3's
            case R.id.p1:
                soundPool.play(c3,1,1,0,0,1);
                Random random = new Random();
                int color = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));
                buttonC3.setBackgroundColor(color);
                break;
            case R.id.b1:
                soundPool.play(c3black,1,1,0,0,1);
                Random random2 = new Random();
                int color2 = Color.argb(255,random2.nextInt(256),random2.nextInt(256),random2.nextInt(256));
                break;
            case R.id.p2:
                soundPool.play(d3,1,1,0,0,1);
                Random random3 = new Random();
                int color3 = Color.argb(255,random3.nextInt(256),random3.nextInt(256),random3.nextInt(256));
                buttonD3.setBackgroundColor(color3);
                break;
            case R.id.b2:
                Random random4 = new Random();
                int color4 = Color.argb(255,random4.nextInt(256),random4.nextInt(256),random4.nextInt(256));
                soundPool.play(d3black,1,1,0,0,1);
                break;
            case R.id.p3:
                Random random5 = new Random();
                int color5 = Color.argb(255,random5.nextInt(256),random5.nextInt(256),random5.nextInt(256));
                buttonE3.setBackgroundColor(color5);
                soundPool.play(e3,1,1,0,0,1);
                break;
            case R.id.p4:
                Random random6 = new Random();
                int color6 = Color.argb(255,random6.nextInt(256),random6.nextInt(256),random6.nextInt(256));
                buttonF3.setBackgroundColor(color6);
                soundPool.play(f3,1,1,0,0,1);
                break;
            case R.id.b3:
                Random random7 = new Random();
                int color7 = Color.argb(255,random7.nextInt(256),random7.nextInt(256),random7.nextInt(256));
                soundPool.play(f3black,1,1,0,0,1);
                break;
            case R.id.p5:
                Random random8 = new Random();
                int color8 = Color.argb(255,random8.nextInt(256),random8.nextInt(256),random8.nextInt(256));
                buttonG3.setBackgroundColor(color8);
                soundPool.play(g3,1,1,0,0,1);
                break;
            case R.id.b4:
                Random random9 = new Random();
                int color9 = Color.argb(255,random9.nextInt(256),random9.nextInt(256),random9.nextInt(256));
                soundPool.play(g3black,1,1,0,0,1);
                break;
            case R.id.p6:
                Random random10 = new Random();
                int color10 = Color.argb(255,random10.nextInt(256),random10.nextInt(256),random10.nextInt(256));
                buttonA3.setBackgroundColor(color10);
                soundPool.play(a3,1,1,0,0,1);
                break;
            case R.id.b5:
                Random random11 = new Random();
                int color11 = Color.argb(255,random11.nextInt(256),random11.nextInt(256),random11.nextInt(256));
                soundPool.play(a3black,1,1,0,0,1);
                break;
            case R.id.p7:
                Random random12 = new Random();
                int color12 = Color.argb(255,random12.nextInt(256),random12.nextInt(256),random12.nextInt(256));
                buttonB3.setBackgroundColor(color12);
                soundPool.play(b3,1,1,0,0,1);
                break;


//                for the 4's

            case R.id.p8:
                Random random13 = new Random();
                int color13 = Color.argb(255,random13.nextInt(256),random13.nextInt(256),random13.nextInt(256));
                buttonC4.setBackgroundColor(color13);
                soundPool.play(c4,1,1,0,0,1);
                break;
            case R.id.b6:
                Random random14 = new Random();
                int color14 = Color.argb(255,random14.nextInt(256),random14.nextInt(256),random14.nextInt(256));
                soundPool.play(c4black,1,1,0,0,1);
                break;
            case R.id.p9:
                Random random15 = new Random();
                int color15 = Color.argb(255,random15.nextInt(256),random15.nextInt(256),random15.nextInt(256));
                buttonD4.setBackgroundColor(color15);
                soundPool.play(d4,1,1,0,0,1);
                break;
            case R.id.b7:
                Random random16 = new Random();
                int color16 = Color.argb(255,random16.nextInt(256),random16.nextInt(256),random16.nextInt(256));
                soundPool.play(d4black,1,1,0,0,1);
                break;
            case R.id.p10:
                Random random17 = new Random();
                int color17 = Color.argb(255,random17.nextInt(256),random17.nextInt(256),random17.nextInt(256));
                buttonE4.setBackgroundColor(color17);
                soundPool.play(e4,1,1,0,0,1);
                break;
            case R.id.p11:
                Random random18 = new Random();
                int color18 = Color.argb(255,random18.nextInt(256),random18.nextInt(256),random18.nextInt(256));
                buttonF4.setBackgroundColor(color18);
                soundPool.play(f4,1,1,0,0,1);
                break;
            case R.id.b8:
                Random random19 = new Random();
                int color19 = Color.argb(255,random19.nextInt(256),random19.nextInt(256),random19.nextInt(256));
                soundPool.play(f4black,1,1,0,0,1);
                break;
            case R.id.p12:
                Random random20 = new Random();
                int color20 = Color.argb(255,random20.nextInt(256),random20.nextInt(256),random20.nextInt(256));
                buttonG4.setBackgroundColor(color20);
                soundPool.play(g4,1,1,0,0,1);
                break;
            case R.id.b9:
                Random random21 = new Random();
                int color21 = Color.argb(255,random21.nextInt(256),random21.nextInt(256),random21.nextInt(256));
                soundPool.play(g4black,1,1,0,0,1);
                break;
            case R.id.p13:
                Random random22 = new Random();
                int color22 = Color.argb(255,random22.nextInt(256),random22.nextInt(256),random22.nextInt(256));
                buttonA4.setBackgroundColor(color22);
                soundPool.play(a4,1,1,0,0,1);
                break;
            case R.id.b10:
                Random random23 = new Random();
                int color23 = Color.argb(255,random23.nextInt(256),random23.nextInt(256),random23.nextInt(256));
                soundPool.play(a4black,1,1,0,0,1);
                break;
            case R.id.p14:
                Random random24 = new Random();
                int color24 = Color.argb(255,random24.nextInt(256),random24.nextInt(256),random24.nextInt(256));
                buttonB4.setBackgroundColor(color24);
                soundPool.play(b4,1,1,0,0,1);
                break;

//                for the 5's

            case R.id.p15:
                Random random25 = new Random();
                int color25 = Color.argb(255,random25.nextInt(256),random25.nextInt(256),random25.nextInt(256));
                buttonC5.setBackgroundColor(color25);
                soundPool.play(c5,1,1,0,0,1);
                break;
            case R.id.b11:
                Random random26 = new Random();
                int color26 = Color.argb(255,random26.nextInt(256),random26.nextInt(256),random26.nextInt(256));
                soundPool.play(c5black,1,1,0,0,1);
                break;
            case R.id.p16:
                Random random27 = new Random();
                int color27 = Color.argb(255,random27.nextInt(256),random27.nextInt(256),random27.nextInt(256));
                buttonD5.setBackgroundColor(color27);
                soundPool.play(d5,1,1,0,0,1);
                break;
            case R.id.b12:
                Random random28 = new Random();
                int color28 = Color.argb(255,random28.nextInt(256),random28.nextInt(256),random28.nextInt(256));
                soundPool.play(d5black,1,1,0,0,1);
                break;
            case R.id.p17:
                Random random29 = new Random();
                int color29 = Color.argb(255,random29.nextInt(256),random29.nextInt(256),random29.nextInt(256));
                buttonE5.setBackgroundColor(color29);
                soundPool.play(e5,1,1,0,0,1);
                break;
            case R.id.p18:
                Random random30 = new Random();
                int color30 = Color.argb(255,random30.nextInt(256),random30.nextInt(256),random30.nextInt(256));
                buttonF5.setBackgroundColor(color30);
                soundPool.play(f5,1,1,0,0,1);
                break;
            case R.id.b13:
                Random random31 = new Random();
                int color31 = Color.argb(255,random31.nextInt(256),random31.nextInt(256),random31.nextInt(256));
                soundPool.play(f5black,1,1,0,0,1);
                break;
            case R.id.p19:
                Random random32 = new Random();
                int color32 = Color.argb(255,random32.nextInt(256),random32.nextInt(256),random32.nextInt(256));
                buttonG5.setBackgroundColor(color32);
                soundPool.play(g5,1,1,0,0,1);
                break;
            case R.id.b14:
                Random random33 = new Random();
                int color33 = Color.argb(255,random33.nextInt(256),random33.nextInt(256),random33.nextInt(256));
                soundPool.play(g5black,1,1,0,0,1);
                break;
            case R.id.p20:
                Random random34 = new Random();
                int color34 = Color.argb(255,random34.nextInt(256),random34.nextInt(256),random34.nextInt(256));
                buttonA5.setBackgroundColor(color34);
                soundPool.play(a5,1,1,0,0,1);
                break;
            case R.id.b15:
                Random random35 = new Random();
                int color35 = Color.argb(255,random35.nextInt(256),random35.nextInt(256),random35.nextInt(256));
                soundPool.play(a5black,1,1,0,0,1);
                break;
            case R.id.p21:
                Random random36 = new Random();
                int color36 = Color.argb(255,random36.nextInt(256),random36.nextInt(256),random36.nextInt(256));
                buttonB5.setBackgroundColor(color36);
                soundPool.play(b5,1,1,0,0,1);
                break;

//                for 6's

            case R.id.p22:
                Random random37 = new Random();
                int color37 = Color.argb(255,random37.nextInt(256),random37.nextInt(256),random37.nextInt(256));
                buttonC6.setBackgroundColor(color37);
                soundPool.play(c6,1,1,0,0,1);
                break;
            case R.id.b16:
                Random random38 = new Random();
                int color38 = Color.argb(255,random38.nextInt(256),random38.nextInt(256),random38.nextInt(256));
                soundPool.play(c6black,1,1,0,0,1);
                break;
            case R.id.p23:
                Random random39 = new Random();
                int color39 = Color.argb(255,random39.nextInt(256),random39.nextInt(256),random39.nextInt(256));
                buttonD6.setBackgroundColor(color39);
                soundPool.play(d6,1,1,0,0,1);
                break;
            case R.id.b17:
                Random random40 = new Random();
                int color40= Color.argb(255,random40.nextInt(256),random40.nextInt(256),random40.nextInt(256));

                soundPool.play(d6black,1,1,0,0,1);
                break;
            case R.id.p24:
                Random random41 = new Random();
                int color41 = Color.argb(255,random41.nextInt(256),random41.nextInt(256),random41.nextInt(256));
                buttonE6.setBackgroundColor(color41);
                soundPool.play(e6,1,1,0,0,1);
                break;
            case R.id.p25:
                Random random42 = new Random();
                int color42 = Color.argb(255,random42.nextInt(256),random42.nextInt(256),random42.nextInt(256));
                buttonF6.setBackgroundColor(color42);
                soundPool.play(f6,1,1,0,0,1);
                break;
            case R.id.b18:
                Random random43 = new Random();
                int color43 = Color.argb(255,random43.nextInt(256),random43.nextInt(256),random43.nextInt(256));
                soundPool.play(f6black,1,1,0,0,1);
                break;
            case R.id.p26:
                Random random44 = new Random();
                int color44 = Color.argb(255,random44.nextInt(256),random44.nextInt(256),random44.nextInt(256));
                buttonG6.setBackgroundColor(color44);
                soundPool.play(g6,1,1,0,0,1);
                break;
            case R.id.b19:
                Random random45 = new Random();
                int color45 = Color.argb(255,random45.nextInt(256),random45.nextInt(256),random45.nextInt(256));
                soundPool.play(g6black,1,1,0,0,1);
                break;
            case R.id.p27:
                Random random46 = new Random();
                int color46 = Color.argb(255,random46.nextInt(256),random46.nextInt(256),random46.nextInt(256));
                buttonA6.setBackgroundColor(color46);
                soundPool.play(a6,1,1,0,0,1);
                break;
            case R.id.b20:
                Random random47 = new Random();
                int color47 = Color.argb(255,random47.nextInt(256),random47.nextInt(256),random47.nextInt(256));
                soundPool.play(a6black,1,1,0,0,1);
                break;
            case R.id.p28:
                Random random48 = new Random();
                int color48 = Color.argb(255,random48.nextInt(256),random48.nextInt(256),random48.nextInt(256));
                buttonB6.setBackgroundColor(color48);
                soundPool.play(b6,1,1,0,0,1);
                break;

//                for the 7's

            case R.id.p29:
                Random random49 = new Random();
                int color49 = Color.argb(255,random49.nextInt(256),random49.nextInt(256),random49.nextInt(256));
                buttonC7.setBackgroundColor(color49);
                soundPool.play(c7,1,1,0,0,1);
                break;
            case R.id.b21:
                Random random50 = new Random();
                int color50 = Color.argb(255,random50.nextInt(256),random50.nextInt(256),random50.nextInt(256));
                soundPool.play(c7black,1,1,0,0,1);
                break;
            case R.id.p30:
                Random random51 = new Random();
                int color51 = Color.argb(255,random51.nextInt(256),random51.nextInt(256),random51.nextInt(256));
                buttonD7.setBackgroundColor(color51);
                soundPool.play(d7,1,1,0,0,1);
                break;
            case R.id.b22:
                Random random52 = new Random();
                int color52 = Color.argb(255,random52.nextInt(256),random52.nextInt(256),random52.nextInt(256));
                soundPool.play(d7black,1,1,0,0,1);
                break;
            case R.id.p31:
                Random random53 = new Random();
                int color53 = Color.argb(255,random53.nextInt(256),random53.nextInt(256),random53.nextInt(256));
                buttonE7.setBackgroundColor(color53);
                soundPool.play(e7,1,1,0,0,1);
                break;
            case R.id.p32:
                Random random54 = new Random();
                int color54 = Color.argb(255,random54.nextInt(256),random54.nextInt(256),random54.nextInt(256));
                buttonF7.setBackgroundColor(color54);
                soundPool.play(f7,1,1,0,0,1);
                break;
            case R.id.b23:
                Random random55 = new Random();
                int color55 = Color.argb(255,random55.nextInt(256),random55.nextInt(256),random55.nextInt(256));
                soundPool.play(f7black,1,1,0,0,1);
                break;
            case R.id.p33:
                Random random56 = new Random();
                int color56 = Color.argb(255,random56.nextInt(256),random56.nextInt(256),random56.nextInt(256));
                buttonG7.setBackgroundColor(color56);
                soundPool.play(g7,1,1,0,0,1);
                break;
            case R.id.b24:
                Random random57 = new Random();
                int color57 = Color.argb(255,random57.nextInt(256),random57.nextInt(256),random57.nextInt(256));
                soundPool.play(g7black,1,1,0,0,1);
                break;
            case R.id.p34:
                Random random58 = new Random();
                int color58 = Color.argb(255,random58.nextInt(256),random58.nextInt(256),random58.nextInt(256));
                buttonA7.setBackgroundColor(color58);
                soundPool.play(a7,1,1,0,0,1);
                break;
            case R.id.b25:
                Random random59 = new Random();
                int color59 = Color.argb(255,random59.nextInt(256),random59.nextInt(256),random59.nextInt(256));
                soundPool.play(a7black,1,1,0,0,1);
                break;
            case R.id.p35:
                Random random60 = new Random();
                int color60 = Color.argb(255,random60.nextInt(256),random60.nextInt(256),random60.nextInt(256));
                layout.setBackgroundColor(color60);
                soundPool.play(b7,1,1,0,0,1);
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
    private void RequestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
