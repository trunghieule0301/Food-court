/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/24/19 5:13 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.myapplication.ui.foodstall;

import androidx.annotation.NonNull;

import com.example.myapplication.Utils;
import com.example.myapplication.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodPresenter {

    private FoodView view;

    public FoodPresenter(FoodView view) {
        this.view = view;
    }
    
    void getMealByCategory(String category) {

        Call<Meals> mealsCall = Utils.getApi().getMealByCategory(category);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.setMeals(response.body().getMeals());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {

            }
        });
    }
}
