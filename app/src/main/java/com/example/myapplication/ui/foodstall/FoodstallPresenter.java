/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.myapplication.ui.foodstall;

import androidx.annotation.NonNull;

import com.example.myapplication.Utils;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class FoodstallPresenter {

    private FoodstallView view;

    public FoodstallPresenter(FoodstallView view) {
        this.view = view;
    }

    void getMeals() {

        Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {

                if (response.isSuccessful() && response.body() != null) {

                    view.setMeal(response.body().getMeals());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {

            }
        });
    }


    void getCategories() {

        Call<Categories> categoriesCall = Utils.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,
                                   @NonNull Response<Categories> response) {


                if (response.isSuccessful() && response.body() != null) {

                    view.setCategory(response.body().getCategories());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {

            }
        });
    }
}
