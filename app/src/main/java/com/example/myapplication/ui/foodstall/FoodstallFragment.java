package com.example.myapplication.ui.foodstall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.ViewModel.FoodstallViewModel;
import com.example.myapplication.ui.adapter.FoodstallRecyclerViewAdapter;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;

import java.io.Serializable;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodstallFragment extends Fragment implements FoodstallView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    private List<Categories.Category> categories;

    private FoodstallViewModel foodstallViewModel;
    @BindView(R.id.recyclerview_id_in_foodstall_frag) RecyclerView recyclerViewCategory;

    FoodstallPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        foodstallViewModel =
                ViewModelProviders.of(this).get(FoodstallViewModel.class);
        View root = inflater.inflate(R.layout.fragment_foodstall, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FoodstallPresenter(this);
        presenter.getMeals();
        presenter.getCategories();

    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {

    }

    @Override
    public void setCategory(final List<Categories.Category> category) {
        FoodstallRecyclerViewAdapter homeAdapter = new FoodstallRecyclerViewAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {

            FragmentActivity activity = (FragmentActivity) view.getContext();
            Fragment myFragment = new FoodFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EXTRA_CATEGORY, (Serializable) category);
            bundle.putInt(EXTRA_POSITION, position);
            myFragment.setArguments(bundle);
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameFoodstall, myFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }

}
