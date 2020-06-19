package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Foodstall extends AppCompatActivity {
    Button btnTest;
    Button btnTest2;
    Button btnTest3;
    Button btnTest4;
    Button btnLogo1;
    Button btnLogo2;
    Button btnLogo3;
    Button btnLogo4;
    Button btnLogo5;
    Button btnLogo6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstall);
        btnTest = (Button) findViewById((R.id.button));
        btnTest2 = (Button) findViewById((R.id.button2));
        btnTest3 = (Button) findViewById((R.id.button3));
        btnTest4 = (Button) findViewById((R.id.button4));
        btnLogo4 = (Button) findViewById((R.id.buttonLogo4));
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnTest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnTest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnTest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}