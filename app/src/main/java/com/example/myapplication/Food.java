package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Food extends AppCompatActivity {
    Button button14;
    Button button15;
    Button button12;
    Button button13;
    Button button10;
    Button button11;
    Button button6;
    Button button9;
    Button button16;
    Button button17;
    Button button18;
    Button button19;
    TextView txtKetQua;
    TextView txtKetQua1;
    TextView txtKetQua2;
    TextView txtKetQua3;
    TextView txtKetQua4;
    TextView txtKetQua5;

    int tong = 0;
    int tong1 = 0;
    int tong2 = 0;
    int tong3 = 0;
    int tong4 = 0;
    int tong5 = 0;
    int tong_hang[] = new int[6];
    public static final String KEY1 = "Tong mon da dat";
    public static final String KEY2 = "";
    public static final String KEY3 = "";
    public static final String KEY4 = "";
    public static final String KEY5 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        button14 = (Button) findViewById((R.id.button14));
        button15 = (Button) findViewById((R.id.button15));
        button12 = (Button) findViewById((R.id.button12));
        button13 = (Button) findViewById((R.id.button13));
        button10 = (Button) findViewById((R.id.button10));
        button11 = (Button) findViewById((R.id.button11));
        button6 = (Button) findViewById((R.id.button6));
        button9 = (Button) findViewById((R.id.button9));
        button16 = (Button) findViewById((R.id.button16));
        button17 = (Button) findViewById((R.id.button17));
        button18 = (Button) findViewById((R.id.button18));
        button19 = (Button) findViewById((R.id.button19));
        txtKetQua = (TextView) findViewById((R.id.textView));
        txtKetQua1 = (TextView) findViewById((R.id.textView5));
        txtKetQua2 = (TextView) findViewById((R.id.textView4));
        txtKetQua3 = (TextView) findViewById((R.id.textView3));
        txtKetQua4 = (TextView) findViewById((R.id.textView2));
        txtKetQua5 = (TextView) findViewById((R.id.textView1));

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong1++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua1.setText(String.valueOf(tong1));
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong1 >= 1){
                    tong--;
                    tong1--;
                }
                else {
                    tong1 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua1.setText(String.valueOf(tong1));
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong2++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua2.setText(String.valueOf(tong2));
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong2 >= 1){
                    tong--;
                    tong2--;
                }
                else {
                    tong2 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua2.setText(String.valueOf(tong2));
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong3++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua3.setText(String.valueOf(tong3));
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong3 >= 1){
                    tong--;
                    tong3--;
                }
                else {
                    tong3 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua3.setText(String.valueOf(tong3));
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong4++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua4.setText(String.valueOf(tong4));
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong4 >= 1){
                    tong--;
                    tong4--;
                }
                else {
                    tong4 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua4.setText(String.valueOf(tong4));
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tong++;
                tong5++;
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua5.setText(String.valueOf(tong5));
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tong5 >= 1){
                    tong--;
                    tong5--;
                }
                else {
                    tong5 = 0;
                }
                txtKetQua.setText(String.valueOf(tong));
                txtKetQua5.setText(String.valueOf(tong5));
            }
        });
        tong_hang[0]=tong;
        tong_hang[1]=tong1;
        tong_hang[2]=tong2;
        tong_hang[3]=tong3;
        tong_hang[4]=tong4;
        tong_hang[5]=tong5;
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this, Foodstall.class);
                startActivity(intent);
            }
        });

        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Food.this, Authenticate.class);
                intent.putExtra("Key1", tong1);
                intent.putExtra("Key2", tong2);
                intent.putExtra("Key3", tong3);
                intent.putExtra("Key4", tong4);
                intent.putExtra("Key5", tong5);
                startActivity(intent);
            }
        });

    }
    public void byExtra(int[] tong){
        Intent intent = new Intent(this, Authenticate.class);
        intent.putExtra(KEY1,tong);
        startActivity(intent);
    }
}