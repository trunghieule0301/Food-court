package com.example.myapplication.ui.foodstall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.FoodstallRecyclerViewAdapter;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodstallFragment extends Fragment implements FoodstallView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    private List<Categories.Category> categories;

    @BindView(R.id.recyclerview_id_in_foodstall_frag)
    RecyclerView recyclerViewCategory;

    FoodstallPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

        });
    }

}
