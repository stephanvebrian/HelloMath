package com.example.stephanv.hellomath;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Game1Activity extends AppCompatActivity {
    DatabaseOpenHelper DBHelper;
    static final long START_TIME_IN_MILLIS = 90000;

    RelativeLayout activityGame1, boxGame1, boxTimer;
    ImageView ivTimer;
    ImageView questionImage1, questionImage2, questionImage3, questionImage4, questionImage5, questionImage6, questionImage7, questionImage8, questionImage9, questionImage10;
    Button btnChoose1, btnChoose2, btnChoose3;
    TextView tvAnswer, tvTimer;

    CountDownTimer countDownTimer;
    long timeLeftInMillis = START_TIME_IN_MILLIS;
    boolean timerRunning;

    int playerID;
    int theme;
    int gameId = 1;
    String userName = "";
    int nPrev = 0;
    int n;
    String num = "";
    //    int answerQ;
    int countTrue = 0;
    int countFalse = 0;
    int score = 0;
    String answer = "";

    ArrayList<ImageView> listQuestionImage = new ArrayList<>();
    String num1 = "";
    String num2 = "";
    String num3 = "";
    int btn1 = 0;
    int btn2 = 0;
    int btn3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DBHelper = new DatabaseOpenHelper(this);

        activityGame1 = findViewById(R.id.activityGame1);
        boxGame1 = findViewById(R.id.boxGame1);
        boxTimer = findViewById(R.id.boxTimer);
        ivTimer = findViewById(R.id.ivTimer);

        questionImage1 = findViewById(R.id.questionImage1);
        questionImage2 = findViewById(R.id.questionImage2);
        questionImage3 = findViewById(R.id.questionImage3);
        questionImage4 = findViewById(R.id.questionImage4);
        questionImage5 = findViewById(R.id.questionImage5);
        questionImage6 = findViewById(R.id.questionImage6);
        questionImage7 = findViewById(R.id.questionImage7);
        questionImage8 = findViewById(R.id.questionImage8);
        questionImage9 = findViewById(R.id.questionImage9);
        questionImage10 = findViewById(R.id.questionImage10);
//        questionImage1.setVisibility(View.VISIBLE);

        listQuestionImage.add(questionImage1);
        listQuestionImage.add(questionImage2);
        listQuestionImage.add(questionImage3);
        listQuestionImage.add(questionImage4);
        listQuestionImage.add(questionImage5);
        listQuestionImage.add(questionImage6);
        listQuestionImage.add(questionImage7);
        listQuestionImage.add(questionImage8);
        listQuestionImage.add(questionImage9);
        listQuestionImage.add(questionImage10);
//
        btnChoose1 = findViewById(R.id.btnChoose1);
        btnChoose2 = findViewById(R.id.btnChoose2);
        btnChoose3 = findViewById(R.id.btnChoose3);

        tvAnswer = findViewById(R.id.tvAnswer);
        tvTimer = findViewById(R.id.tvTimer);

//
        Intent intent = getIntent();
//        userName = intent.getStringExtra("UName");
        userName = intent.getExtras().getString("UName");
//        int num = intent.getExtras().getInt("EXTRA_NUM");
//        int num = 6;
//        String n = "";
//        n = n + num;

        changeBG();
        generateQuestion();
        startTimer();

        btnChoose1.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              answer = btnChoose1.getText().toString();
                                              if(answer.equals(num)){
//                    btnChoose1.clearAnimation();
                                                  switch (theme){
                                                      case 1:
                                                          btnChoose1.setBackgroundResource(R.drawable.circle_shape_choose);
                                                          btnChoose1.setTextColor(getResources().getColor(R.color.colorWhite));
                                                          break;
                                                      case 2:
                                                          btnChoose1.setBackgroundResource(R.drawable.circle_shape_choose2);
                                                          btnChoose1.setTextColor(getResources().getColor(R.color.colorWhite));
                                                          break;
                                                  }
                                              }
                                              else{
                                                  btnChoose1.setBackgroundResource(R.drawable.circle_shape_false);
                                                  btnChoose1.setTextColor(getResources().getColor(R.color.colorWhite));
                                                  btnChoose1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
                                              }
                                              checkAnswer();
//                    if(answer == num){
//                        countTrue++;
//                    }
//                    else{
//                        countFalse++;
//                    }
//
////                    score = (countTrue / (countTrue+countFalse)) * 100;
//                    score++;

//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        generateQuestion();
//                    }
//                }, 100);
//                generateQuestion();
                                          }
                                      }
//        for(int i =0; i<2000000000; i++){
//            Toast.makeText(this, "TEst", Toast.LENGTH_SHORT).show();
//        }

        );

        btnChoose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnChoose2.setBackgroundResource(R.drawable.circle_shape_choose);
//                btnChoose2.setTextColor(getResources().getColor(R.color.colorWhite));
                answer = btnChoose2.getText().toString();
                if(answer.equals(num)){
//                    btnChoose2.clearAnimation();
//                    btnChoose2.setBackgroundResource(R.drawable.circle_shape_choose);
//                    btnChoose2.setTextColor(getResources().getColor(R.color.colorWhite));
                    switch (theme){
                        case 1:
                            btnChoose2.setBackgroundResource(R.drawable.circle_shape_choose);
                            btnChoose2.setTextColor(getResources().getColor(R.color.colorWhite));
                            break;
                        case 2:
                            btnChoose2.setBackgroundResource(R.drawable.circle_shape_choose2);
                            btnChoose2.setTextColor(getResources().getColor(R.color.colorWhite));
                            break;
                    }
                }
                else{
                    btnChoose2.setBackgroundResource(R.drawable.circle_shape_false);
                    btnChoose2.setTextColor(getResources().getColor(R.color.colorWhite));
                    btnChoose2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
                }
                checkAnswer();
            }
        });

        btnChoose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btnChoose3.setBackgroundResource(R.drawable.circle_shape_choose);
