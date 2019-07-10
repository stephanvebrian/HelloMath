package com.example.stephanv.hellomath;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephanv.hellomath.ApplicationClass.DatabaseOpenHelper;

/*
Last Edited : 30 Des '18 - 20:00
Last Modified : Stephan
 */

public class SignUp extends AppCompatActivity {

    DatabaseOpenHelper DBHelper;

    EditText Nama;
    public String Gender;
    public String DOB;

    CheckBox Men, Women;
    Spinner SpinDay, SpinMonth, SpinYear;
    Button SubmitBtn;
    ImageButton BackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        DBHelper = new DatabaseOpenHelper(this);

        SpinnerStyle();
        CheckGender();
        BackButton();
        SubmitButton();


    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click again to back", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        },2000);

    }

    boolean doublePressed = false;
    private void BackButton(){
        BackBtn = findViewById(R.id.btn_BackRegist);

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doublePressed) finish();
                else doublePressed = true;
                Toast.makeText(SignUp.this, "Please Click again to back", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CheckGender(){
        Men = findViewById(R.id.cb_Men);
        Women = findViewById(R.id.cb_Women);

        Men.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Gender = "male";
                    Women.setChecked(false);
                }
            }
        });

        Women.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Gender = "female";
                    Men.setChecked(false);
                }
            }
        });
    }

    //Spinner Style
    private void SpinnerStyle(){

        SpinDay = findViewById(R.id.spin_day);
        SpinMonth = findViewById(R.id.spin_month);
        SpinYear = findViewById(R.id.spin_year);

        ArrayAdapter AdaptDay = ArrayAdapter.createFromResource(this, R.array.day_arrays, R.layout.spinner_style);
        SpinDay.setAdapter(AdaptDay);

        ArrayAdapter AdaptMonth = ArrayAdapter.createFromResource(this, R.array.month_arrays, R.layout.spinner_style);
        SpinMonth.setAdapter(AdaptMonth);

        ArrayAdapter AdaptYear = ArrayAdapter.createFromResource(this, R.array.year_arrays, R.layout.spinner_style);
        SpinYear.setAdapter(AdaptYear);

    }

    private void SubmitButton(){
        Nama = (EditText) findViewById(R.id.et_NameRegist);
        DOB = SpinDay.getSelectedItem().toString() + " " + SpinMonth.getSelectedItem().toString() + " " + SpinYear.getSelectedItem().toString();

        SubmitBtn = findViewById(R.id.btn_SubmitRegist);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DBHelper.InsertPlayer(Nama.getText().toString().trim(), Gender, DOB, 0 );


                Toast.makeText(SignUp.this, "Register Completed !", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
