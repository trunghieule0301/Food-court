package com.example.myapplication.ui.foodstall;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ui.ViewModel.FoodViewModel;
import com.example.myapplication.ui.adapter.FoodstallRecyclerViewAdapter;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ui.foodstall.FoodFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodFragment extends Fragment implements FoodView {

    private FoodViewModel foodViewModel;

    @BindView(R.id.recyclerview_id_in_food_frag)
    RecyclerView recyclerView;

    FoodPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        foodViewModel =
                ViewModelProviders.of(this).get(FoodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            presenter = new FoodPresenter(this);
            Toast.makeText(view.getContext(), "test click " + getArguments().getString("EXTRA_DATA_NAME"), Toast.LENGTH_SHORT).show();
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {

        RecyclerViewAdapter adapter =
                new RecyclerViewAdapter(this, meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            //TODO #8.2 make an intent to DetailActivity (get the name of the meal from the edit text view, then send the name of the meal to DetailActivity)
        });
    }

}