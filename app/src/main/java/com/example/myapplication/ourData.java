package com.example.myapplication;

import com.example.myapplication.ui.foodstall.FoodFragment;

import java.util.ArrayList;

public class ourData {
    public static int[] promotion = new int[]{
            R.drawable.promotion1,
            R.drawable.promotion2,
            R.drawable.promotion3,
            R.drawable.promotion4,
            R.drawable.promotion5,
            R.drawable.promotion6
    };

    public static Integer num = 20;

    public static String[] food = new String[num];

    public static int[] ammount = new int[num];

    public static String[] price = new String[num];

    public static String[] tolPrice = new String[num];

    public static String[] account = new String[2];

    public static ArrayList<Order> orderArrayList= new ArrayList<>();

}
