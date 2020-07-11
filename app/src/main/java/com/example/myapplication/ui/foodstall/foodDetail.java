package com.example.myapplication.ui.foodstall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class foodDetail extends AppCompatActivity {

    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        ImageView iconFood = findViewById(R.id.food_icon);
        textView = findViewById(R.id.name_of_food);

        Intent i = getIntent();
        String name = i.getStringExtra("title");
        String description = i.getStringExtra("title1");
        int foodIcon = i.getIntExtra("icon", 0);
        textView.setText(name);
        textView2.setText(description);
        iconFood.setImageResource(foodIcon);
    }
}