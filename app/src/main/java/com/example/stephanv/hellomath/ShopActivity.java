package com.example.stephanv.hellomath;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;


public class ShopActivity extends AppCompatActivity {

    public int PlayerID;
    public String UserName;
    public String TempName;

    DatabaseOpenHelper DBHelper;
    ImageButton btnShop;
    Button back, btnBuy2, btnThemeUse1, btnThemeUse2;
    TextView tvUsername, tvCoin, tvThemeUsed2, tvThemeUsed1;
    ImageView imageView6, imageView4,ivIjo,ivBiru;
    RelativeLayout boxUsernameBG, boxCoin, rlShopping;

    int playerID, theme;
    int coin = 0;
    int score = 0;
    int gameId = 0;
    String userName = "";

    public String Tema01;
    public String Tema02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DBHelper = new DatabaseOpenHelper(this);

        ivIjo = findViewById(R.id.ivIjo);
        boxUsernameBG = findViewById(R.id.boxUsernameBG);
        boxCoin = findViewById(R.id.boxCoin);
        rlShopping = findViewById(R.id.rlShopping);

        tvUsername = findViewById(R.id.tvUsername);
        tvCoin = findViewById(R.id.tvCoin);

        changeBG();
        GetIntent();
        SetUname();
        final int co = DBHelper.GetCoin(UserName);
        tvCoin.setText(String.valueOf(co));



        btnThemeUse1 = findViewById(R.id.btnThemeUse1);
        btnThemeUse2 = findViewById(R.id.btnThemeUse2);

        Tema01 = DBHelper.GetThemeStatus(1, PlayerID);
        Tema02 = DBHelper.GetThemeStatus(2, PlayerID);

        //Toast.makeText(this, "Tema 01 =" + Tema01 + " AND Tema 02=" + Tema02 + " ", Toast.LENGTH_SHORT).show();

        btnThemeUse1.setText(Tema01);
        btnThemeUse2.setText(Tema02);

        //&& ( Tema02.equals("use") || Tema02.equals("buy") )

        if(Tema01.equals("used") ){
            btnThemeUse1.setClickable(false);
            btnThemeUse2.setClickable(true);
            btnThemeUse1.setAlpha(0.5f);

        }

        if(Tema02.equals("used")){
            btnThemeUse1.setClickable(true);
            btnThemeUse2.setClickable(false);
            btnThemeUse2.setAlpha(0.5f);

        }

        if(Tema01.equals("use")){
            btnThemeUse1.setClickable(true);
            btnThemeUse1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //btnThemeUse1.setAlpha();
                    DBHelper.SetThemeStatus(1, PlayerID);
                    DBHelper.SetThemeStatusToUse(2, PlayerID);
                    ReloadAct();
                }
            });
        }

        if(Tema02.equals("use")){
            btnThemeUse2.setClickable(true);

            btnThemeUse2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper.SetThemeStatus(2, PlayerID);
                    DBHelper.SetThemeStatusToUse(1, PlayerID);
                    ReloadAct();
                }
            });
        }

        if(Tema02.equals("buy")){
            btnThemeUse2.setClickable(true);

            btnThemeUse2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(co > 49){
                        PlayerID = DBHelper.PlayerID(UserName);
                        DBHelper.BuyTheme(PlayerID);

                        ReloadAct();
                    }else{
                        Toast.makeText(ShopActivity.this, "Not Enough Coin.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

//        btnThemeUse1.setText(Tema01);
//        btnThemeUse2.setText(Tema02);

//        btnThemeUse1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBHelper.SetThemeStatus(1, PlayerID);
//                btnThemeUse1.setClickable(false);
//                btnThemeUse1.setAlpha(0.1f);
//                btnThemeUse2.setClickable(true);
//
//                Tema01 = DBHelper.GetThemeStatus(1, PlayerID);
//                Tema02 = DBHelper.GetThemeStatus(2, PlayerID);
//                btnThemeUse1.setText(Tema01);
//                btnThemeUse2.setText(Tema02);
//                ReloadAct();
//            }
//        });
//
//
//        btnThemeUse2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlayerID = DBHelper.PlayerID(UserName);
//                btnThemeUse1.setClickable(true);
//                btnThemeUse2.setClickable(false);
//                boolean cek = DBHelper.CheckTema2(PlayerID);
//                Toast.makeText(ShopActivity.this, "Cek BOOL = " + cek + "", Toast.LENGTH_SHORT).show();
//                if(cek == true){
//                    DBHelper.SetThemeStatus(2, PlayerID);
//
//                    Tema01 = DBHelper.GetThemeStatus(1, PlayerID);
//                    Tema02 = DBHelper.GetThemeStatus(2, PlayerID);
//                    btnThemeUse1.setText(Tema01);
//                    btnThemeUse2.setText(Tema02);
//
//                    ReloadAct();
//
//                }else{
//                    if(co > 49){
//                        PlayerID = DBHelper.PlayerID(UserName);
//                        DBHelper.BuyTheme(PlayerID);
//
//                        Tema01 = DBHelper.GetThemeStatus(1, PlayerID);
//                        Tema02 = DBHelper.GetThemeStatus(2, PlayerID);
//                        btnThemeUse1.setText(Tema01);
//                        btnThemeUse2.setText(Tema02);
//
//                        ReloadAct();
//                    }else{
//                        Toast.makeText(ShopActivity.this, "Not Enough Coin.", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//
//            }
//        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    protected void changeBG(){
        int tesid = DBHelper.PlayerID(userName);
        int temanya = DBHelper.GetTheme(tesid);

        if(temanya == 2){
            ivIjo.setBackgroundResource(R.drawable.background_cover1);
            boxUsernameBG.setBackgroundResource(R.drawable.ic_cloud_blue_24dp);
            boxCoin.setBackgroundResource(R.drawable.rectangle_timer2);
            rlShopping.setBackgroundResource(R.drawable.rectangle_shape_shopping2);
            btnThemeUse1.setBackgroundResource(R.color.colorBlueScoreReportStroke);
            btnThemeUse2.setBackgroundResource(R.color.colorBlueScoreReportStroke);
        }
    }

    private void ReloadAct(){
        finish();
        startActivity(getIntent());
    }

    private void GetIntent(){
        Intent i = getIntent();
        Bundle bund = i.getExtras();

        PlayerID = bund.getInt("PlayerID");
        //Toast.makeText(this, "PLAYERID = " + PlayerID + " ", Toast.LENGTH_SHORT).show();
        UserName = bund.getString("Username");
        TempName = UserName;
    }

    private void SetUname(){

        tvUsername.setText(TempName);
    }

}