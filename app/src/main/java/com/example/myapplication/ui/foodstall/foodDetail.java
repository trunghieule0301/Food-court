package com.example.myapplication.ui.foodstall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

public class foodDetail extends AppCompatActivity {

    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        ImageView iconFood = findViewById(R.id.food_icon);
        textView = findViewById(R.id.name_of_food_detail);
        textView2 = findViewById(R.id.description_of_food_detail);

        Intent i = getIntent();
        String name = i.getStringExtra("title");
        String description = i.getStringExtra("title1");
        String foodIcon = i.getStringExtra("icon");
        textView.setText(name);
        textView2.setText(description);
        Toast.makeText(getApplicationContext(), "test " + foodIcon, Toast.LENGTH_SHORT).show();
    }
}