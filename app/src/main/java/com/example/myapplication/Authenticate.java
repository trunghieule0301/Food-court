package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Authenticate extends AppCompatActivity {
    Button home;
    TextView txtQty1;
    TextView txtQty2;
    TextView txtQty3;
    TextView txtQty4;
    TextView txtQty5;
    TextView txtPrice1;
    TextView txtPrice2;
    TextView txtPrice3;
    TextView txtPrice4;
    TextView txtPrice5;
    TextView txtTotalPrice;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);
        Intent intent = getIntent();
        int[] tong = new int[6];
        tong[1] = intent.getIntExtra("Key1",0);
        tong[2] = intent.getIntExtra("Key2",0);
        tong[3] = intent.getIntExtra("Key3",0);
        tong[4] = intent.getIntExtra("Key4",0);
        tong[5] = intent.getIntExtra("Key5",0);
        txtQty1 = (TextView) findViewById(R.id.txtQty1);
        txtQty2 = (TextView) findViewById(R.id.txtQty2);
        txtQty3 = (TextView) findViewById(R.id.txtQty3);
        txtQty4 = (TextView) findViewById(R.id.txtQty4);
        txtQty5 = (TextView) findViewById(R.id.txtQty5);
        txtPrice1 = (TextView) findViewById(R.id.txtPrice1);
        txtPrice2 = (TextView) findViewById(R.id.txtPrice2);
        txtPrice3 = (TextView) findViewById(R.id.txtPrice3);
        txtPrice4 = (TextView) findViewById(R.id.txtPrice4);
        txtPrice5 = (TextView) findViewById(R.id.txtPrice5);
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        txtQty1.setText(tong[1] + "");
        txtQty2.setText(tong[2] + "");
        txtQty3.setText(tong[3] + "");
        txtQty4.setText(tong[4] + "");
        txtQty5.setText(tong[5] + "");
        int tong1 = tong[1]*150000;
        txtPrice1.setText(String.valueOf(tong1));
        int tong2 = tong[2]*150000;
        txtPrice2.setText(String.valueOf(tong2));
        int tong3 = tong[3]*90000;
        txtPrice3.setText(String.valueOf(tong3));
        int tong4 = tong[4]*175000;
        txtPrice4.setText(String.valueOf(tong4));
        int tong5 = tong[5]*95000;
        txtPrice5.setText(String.valueOf(tong5));
        int Phai_tra;
        Phai_tra = tong1 + tong2 + tong3 + tong4 + tong5;
        txtTotalPrice.setText(String.valueOf(Phai_tra));
        btnBack = (Button) findViewById(R.id.btnBack);
        home = (Button) findViewById(R.id.btnHome4);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Authenticate.this,Food.class);
                startActivity(intent1);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Authenticate.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}