//                btnChoose3.setTextColor(getResources().getColor(R.color.colorWhite));
                answer = btnChoose3.getText().toString();
                if(answer.equals(num)){
//                    btnChoose3.clearAnimation();
//                    btnChoose3.setBackgroundResource(R.drawable.circle_shape_choose);
//                    btnChoose3.setTextColor(getResources().getColor(R.color.colorWhite));
                    switch (theme){
                        case 1:
                            btnChoose3.setBackgroundResource(R.drawable.circle_shape_choose);
                            btnChoose3.setTextColor(getResources().getColor(R.color.colorWhite));
                            break;
                        case 2:
                            btnChoose3.setBackgroundResource(R.drawable.circle_shape_choose2);
                            btnChoose3.setTextColor(getResources().getColor(R.color.colorWhite));
                            break;
                    }
                }
                else{
                    btnChoose3.setBackgroundResource(R.drawable.circle_shape_false);
                    btnChoose3.setTextColor(getResources().getColor(R.color.colorWhite));
                    btnChoose3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
                }
                checkAnswer();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
        finish();
    }

    protected void changeBG(){
        playerID = DBHelper.PlayerID(userName);
        theme = DBHelper.GetTheme(playerID);

        if(theme == 2){
            activityGame1.setBackgroundResource(R.drawable.background_cover1);
            boxGame1.setBackgroundResource(R.drawable.rectangle_shape2);
            boxTimer.setBackgroundResource(R.drawable.rectangle_timer2);
            ivTimer.setImageResource(R.drawable.ic_access_time_darkblue_24dp);
            questionImage1.setImageResource(R.drawable.char_1_1);
            questionImage2.setImageResource(R.drawable.char_1_1);
            questionImage3.setImageResource(R.drawable.char_1_1);
            questionImage4.setImageResource(R.drawable.char_1_1);
            questionImage5.setImageResource(R.drawable.char_1_1);
            questionImage6.setImageResource(R.drawable.char_1_1);
            questionImage7.setImageResource(R.drawable.char_1_1);
            questionImage8.setImageResource(R.drawable.char_1_1);
            questionImage9.setImageResource(R.drawable.char_1_1);
            questionImage10.setImageResource(R.drawable.char_1_1);
            btnChoose1.setTextColor(getResources().getColor(R.color.colorBlueCircleBtnChooseText));
            btnChoose2.setTextColor(getResources().getColor(R.color.colorBlueCircleBtnChooseText));
            btnChoose3.setTextColor(getResources().getColor(R.color.colorBlueCircleBtnChooseText));
        }
    }

    protected void generateQuestion(){

        Random rand = new Random();

        if(timeLeftInMillis > 1){
            btnChoose1.clearAnimation();
            btnChoose2.clearAnimation();
            btnChoose3.clearAnimation();

            for (int i = 0; i < 10; i++){
                listQuestionImage.get(i).setVisibility(View.INVISIBLE);
            }

            num = "";
            num1 = "";
            num2 = "";
            num3 = "";

            do {
                n = rand.nextInt(10) + 1;
            }while(n == nPrev);

            num = num + n;

            for (int i = 0; i < n; i++){
                listQuestionImage.get(i).setVisibility(View.VISIBLE);
            }

//
            //buat kasi pilihan jawabannya
            while(btn1 == btn2 || btn2 == btn3 || btn3 == btn1 || (btn1 != n && btn2 != n && btn3 != n)) {
                btn1 = rand.nextInt(10) + 1;
                btn2 = rand.nextInt(10) + 1;
                btn3 = rand.nextInt(10) + 1;
            }

            num1 = num1 + btn1;
            num2 = num2 + btn2;
            num3 = num3 + btn3;

            btnChoose1.setText(num1);
            btnChoose2.setText(num2);
            btnChoose3.setText(num3);

            btnChoose1.setBackgroundResource(R.drawable.circle_shape);
            btnChoose1.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose2.setBackgroundResource(R.drawable.circle_shape);
            btnChoose2.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose3.setBackgroundResource(R.drawable.circle_shape);
            btnChoose3.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));

            nPrev = n;

        }
        else if(timeLeftInMillis == 0){
            finishGame();
        }

    }

    protected void checkAnswer(){
        if(answer.equals(num)){
            countTrue++;
            score++;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            }, 200);
        }
        else{
            countFalse++;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            }, 400);
        }
    }

    protected void finishGame(){
        Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
        intent.putExtra("EXTRA_SCORE", score);
        intent.putExtra("EXTRA_COUNT_TRUE", countTrue);
        intent.putExtra("EXTRA_COUNT_FALSE", countFalse);
        intent.putExtra("EXTRA_GAME", gameId);
        intent.putExtra("EXTRA_USERNAME", userName);
        startActivity(intent);
        finish();
    }

    protected void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                timeLeftInMillis = 0;
                updateTimerText();
                finishGame();
            }
        }.start();

        timerRunning = true;
    }

    protected void updateTimerText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormated = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText(timeLeftFormated);
    }

}