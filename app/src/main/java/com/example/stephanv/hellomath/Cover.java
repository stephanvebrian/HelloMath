package com.example.stephanv.hellomath;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

/*
Last Edited : 30 Des '18 - 20:00
Last Modified : Stephan
 */

public class Cover extends AppCompatActivity {

    EditText Username;
    Button BtnSign, BtnClickHere, BtnPlay;

    DatabaseOpenHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DBHelper = new DatabaseOpenHelper(this);

        //DBHelper.InsertCoin("iko", 74);

        BtnSign = findViewById(R.id.btn_ClickHere);
        ButtonAnim(BtnSign);
        BtnClickHere();
        BtnPlay();


    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(Cover.this, PopClose.class);
        startActivity(i);

        /* //Set Normal Dialog
        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.endnow);

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setView(image)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Cover.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
        */
    }

    protected void BtnClickHere(){
        BtnClickHere = findViewById(R.id.btn_ClickHere);

        BtnClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cover.this, SignUp.class);
                startActivity(i);
            }
        });

    }

    private void BtnPlay(){
        Username = findViewById(R.id.et_Username);
        BtnPlay = findViewById(R.id.btn_PlayGame);

        BtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean CekPlayer = DBHelper.CheckPlayer(Username.getText().toString().trim());
                //Toast.makeText(Cover.this, "CekPlayer = " + CekPlayer + " ", Toast.LENGTH_SHORT).show();

                if( CekPlayer == true){
                    //Toast.makeText(Cover.this, "Welcome Back " + Username.getText().toString().trim() + "!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Cover.this, Home.class);
                    int PlayerID = DBHelper.PlayerID(Username.getText().toString().trim());
                    i.putExtra("PlayerID", String.valueOf(PlayerID) );
                    i.putExtra("UName", Username.getText().toString().trim());
                    startActivity(i);
                }
                else{
                    Toast.makeText(Cover.this, "Sorry, Your Username not found. ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    // Fade in-out Animation
    public void ButtonAnim(Button btnSign){
        AlphaAnimation anim = new AlphaAnimation(1.0f, 0.1f);
        anim.setDuration(1000);
        anim.setRepeatCount(Animation.INFINITE);
        btnSign.startAnimation(anim);
    }

}
