package com.example.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.R;
import com.example.myapplication.ui.ViewModel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.food1, ScaleTypes.FIT)); // for one image
        slideModels.add(new SlideModel(R.drawable.food2, ScaleTypes.FIT)); // for one image
        slideModels.add(new SlideModel(R.drawable.food3, ScaleTypes.FIT)); // for one image
        slideModels.add(new SlideModel(R.drawable.food4, ScaleTypes.FIT)); // for one image
        slideModels.add(new SlideModel(R.drawable.food5, ScaleTypes.FIT)); // for one image
        imageSlider.setImageList(slideModels, ScaleTypes.FIT); // for all images

    }
}

