package com.example.myapplication.ui.foodstall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FoodstallViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FoodstallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
