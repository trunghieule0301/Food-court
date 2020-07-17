package com.example.myapplication.ui.foodstall;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ourData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodFragment extends Fragment implements FoodView {

    public static String[] dataFood;

    public Integer count = 0;

    public static Integer count1 = 0;

    public int sum = 0;

    @BindView(R.id.numberOfFood)
    TextView numberOfFood;

    @BindView(R.id.recyclerview_id_in_food_frag)
    RecyclerView recyclerView;

    @BindView(R.id.NameOfFoodStall)
    TextView NameOfFoodStall;

    public static String Name_of_foodstall;
    FoodPresenter presenter;

    public static Integer itemCount;

    Button btnPurchase;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food, container, false);
        ButterKnife.bind(this, root);
        btnPurchase = (Button) root.findViewById(R.id.button_purchase);

        btnPurchase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ourData.num = count;
                for(int i = 0; i < count; i++){
                    ourData.data[i] = dataFood[i];
                }
                Toast.makeText(v.getContext(), "test click " + String.valueOf(ourData.num), Toast.LENGTH_SHORT).show();
                FragmentActivity activity = (FragmentActivity) v.getContext();
                Fragment fragment = new FoodDetailFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameFood, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
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

    public static boolean check = false;

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewAdapter adapter =
                new RecyclerViewAdapter(this, meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        NameOfFoodStall.setText(Name_of_foodstall);
        itemCount = adapter.getItemCount();
        int[] checkArray = new int[itemCount];
        dataFood = new String[itemCount];

        adapter.setOnItemClickListener((view, position) -> {
            String foodName = meals.get(position).getStrMeal();
            while(count != itemCount){
                for (int i = 0; i <= count; i++) {
                    if(position == 0){
                        check = false;
                        break;
                    }
                    if (checkArray[i] == position) {
                        check = true;
                    }
                }

                if(check == false) {
                    dataFood[count] = foodName;
                    sum = sum + 1;
                    checkArray[count] = position;
                    count++;
                    count1++;
                }
                check = false;
                break;
            }
            numberOfFood.setText(String.valueOf(sum));
        });
    }
}