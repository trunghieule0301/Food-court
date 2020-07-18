package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ui.home.HomeFragment;

public class PaySuccessActivity extends AppCompatActivity {
    Button buttonTurnBack, buttonTrackOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        buttonTurnBack = (Button) findViewById(R.id.btnTurnHome);
        buttonTrackOrder = (Button) findViewById(R.id.btnTrackOrder);

        buttonTurnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaySuccessActivity.this, "Feature is in progress", Toast.LENGTH_SHORT).show();
            }
        });
    }
}