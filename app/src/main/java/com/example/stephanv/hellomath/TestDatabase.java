package com.example.stephanv.hellomath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

public class TestDatabase extends AppCompatActivity {

    DatabaseOpenHelper DBHelper;

    EditText text;
    TextView confirm, koin, score1, score2, score3;
    Button btnclick, intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        DBHelper = new DatabaseOpenHelper(this);

        /*DBHelper.InsertPlayer("Waduhek", "Unknown", 125);
        DBHelper.InsertPlayer("WidihGilak", "Male", 77);
        DBHelper.InsertPlayer("SayaBudi", "Female", 88);

        DBHelper.GetPlayer();*/

        text = findViewById(R.id.et_cekuser);
        confirm = findViewById(R.id.tv_confirm);
        koin = findViewById(R.id.tv_ko2);
        score1 = findViewById(R.id.tv_cekscore1);
        score2 = findViewById(R.id.tv_cekscore2);
        score3 = findViewById(R.id.tv_cekscore3);
        intent = findViewById(R.id.testIntent);

        btnclick = findViewById(R.id.btn_click);

        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DBHelper.CheckPlayer(text.getText().toString().trim())){
                    confirm.setText("True");
                }
                else{
                    confirm.setText("False");
                }

                int coin = DBHelper.GetCoin(text.getText().toString().trim());
                koin.setText(String.valueOf(coin));

                int PlayerID = DBHelper.PlayerID(text.getText().toString().trim());
                //score1.setText(String.valueOf(PlayerID));
                int scores1 = DBHelper.GetScore(1, PlayerID);
                int scores2 = DBHelper.GetScore(2, PlayerID);
                int scores3 = DBHelper.GetScore(3, PlayerID);

                score1.setText(String.valueOf(scores1));
                score2.setText(String.valueOf(scores2));
                score3.setText(String.valueOf(scores3));

            }
        });


        intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestDatabase.this, Cover.class);
                startActivity(i);
            }
        });



    }
}
