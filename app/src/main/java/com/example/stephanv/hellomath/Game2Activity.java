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

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Game2Activity extends AppCompatActivity {
    DatabaseOpenHelper DBHelper;
    static final long START_TIME_IN_MILLIS = 90000;

    RelativeLayout activityGame2, boxGame2, boxTimer;
    ImageView ivTimer;

    ImageView Num1Image1, Num1Image2, Num1Image3, Num1Image4, Num1Image5, Num1Image6, Num1Image7, Num1Image8, Num1Image9, Num1Image10;
    ImageView Num2Image1, Num2Image2, Num2Image3, Num2Image4, Num2Image5, Num2Image6, Num2Image7, Num2Image8, Num2Image9, Num2Image10;
    Button btnChoose1, btnChoose2, btnChoose3, btnChoose4, btnChoose5, btnChoose6, btnChoose7, btnChoose8, btnChoose9, btnChoose0;
    TextView tvAnswer, tvTimer, tvNum1, tvNum2, tvOp;

    CountDownTimer countDownTimer;
    long timeLeftInMillis = START_TIME_IN_MILLIS;
//    boolean timerRunning;

    int playerID, theme;
    int gameId = 2;
    String userName = "";
    int n1, n2;
    int n1Prev = 0;
    int n2Prev = 0;
    int randOp;
    String op;
    String num1 = "";
    String num2 = "";
    String operator = "";

    int t = 0;
    String total = "";

    int countTrue = 0;
    int countFalse = 0;
    int score = 0;
    String answer = "";

    ArrayList<ImageView> listNum1Image = new ArrayList<>();
    ArrayList<ImageView> listNum2Image = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DBHelper = new DatabaseOpenHelper(this);

        activityGame2 = findViewById(R.id.activityGame2);
        boxGame2 = findViewById(R.id.boxGame2);
        boxTimer = findViewById(R.id.boxTimer);
        ivTimer = findViewById(R.id.ivTimer);

        Num1Image1 = findViewById(R.id.Num1Image1);
        Num1Image2 = findViewById(R.id.Num1Image2);
        Num1Image3 = findViewById(R.id.Num1Image3);
        Num1Image4 = findViewById(R.id.Num1Image4);
        Num1Image5 = findViewById(R.id.Num1Image5);
        Num1Image6 = findViewById(R.id.Num1Image6);
        Num1Image7 = findViewById(R.id.Num1Image7);
        Num1Image8 = findViewById(R.id.Num1Image8);
        Num1Image9 = findViewById(R.id.Num1Image9);
        Num1Image10 = findViewById(R.id.Num1Image10);

        listNum1Image.add(Num1Image1);
        listNum1Image.add(Num1Image2);
        listNum1Image.add(Num1Image3);
        listNum1Image.add(Num1Image4);
        listNum1Image.add(Num1Image5);
        listNum1Image.add(Num1Image6);
        listNum1Image.add(Num1Image7);
        listNum1Image.add(Num1Image8);
        listNum1Image.add(Num1Image9);
        listNum1Image.add(Num1Image10);

        Num2Image1 = findViewById(R.id.Num2Image1);
        Num2Image2 = findViewById(R.id.Num2Image2);
        Num2Image3 = findViewById(R.id.Num2Image3);
        Num2Image4 = findViewById(R.id.Num2Image4);
        Num2Image5 = findViewById(R.id.Num2Image5);
        Num2Image6 = findViewById(R.id.Num2Image6);
        Num2Image7 = findViewById(R.id.Num2Image7);
        Num2Image8 = findViewById(R.id.Num2Image8);
        Num2Image9 = findViewById(R.id.Num2Image9);
        Num2Image10 = findViewById(R.id.Num2Image10);

        listNum2Image.add(Num2Image1);
        listNum2Image.add(Num2Image2);
        listNum2Image.add(Num2Image3);
        listNum2Image.add(Num2Image4);
        listNum2Image.add(Num2Image5);
        listNum2Image.add(Num2Image6);
        listNum2Image.add(Num2Image7);
        listNum2Image.add(Num2Image8);
        listNum2Image.add(Num2Image9);
        listNum2Image.add(Num2Image10);

        btnChoose0 = findViewById(R.id.btnChoose0);
        btnChoose1 = findViewById(R.id.btnChoose1);
        btnChoose2 = findViewById(R.id.btnChoose2);
        btnChoose3 = findViewById(R.id.btnChoose3);
        btnChoose4 = findViewById(R.id.btnChoose4);
        btnChoose5 = findViewById(R.id.btnChoose5);
        btnChoose6 = findViewById(R.id.btnChoose6);
        btnChoose7 = findViewById(R.id.btnChoose7);
        btnChoose8 = findViewById(R.id.btnChoose8);
        btnChoose9 = findViewById(R.id.btnChoose9);

        tvNum1 = findViewById(R.id.tvNum1);
        tvNum2 = findViewById(R.id.tvNum2);
        tvOp = findViewById(R.id.tvOp);
        tvAnswer = findViewById(R.id.tvAnswer);
        tvTimer = findViewById(R.id.tvTimer);

        Intent intent = getIntent();
        userName = intent.getExtras().getString("UName");

        changeBG();
        generateQuestion();
        startTimer();

        btnChoose0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose0.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose1.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose2.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose3.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose4.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose5.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose6.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose7.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose8.getText().toString();
                tvAnswer.setText(answer);
                checking();
            }
        });
        btnChoose9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer = answer + btnChoose9.getText().toString();
                tvAnswer.setText(answer);
                checking();
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
        int tema = DBHelper.GetTheme(playerID);

        if(tema == 2){
            activityGame2.setBackgroundResource(R.drawable.background_cover1);
            boxGame2.setBackgroundResource(R.drawable.rectangle_shape2);
            boxTimer.setBackgroundResource(R.drawable.rectangle_timer2);
            ivTimer.setImageResource(R.drawable.ic_access_time_darkblue_24dp);

            Num1Image1.setImageResource(R.drawable.char_2_1);
            Num1Image2.setImageResource(R.drawable.char_2_1);
            Num1Image3.setImageResource(R.drawable.char_2_1);
            Num1Image4.setImageResource(R.drawable.char_2_1);
            Num1Image5.setImageResource(R.drawable.char_2_1);
            Num1Image6.setImageResource(R.drawable.char_2_1);
            Num1Image7.setImageResource(R.drawable.char_2_1);
            Num1Image8.setImageResource(R.drawable.char_2_1);
            Num1Image9.setImageResource(R.drawable.char_2_1);
            Num1Image10.setImageResource(R.drawable.char_2_1);

            Num2Image1.setImageResource(R.drawable.char_7_1);
            Num2Image2.setImageResource(R.drawable.char_7_1);
            Num2Image3.setImageResource(R.drawable.char_7_1);
            Num2Image4.setImageResource(R.drawable.char_7_1);
            Num2Image5.setImageResource(R.drawable.char_7_1);
            Num2Image6.setImageResource(R.drawable.char_7_1);
            Num2Image7.setImageResource(R.drawable.char_7_1);
            Num2Image8.setImageResource(R.drawable.char_7_1);
            Num2Image9.setImageResource(R.drawable.char_7_1);
            Num2Image10.setImageResource(R.drawable.char_7_1);

            tvNum1.setTextColor(getResources().getColor(R.color.colorBlueGame2Num1));
            tvNum2.setTextColor(getResources().getColor(R.color.colorBlueGame2Num2));

            btnChoose0.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose1.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose2.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose3.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose4.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose5.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose6.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose7.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose8.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
            btnChoose9.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
        }
    }

    protected void generateQuestion(){
        Random rand = new Random();

        if(timeLeftInMillis > 1){
            for (int i = 0; i < 10; i++){
                listNum1Image.get(i).setVisibility(View.INVISIBLE);
            }
            for (int i = 0; i < 10; i++){
                listNum2Image.get(i).setVisibility(View.INVISIBLE);
            }

            num1 = "";
            num2 = "";
            answer = "";
            total = "";
            tvAnswer.clearAnimation();
            tvAnswer.setTextColor(getResources().getColor(R.color.colorWhite));
            tvAnswer.setText(" ? ");

            randOp = (int) rand.nextInt(2);
            if(randOp == 0){
                op = "+";
            }
            else if(randOp == 1){
                op = "-";
            }
//            switch (randOp){
//                case 0:
//                    op = '+';
//                    break;
//                case 1:
//                    op = '-';
//                    break;
//            }


            do {
                if (op.equals("+")) {
                    n1 = rand.nextInt(10) + 1;
                    n2 = rand.nextInt(10) + 1;
                }
                if (op.equals("-")) {
                    n2 = rand.nextInt(10) + 1;
                    do {
                        n1 = rand.nextInt(10) + 1;
                    } while (n2 > n1);
                }
            }while(n1 == n1Prev && n2 == n2Prev);

//            n1 = rand.nextInt(10) + 1;
//            n2 = rand.nextInt(10) + 1;

            num1 = num1 + n1;
            num2 = num2 + n2;
//            operator = operator + op;
            operator = operator + randOp;

            for (int i = 0; i < n1; i++){
                listNum1Image.get(i).setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < n2; i++){
                listNum2Image.get(i).setVisibility(View.VISIBLE);
            }

            switch (randOp){
                case 0:
                    t = n1 + n2;
                    break;
                case 1:
                    t = n1 - n2;
                    break;
            }
//            t = n1 + n2;

            total = total + t;
            tvNum1.setText(num1);
            tvNum2.setText(num2);
            tvOp.setText(op);
//            tvOp.setText(operator);

            btnChoose0.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose0.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose1.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose1.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose2.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose2.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose3.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose3.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose4.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose4.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose5.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose5.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose6.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose6.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose7.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose7.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose8.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose8.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));
            btnChoose9.setBackgroundResource(R.drawable.rectangle_white_button);
            btnChoose9.setTextColor(getResources().getColor(R.color.colorIjoTuaHomeText));

            n1Prev = n1;
            n2Prev = n2;

        }
        else if(timeLeftInMillis == 0){
            finishGame();
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

    protected void checking(){
        if(answer.length() == total.length()){
            checkAnswer();
        }
    }

    protected void checkAnswer(){
        if(answer.equals(total)){
            switch (theme){
                case 1:
                    tvAnswer.setTextColor(getResources().getColor(R.color.colorIjoTerangCoinBorderTimerChoose));
                    break;
                case 2:
                    tvAnswer.setTextColor(getResources().getColor(R.color.colorBlueRecBtnChooseText));
                    break;
            }
//            tvAnswer.setTextColor(getResources().getColor(R.color.colorIjoTerangCoinBorderTimerChoose));
            countTrue++;
            score++;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            },300);
        }
        else {
            tvAnswer.setTextColor(getResources().getColor(R.color.colorRedBackgroundClose));
            tvAnswer.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));
            countFalse++;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            },500);
        }
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
                timeLeftInMillis = 0;
                updateTimerText();
                finishGame();
            }
        }.start();
    }

    protected void updateTimerText(){
        int minutes = (int)(timeLeftInMillis / 1000) / 60;
        int seconds = (int)(timeLeftInMillis / 1000) % 60;

        String timeLeftFormated = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTimer.setText(timeLeftFormated);
    }
}
