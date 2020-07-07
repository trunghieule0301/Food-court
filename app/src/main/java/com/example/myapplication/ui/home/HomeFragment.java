package com.example.myapplication.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.R;

import java.lang.String;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Context thiscontext;

    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        thiscontext = container.getContext();

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

