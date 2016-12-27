package com.example.clicker20;


import java.io.IOException;
import java.util.TimerTask;
import java.util.Timer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.IntegerRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Timer time;
    TimerTask mTimerTask;
    Timer questionT;
    TimerTask questionTask;

    int wallpaper;

    Timer questionReload;
    TimerTask questionReloadTask;

    Clicker player = new Clicker();

    TextView scoreView;
    TextView coeffView;
    TextView record;
    TextView questionOfGame;

    LinearLayout mainLayout;

    Button startOfG, clickButton, howToPlay, settingButton;

    Resources resources;

    Button tb1, tb2, tb3, tb4;

    private SoundPool mySoundPool;
    private AssetManager mAssetManager;
    private int failSound, rightSound;
    private int StreamID;


    Intent intentOfInstr;

    final String APP_PREFERENCES = "mysetting";
    final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mySoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        mAssetManager = getAssets();

        failSound = loadSound("fail.mp3");
        rightSound = loadSound("right.mp3");

        mSetting = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        howToPlay = (Button) findViewById(R.id.howToPlay);
        startOfG = (Button) findViewById(R.id.start);
        clickButton = (Button) findViewById(R.id.clickButton);
        settingButton = (Button) findViewById(R.id.settingButton);

        tb1 = (Button) findViewById(R.id.taskButt1);
        tb2 = (Button) findViewById(R.id.taskButt2);
        tb3 = (Button) findViewById(R.id.taskButt3);
        tb4 = (Button) findViewById(R.id.taskButt4);

        coeffView = (TextView) findViewById(R.id.coeffView);
        scoreView = (TextView) findViewById(R.id.textView10);
        record = (TextView) findViewById(R.id.stringOfRecord);
        questionOfGame = (TextView) findViewById(R.id.questionOfGame);

        mainLayout = (LinearLayout) findViewById(R.id.content_main);

        resources = getResources();


        clickButton.setEnabled(false);


        tb1.setVisibility(View.INVISIBLE);
        tb2.setVisibility(View.INVISIBLE);
        tb3.setVisibility(View.INVISIBLE);
        tb4.setVisibility(View.INVISIBLE);



        startOfG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.setEnabled(true);

                player.nullExeptionOfArray();

                time = new Timer();
                mTimerTask = new startOfGame();
                time.schedule(mTimerTask, 59000);

                settingButton.setEnabled(false);
                startOfG.setEnabled(false);
                howToPlay.setEnabled(false);

                questionT = new Timer();
                questionTask = new questionTask();
                questionT.schedule(questionTask, 10000, 10000);


            }
        });


        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.click();
                scoreView.setText(String.valueOf(player.score));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSetting.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, player.record);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSetting.contains(APP_PREFERENCES_COUNTER)){
            player.record = mSetting.getInt(APP_PREFERENCES_COUNTER, 0);
            record.setText("Рекорд:" + Integer.toString(player.record));}
             wallpaper = getIntent().getIntExtra("WALL",0);


        int white = resources.getColor(R.color.white);
            switch (wallpaper){
                 case 1:
                     mainLayout.setBackgroundResource(R.drawable.fonfire);
                     clickButton.setBackgroundResource(R.drawable.buttonswapogonsetinterface);
                     tb1.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     tb2.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     tb3.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     tb4.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     howToPlay.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     settingButton.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     startOfG.setBackgroundResource(R.drawable.fireanotherswapbtn);
                     tb1.setTextColor(white);tb2.setTextColor(white);tb3.setTextColor(white);tb4.setTextColor(white);
                     howToPlay.setTextColor(white);startOfG.setTextColor(white);settingButton.setTextColor(white);
                     break;
                 case 2:
                     mainLayout.setBackgroundResource(R.drawable.fonwater);
                     clickButton.setBackgroundResource(R.drawable.buttonswapwater);
                     tb1.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     tb2.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     tb3.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     tb4.setBackgroundResource(R.drawable.wateranotherbtnswap);
                    howToPlay.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     settingButton.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     startOfG.setBackgroundResource(R.drawable.wateranotherbtnswap);
                     break;
                case 3:
                    mainLayout.setBackgroundResource(R.drawable.metall_fon_carapiny_poverhnost_18408_1920x1080);
                    clickButton.setBackgroundResource(R.drawable.buttonswap);
                    tb1.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    tb2.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    tb3.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    tb4.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    howToPlay.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    settingButton.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    startOfG.setBackgroundResource(R.drawable.ironbtnswapdefault);
                    break;
                case 4:
                    mainLayout.setBackgroundResource(R.drawable.woodfon);
                    clickButton.setBackgroundResource(R.drawable.woodmainbtnswap);
                    tb1.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    tb2.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    tb3.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    tb4.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    howToPlay.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    settingButton.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    startOfG.setBackgroundResource(R.drawable.wooddiffbtnswap);
                    white = resources.getColor(R.color.white);
                    tb1.setTextColor(white);tb2.setTextColor(white);tb3.setTextColor(white);tb4.setTextColor(white);
                    howToPlay.setTextColor(white);startOfG.setTextColor(white);settingButton.setTextColor(white);
            }

}
    class Clicker  {
        int record = 0;
        int score = 0;
        int colorOfCoeff=0;
        public int coeff = 1;
        int []number = new int [15];
        int numberOfQ;
        public void setColorOfCoeff(){
            switch (coeff){
                case 0:
                    colorOfCoeff = resources.getColor(R.color.red_x0);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 1:
                    colorOfCoeff = resources.getColor(R.color.orange_x1);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 2:
                    colorOfCoeff = resources.getColor(R.color.yellow_x2);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 4:
                    colorOfCoeff = resources.getColor(R.color.litegreen_x4);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 8:
                    colorOfCoeff = resources.getColor(R.color.green_x8);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 16:
                    colorOfCoeff = resources.getColor(R.color.litepupule_x16);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
                case 32:
                    colorOfCoeff = resources.getColor(R.color.fiolet_x32);
                    coeffView.setTextColor(colorOfCoeff);
                    break;
            }
        }

        public void nullExeptionOfArray(){
            for (int i = 0;i<15;i++){
                number[i] = 0;
            }
        }
        public void selectOfQuestions() {
            boolean qwerty = true;
            while (qwerty) {
                int numberOfQues = (int) (Math.random() * 15);
                if (number[numberOfQues] == 0) {
                    qwerty = false;
                    numberOfQ = numberOfQues;
                    number[numberOfQues]++;
                }
            }
        }




        public void click() {
            score = score + coeff;
        }

        public void notRightAnswer(){
                if (coeff>1){
                    coeff = coeff/2;
                }
                else coeff = 0;

        }
        public void rightAnswer(){
            if (coeff>0)
            coeff = coeff*2;
            else
                coeff = 1;
        }



    }


    class startOfGame extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    clickButton.setEnabled(false);
                    howToPlay.setEnabled(true);
                    startOfG.setEnabled(true);
                    settingButton.setEnabled(true);

                    questionTask.cancel();
                    questionT.cancel();
                    questionReloadTask.cancel();
                    questionReload.cancel();

                    if (player.record<player.score){
                     player.record  = player.score;
                     record.setText("Рекорд:"+Integer.toString(player.record));
                    }
                    if(player.score<1000)
                        Toast.makeText(getApplicationContext(), "слабовато...ты набрал "+player.score+" очков", Toast.LENGTH_LONG).show();
                    if(player.score>=1000&&player.score<=4000)
                        Toast.makeText(getApplicationContext(), "неплохо...ты набрал "+player.score+" очков", Toast.LENGTH_LONG).show();
                    if(player.score>4000)
                        Toast.makeText(getApplicationContext(), "да ты профи!! Ты набрал целых "+player.score+" очков", Toast.LENGTH_LONG).show();
                    player.nullExeptionOfArray();

                    int startColor=resources.getColor(R.color.orange_x1);
                    coeffView.setTextColor(startColor);
                    buttonAndQuestionInvise();
                    player.score = 0;
                    scoreView.setText(Integer.toString(player.score));
                    player.coeff = 1;
                    coeffView.setText("x"+player.coeff);

                }});
        }
    }

    class questionTask extends TimerTask{
        @Override
        public void run(){
            runOnUiThread(new Runnable() {
                @Override
                  public void run() {
                    questionReload = new Timer();
                    questionReloadTask = new questionReloadTaskClass();
                    questionReload.schedule(questionReloadTask, 7000);
                    player.selectOfQuestions();

                    String[] ask = resources.getStringArray(R.array.questions);
                    final String[] rightAnswer = resources.getStringArray(R.array.right_answer);
                    String[] firstAnswer = resources.getStringArray(R.array.first_answer);
                    String[] secondAnswer = resources.getStringArray(R.array.second_ansewr);
                    String[] thirdAnswer = resources.getStringArray(R.array.third_answer);

                    tb1.setVisibility(View.VISIBLE);
                    tb2.setVisibility(View.VISIBLE);
                    tb3.setVisibility(View.VISIBLE);
                    tb4.setVisibility(View.VISIBLE);

                    questionOfGame.setText(ask[player.numberOfQ]);
                    int placeOfRightAnswer = (int) (Math.random() * 4 + 1);
                    switch (placeOfRightAnswer) {
                        case 1:
                            tb1.setText(rightAnswer[player.numberOfQ]);
                            tb2.setText(firstAnswer[player.numberOfQ]);
                            tb3.setText(secondAnswer[player.numberOfQ]);
                            tb4.setText(thirdAnswer[player.numberOfQ]);
                            View.OnClickListener onClickListener= new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    switch (v.getId()){
                                        case R.id.taskButt4:
                                        case R.id.taskButt2:
                                        case R.id.taskButt3: {
                                            player.notRightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            playSound(failSound);
                                            player.setColorOfCoeff();
                                            break;
                                        }
                                            case R.id.taskButt1:
                                                playSound(rightSound);
                                                player.rightAnswer();
                                                questionReload.cancel();
                                                coeffView.setText("x"+player.coeff);
                                                buttonAndQuestionInvise();
                                                player.setColorOfCoeff();
                                                break;
                                    }
                                }
                            };
                            tb1.setOnClickListener(onClickListener);
                            tb2.setOnClickListener(onClickListener);
                            tb3.setOnClickListener(onClickListener);
                            tb4.setOnClickListener(onClickListener);

                            break;
                        case 2:
                            tb1.setText(firstAnswer[player.numberOfQ]);
                            tb2.setText(rightAnswer[player.numberOfQ]);
                            tb3.setText(thirdAnswer[player.numberOfQ]);
                            tb4.setText(secondAnswer[player.numberOfQ]);
                            View.OnClickListener onClickListener1= new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    switch (v.getId()){
                                        case R.id.taskButt4:
                                        case R.id.taskButt1:
                                        case R.id.taskButt3: {
                                            playSound(failSound);
                                            player.notRightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                        }
                                        case R.id.taskButt2:
                                            playSound(rightSound);
                                            player.rightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                    }
                                }
                            };
                            tb1.setOnClickListener(onClickListener1);
                            tb2.setOnClickListener(onClickListener1);
                            tb3.setOnClickListener(onClickListener1);
                            tb4.setOnClickListener(onClickListener1);
                            break;
                        case 3:
                            tb1.setText(firstAnswer[player.numberOfQ]);
                            tb2.setText(thirdAnswer[player.numberOfQ]);
                            tb3.setText(rightAnswer[player.numberOfQ]);
                            tb4.setText(secondAnswer[player.numberOfQ]);
                            View.OnClickListener onClickListener2= new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    switch (v.getId()){
                                        case R.id.taskButt4:
                                        case R.id.taskButt2:
                                        case R.id.taskButt1: {
                                            playSound(failSound);
                                            player.notRightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                        }
                                        case R.id.taskButt3:
                                            playSound(rightSound);
                                            player.rightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                    }
                                }
                            };
                            tb1.setOnClickListener(onClickListener2);
                            tb2.setOnClickListener(onClickListener2);
                            tb3.setOnClickListener(onClickListener2);
                            tb4.setOnClickListener(onClickListener2);
                            break;
                        case 4:
                            tb1.setText(thirdAnswer[player.numberOfQ]);
                            tb2.setText(secondAnswer[player.numberOfQ]);
                            tb3.setText(firstAnswer[player.numberOfQ]);
                            tb4.setText(rightAnswer[player.numberOfQ]);
                            View.OnClickListener onClickListener3 = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    switch (v.getId()){
                                        case R.id.taskButt1:
                                        case R.id.taskButt2:
                                        case R.id.taskButt3: {
                                            playSound(failSound);
                                            player.notRightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                        }
                                        case R.id.taskButt4:
                                            playSound(rightSound);
                                            player.rightAnswer();
                                            questionReload.cancel();
                                            coeffView.setText("x"+player.coeff);
                                            buttonAndQuestionInvise();
                                            player.setColorOfCoeff();
                                            break;
                                    }
                                }
                            };
                            tb1.setOnClickListener(onClickListener3);
                            tb2.setOnClickListener(onClickListener3);
                            tb3.setOnClickListener(onClickListener3);
                            tb4.setOnClickListener(onClickListener3);
                            break;
                    }


                }
            });
        }
    }
    class questionReloadTaskClass extends TimerTask{
        @Override
        public void run(){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    questionOfGame.setText(R.string.task_string);
                    buttonAndQuestionInvise();
                    player.notRightAnswer();
                    coeffView.setText("x"+player.coeff);

                }
            });
        }
    }


    public void getInstructions(View view){
        intentOfInstr = new Intent(this,Instructions.class);
        intentOfInstr.putExtra("WALLOFINSTRUC",wallpaper);
        startActivity(intentOfInstr);
    }
    public void goToSetting(View view){
        intentOfInstr = new Intent(this,SettingWallpaper.class);
        intentOfInstr.putExtra("WALLOFSETTING",wallpaper);
        startActivity(intentOfInstr);
    }


    public void buttonAndQuestionInvise(){
        tb1.setVisibility(View.INVISIBLE);
        tb2.setVisibility(View.INVISIBLE);
        tb3.setVisibility(View.INVISIBLE);
        tb4.setVisibility(View.INVISIBLE);
        questionOfGame.setText(R.string.task_string);
    }
    private int playSound(int sound) {
        if (sound > 0) {
            StreamID = mySoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return StreamID;
    }
    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mySoundPool.load(afd, 1);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mySoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }
}