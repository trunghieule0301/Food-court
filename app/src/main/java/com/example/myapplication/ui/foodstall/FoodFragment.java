package com.example.myapplication.ui.foodstall;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ui.foodstall.FoodstallFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.myapplication.ui.foodstall.FoodstallFragment.EXTRA_DETAIL;

public class FoodFragment extends Fragment implements FoodView {

    @BindView(R.id.recyclerview_id_in_food_frag)
    RecyclerView recyclerView;

    @BindView(R.id.NameOfFoodStall)
    TextView NameOfFoodStall;

    public static String Name_of_foodstall;

    FoodPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            presenter = new FoodPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
            Name_of_foodstall = getArguments().getString("EXTRA_DATA_NAME");
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

        NameOfFoodStall.setText(Name_of_foodstall);

        adapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.name_of_food);
            Intent intent = new Intent(getActivity(), foodDetail.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);
        });
    }

}