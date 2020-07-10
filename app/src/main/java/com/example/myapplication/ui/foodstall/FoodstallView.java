/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/17/19 5:24 AM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.example.myapplication.ui.foodstall;

import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;

import java.util.List;

public interface FoodstallView {
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
}
