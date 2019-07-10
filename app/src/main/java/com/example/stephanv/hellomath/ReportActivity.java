package com.example.stephanv.hellomath;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

public class ReportActivity extends AppCompatActivity {

    DatabaseOpenHelper DBHelper;

    RelativeLayout activityReport, boxReport, boxCoin, rlShopping, rlScore;
    ImageView ivNext;

    Dialog popUpDialog;
    TextView tvScore, tvTrue, tvFalse, tvCoin;
    //    Button btnClose;
    Button btnNext, btnShopping;
    ImageView ivCoin;
    ImageView ivClose;

    int playerID, theme;
    int coin;
    int newCoin = 0;
    int score = 0;
    int gameId = 0;
    String userName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DBHelper = new DatabaseOpenHelper(this);
        popUpDialog = new Dialog(this);

        activityReport = findViewById(R.id.activityReport);
        boxReport = findViewById(R.id.boxReport);
        boxCoin = findViewById(R.id.boxCoin);
        rlShopping = findViewById(R.id.rlShopping);
        rlScore = findViewById(R.id.rlScore);
        ivNext = findViewById(R.id.ivNext);

        btnNext = findViewById(R.id.btnNext);

        tvScore = findViewById(R.id.tvScore);
        tvTrue = findViewById(R.id.tvTrue);
        tvFalse = findViewById(R.id.tvFalse);
        tvCoin = findViewById(R.id.tvCoin);



        Intent intent = getIntent();

        score = intent.getExtras().getInt("EXTRA_SCORE");
        int countTrue = intent.getExtras().getInt("EXTRA_COUNT_TRUE");
        int countFalse = intent.getExtras().getInt("EXTRA_COUNT_FALSE");
        gameId = intent.getExtras().getInt("EXTRA_GAME");
        userName = intent.getExtras().getString("UName");

        Log.e("Score", String.valueOf(score));
        //Toast.makeText(this, "Score = " + score + " ", Toast.LENGTH_SHORT).show();
        changeBG();

        String scoreValue = "";
        String countTrueValue = "";
        String countFalseValue = "";

        scoreValue = scoreValue + score;
        countTrueValue = countTrueValue + countTrue;
        countFalseValue = countFalseValue + countFalse;
        coin = DBHelper.GetCoin(userName);

        if(coin == -1){
            coin = 0;
        }

        tvScore.setText(scoreValue);
        tvTrue.setText("Benar : " + countTrueValue);
        tvFalse.setText("Salah : " + countFalseValue);

        ShowPopUp();
//        UpdateDBScore();
//        UpdateCoin();

        tvCoin.setText(String.valueOf(coin));

        btnShopping = findViewById(R.id.btnShopping);
        btnShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ReportActivity.this, ShopActivity.class);
                i.putExtra("PlayerID", DBHelper.PlayerID(userName));
                i.putExtra("Username", userName);
                startActivity(i);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void changeBG(){
        playerID = DBHelper.PlayerID(userName);
        theme = DBHelper.GetTheme(playerID);

        if(theme == 2){
            activityReport.setBackgroundResource(R.drawable.background_cover1);
            boxReport.setBackgroundResource(R.drawable.rectangle_shape2);
            boxCoin.setBackgroundResource(R.drawable.rectangle_timer2);
            rlShopping.setBackgroundResource(R.drawable.rectangle_shape_shopping2);
            rlScore.setBackgroundResource(R.drawable.circle_shape_score_report2);
            ivNext.setImageResource(R.drawable.char_6_1);
            btnNext.setBackgroundResource(R.drawable.rectangle_timer2);
        }
    }

    protected void UpdateDBScore(){
        int playerID = DBHelper.PlayerID(userName);
        //DBHelper.CheckScore(gameId, playerID, score);
        int ScoreDB = DBHelper.GetScore(gameId, playerID);
        if(score > ScoreDB){
            DBHelper.InsertScore(gameId, playerID, score);
        }
    }

    protected void UpdateCoin(){
        DBHelper.InsertCoin(userName, newCoin);
        //Toast.makeText(this, "newCoin = " + newCoin + " ", Toast.LENGTH_SHORT).show();

        int coinrn = DBHelper.GetCoin(userName);
        //Toast.makeText(this, "Coin Right Now =" + coinrn + " ", Toast.LENGTH_SHORT).show();
    }

    public void ShowPopUp(){

//        Intent intent = new Intent(getApplicationContext(), PopReportActivity.class);
//        intent.putExtra("EXTRA_SCORE", score);
//        startActivity(intent);

        popUpDialog.setContentView(R.layout.popup_report);
//        popUpDialog.setContentView(R.layout.report_popup);
//        btnClose = popUpDialog.findViewById(R.id.btnClose);
        ivClose = popUpDialog.findViewById(R.id.ivClose);
        ivCoin = popUpDialog.findViewById(R.id.ivCoin);

        if(score < 20){
            ivCoin.setImageResource(R.drawable.image_0);
            newCoin = 0;
            UpdateDBScore();
            UpdateCoin();
        }
        else if(score < 40){
            ivCoin.setImageResource(R.drawable.image_2);
            newCoin = 2;
            UpdateDBScore();
            UpdateCoin();
        }
        else if(score < 60){
            ivCoin.setImageResource(R.drawable.image_4);
            newCoin = 4;
            UpdateDBScore();
            UpdateCoin();
        }
        else if(score < 80){
            ivCoin.setImageResource(R.drawable.image_6);
            newCoin = 6;
            UpdateDBScore();
            UpdateCoin();
        }
        else if(score < 100){
            ivCoin.setImageResource(R.drawable.image_8);
            newCoin = 8;
            UpdateDBScore();
            UpdateCoin();
        }
        else if(score >= 100){
            ivCoin.setImageResource(R.drawable.image_10);
            newCoin = 10;
            UpdateDBScore();
            UpdateCoin();
        }



        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog.dismiss();
            }
        });

//        btnClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popUpDialog.dismiss();
//            }
//        });

        popUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popUpDialog.show();

//        UpdateDBScore();
//        UpdateCoin();
    }
}
