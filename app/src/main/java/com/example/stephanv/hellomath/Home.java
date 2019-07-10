package com.example.stephanv.hellomath;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

public class Home extends AppCompatActivity {

    DatabaseOpenHelper DBHelper;

    public String PlayerID;
    public String UserName;
    public String TempName;

    ImageButton BackImage, Shop;
    ImageButton Number, PlusMinus, Multiply;
    ImageView HighscoreList, CoinBG, Karak1, Karak2, Karak3;
    ImageView PNum, MNum, MultiNums, DivNum;
    TextView UNameHome, Coin, CloudBG, TextNumber;
    RelativeLayout ActivityBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DBHelper = new DatabaseOpenHelper(this);

        AllVar();
        GetIntent();
        SetUname();
        ChangeBG();
        BackButton();
        NumberBtn();
        PlusMinusGames();
        MultiplyGame();
        HighscoreMenu();
        MenuShop();
        SetCoin();

    }

    private void AllVar(){
        Shop = findViewById(R.id.ib_HomeShop);
        Multiply = findViewById(R.id.ib_MultiplyGame);
        PlusMinus = findViewById(R.id.ib_PlusMinGames);
        Number = findViewById(R.id.ib_NumbersGame);
        CloudBG = findViewById(R.id.tv_CloudBG);
        CoinBG = findViewById(R.id.iv_CoinBG);
        ActivityBG = findViewById(R.id.rl_ActivityBG);
        Karak1 = findViewById(R.id.karak1);
        Karak2 = findViewById(R.id.karak2);
        Karak3 = findViewById(R.id.karak3);
        TextNumber = findViewById(R.id.tv_Numbers);
        PNum = findViewById(R.id.iv_PlusNum);
        MNum = findViewById(R.id.iv_MinNum);
        MultiNums = findViewById(R.id.iv_MultiNum);
        DivNum = findViewById(R.id.iv_DiviNum);
    }

    private void ChangeBG(){
        //DBHelper.InsertCoin(UserName, 20);
        int PlayerID = DBHelper.PlayerID(UserName);
        int Theme = DBHelper.GetTheme(PlayerID);
        //Toast.makeText(this, "PlayerID = " + PlayerID + " AND Theme = " + Theme +" ", Toast.LENGTH_SHORT).show();
        //Toast.makeText(Home.this, "Test", Toast.LENGTH_LONG).show();
        //int Theme = 2;

        if(Theme == 2){
            //Toast.makeText(Home.this, "Test", Toast.LENGTH_LONG).show();
            ActivityBG.setBackgroundResource(R.drawable.background_cover1);
            CloudBG.setBackgroundResource(R.drawable.ic_cloud_blue_24dp);
            CoinBG.setBackgroundResource(R.drawable.roundbuttonscore_blue);
            Karak1.setBackgroundResource(R.drawable.char_1_1);
            Karak2.setBackgroundResource(R.drawable.char_2_1);
            Karak3.setBackgroundResource(R.drawable.char_5_1);
            Number.setBackgroundResource(R.drawable.roundbutton_blue);
            PlusMinus.setBackgroundResource(R.drawable.roundbutton_blue);
            Shop.setBackgroundResource(R.drawable.rectangle_shape_shopping2);
            Multiply.setBackgroundResource(R.drawable.roundbutton_blue);
            TextNumber.setTextColor(Color.parseColor("#003398"));
            PNum.setBackgroundResource(R.drawable.ic_plus_blue_24dp);
            MNum.setBackgroundResource(R.drawable.icon_minus_blue);
            MultiNums.setBackgroundResource(R.drawable.icon_multiply_blue);
            DivNum.setBackgroundResource(R.drawable.icon_divide_blue);
        }

    }

    private void GetIntent(){
        Intent i = getIntent();
        Bundle bund = i.getExtras();

        PlayerID = bund.getString("PlayerID");
        UserName = bund.getString("UName");
        TempName = UserName.toString();
    }

    private void SetUname(){
        UNameHome = findViewById(R.id.tv_UserName);

        UNameHome.setText(TempName);
    }

    private void SetCoin(){
        Coin = findViewById(R.id.tv_coin);
        int co = DBHelper.GetCoin(UserName);
        Coin.setText(String.valueOf(co));
        Coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home.this, "Coin Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void NumberBtn(){
        Number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Number.setAlpha(0.2f);
                Number.animate().alpha(1).setDuration(120);

                Intent i = new Intent(Home.this, Game1Activity.class);
                i.putExtra("UName", UserName);
                startActivity(i);
            }
        });
    }

    private void PlusMinusGames(){
        PlusMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlusMinus.setAlpha(0.2f);
                PlusMinus.animate().alpha(1).setDuration(120);

                Intent i = new Intent(Home.this, Game2Activity.class);
                i.putExtra("UName", UserName);
                startActivity(i);
            }
        });
    }

    private void MultiplyGame(){
        Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Multiply.setAlpha(0.2f);
                Multiply.animate().alpha(1).setDuration(120);

                Intent i = new Intent(Home.this, Game3Activity.class);
                i.putExtra("UName", UserName);
                startActivity(i);
            }
        });
    }

    private void HighscoreMenu(){
        HighscoreList = findViewById(R.id.iv_HighscoreList);
        HighscoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home.this, "HighScoreMenu Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, ScoreActivity.class);
                i.putExtra("Username", UserName);
                startActivity(i);
            }
        });
    }

    private void MenuShop(){
        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Home.this, "MenuShop Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this, ShopActivity.class);
                i.putExtra("PlayerID", DBHelper.PlayerID(UserName));
                i.putExtra("Username", UserName);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        finish();
        startActivity(getIntent());

    }

    //Back ImageButton
    boolean doublePressed = false;
    private void BackButton(){
        BackImage = findViewById(R.id.btn_BackRegist);

        BackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
//                if(doublePressed) finish();
//                else doublePressed = true;
//                Toast.makeText(Home.this, "Please Click again to back", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
