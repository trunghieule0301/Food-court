package com.example.myapplication.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ui.adapter.FoodstallRecyclerViewAdapter;
import com.example.myapplication.ui.adapter.HomeRecyclerViewAdapter;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ui.foodstall.FoodView;
import com.example.myapplication.ui.foodstall.foodDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.myapplication.ui.foodstall.FoodstallFragment.EXTRA_DETAIL;

public class HomeFragment extends Fragment {

    @BindView(R.id.recyclerview_id_in_home_frag)
    RecyclerView recyclerViewCategory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
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


        HomeRecyclerViewAdapter homeAdapter = new HomeRecyclerViewAdapter();
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewCategory.setLayoutManager(layoutManager);
        homeAdapter.notifyDataSetChanged();

    }
}

