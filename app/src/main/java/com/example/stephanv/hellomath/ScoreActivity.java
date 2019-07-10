package com.example.stephanv.hellomath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;



public class ScoreActivity extends AppCompatActivity {

    private int HighScore;
    TextView tvScore1, tvViewUser1,tvViewUser2,tvViewUser3,tvScore2, tvScore3;
    int game = 1;
    DatabaseOpenHelper DBHelper;
    int score;
    int gameId = 0;
    String userName;

    Button back;

    ImageView PNum, MNum, MultiNums, DivNum, ivCharHighScore;
    TextView  TextNumber;
    RelativeLayout ActivityBG, ivBox, Number, PlusMinus, Multiply;

    public String PlayerID;
    public int pleyid;
    public String UserName;
    public String TempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DBHelper = new DatabaseOpenHelper(this);

        Intent i = getIntent();
        Bundle bund = i.getExtras();

        UserName = bund.getString("Username");


        ActivityBG = findViewById(R.id.rl_ActivityBG);

        Number = findViewById(R.id.ib_NumbersGame);
        PlusMinus = findViewById(R.id.ib_PlusMinGames);
        Multiply = findViewById(R.id.ib_MultiplyGame);

        ivBox = findViewById(R.id.ivBox);
        ActivityBG = findViewById(R.id.rl_ActivityBG);
        TextNumber = findViewById(R.id.tv_Numbers);
        PNum = findViewById(R.id.iv_PlusNum);
        MNum = findViewById(R.id.iv_MinNum);
        MultiNums = findViewById(R.id.iv_MultiNum);
        DivNum = findViewById(R.id.iv_DiviNum);
        ivCharHighScore = findViewById(R.id.ivCharHighScore);

        tvViewUser1 = findViewById(R.id.tvViewUser1);
        tvViewUser2 = findViewById(R.id.tvViewUser2);
        tvViewUser3 = findViewById(R.id.tvViewUser3);

        tvScore1 = findViewById(R.id.tvScore1);
        tvScore2 = findViewById(R.id.tvScore2);
        tvScore3 = findViewById(R.id.tvScore3);

        back =(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                finish();
            }
        });


        ChangeBG();

        game = 1;

        String name1 = DBHelper.GetHighScoreName1(1);
        String name2 = DBHelper.GetHighScoreName2(1);
        String name3 = DBHelper.GetHighScoreName3(1);
        tvViewUser1.setText(name1);
        tvViewUser2.setText(name2);
        tvViewUser3.setText(name3);

        int score1 = DBHelper.GetHighScore1(1);
        int score2 = DBHelper.GetHighScore2(1);
        int score3 = DBHelper.GetHighScore3(1);
        tvScore1.setText(String.valueOf(score1));
        tvScore2.setText(String.valueOf(score2));
        tvScore3.setText(String.valueOf(score3));

        Number.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                game = 1;

                String name1 = DBHelper.GetHighScoreName1(1);
                String name2 = DBHelper.GetHighScoreName2(1);
                String name3 = DBHelper.GetHighScoreName3(1);
                tvViewUser1.setText(name1);
                tvViewUser2.setText(name2);
                tvViewUser3.setText(name3);

                int score1 = DBHelper.GetHighScore1(1);
                int score2 = DBHelper.GetHighScore2(1);
                int score3 = DBHelper.GetHighScore3(1);
                tvScore1.setText(String.valueOf(score1));
                tvScore2.setText(String.valueOf(score2));
                tvScore3.setText(String.valueOf(score3));

            }
        });

        PlusMinus.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                game = 2;

                String name1 = DBHelper.GetHighScoreName1(2);
                String name2 = DBHelper.GetHighScoreName2(2);
                String name3 = DBHelper.GetHighScoreName3(2);
                tvViewUser1.setText(name1);
                tvViewUser2.setText(name2);
                tvViewUser3.setText(name3);

                int score1 = DBHelper.GetHighScore1(2);
                int score2 = DBHelper.GetHighScore2(2);
                int score3 = DBHelper.GetHighScore3(2);
                tvScore1.setText(String.valueOf(score1));
                tvScore2.setText(String.valueOf(score2));
                tvScore3.setText(String.valueOf(score3));

            }
        });

        Multiply.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                game = 3;

                String name1 = DBHelper.GetHighScoreName1(3);
                String name2 = DBHelper.GetHighScoreName2(3);
                String name3 = DBHelper.GetHighScoreName3(3);
                tvViewUser1.setText(name1);
                tvViewUser2.setText(name2);
                tvViewUser3.setText(name3);

                int score1 = DBHelper.GetHighScore1(3);
                int score2 = DBHelper.GetHighScore2(3);
                int score3 = DBHelper.GetHighScore3(3);
                tvScore1.setText(String.valueOf(score1));
                tvScore2.setText(String.valueOf(score2));
                tvScore3.setText(String.valueOf(score3));

            }
        });




    }
    private void ChangeBG(){
        int playerID = DBHelper.PlayerID(UserName);
        int Theme = DBHelper.GetTheme(playerID);
        Log.e("BG", String.valueOf(Theme));

        if(Theme == 2){
            ActivityBG.setBackgroundResource(R.drawable.background_cover1);
            ivBox.setBackgroundResource(R.drawable.rectangle_shape2);

            Number.setBackgroundResource(R.drawable.roundbutton_blue);
            PlusMinus.setBackgroundResource(R.drawable.roundbutton_blue);
            Multiply.setBackgroundResource(R.drawable.roundbutton_blue);
            TextNumber.setTextColor(Color.parseColor("#003998"));
            PNum.setBackgroundResource(R.drawable.ic_plus_blue_24dp);
            MNum.setBackgroundResource(R.drawable.icon_minus_blue);
            MultiNums.setBackgroundResource(R.drawable.icon_multiply_blue);
            DivNum.setBackgroundResource(R.drawable.icon_divide_blue);
            ivCharHighScore.setImageResource(R.drawable.char_4_1);
        }

    }

}













