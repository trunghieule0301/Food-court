package com.example.myapplication.ui.foodstall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ui.adapter.FoodDetailRecyclerViewAdapter;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ourData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailFragment extends Fragment {

    @BindView(R.id.recyclerview_id_in_food_detail_frag)
    RecyclerView recyclerView;

    @BindView(R.id.totalPrice)
    TextView totalPrice;

    public static Integer sum;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FoodDetailRecyclerViewAdapter adapter =
                new FoodDetailRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        sum = Integer.parseInt(ourData.tolPrice[0]);
        totalPrice.setText(String.valueOf(sum));

        adapter.setOnItemClickListener((v, position, check) -> {
            int price = Integer.parseInt(ourData.price[position]);
            if(check == 1){
                sum = sum + price;
            }
            else if(check == 0) {
                sum = sum - price;
            }
            totalPrice.setText(String.valueOf(sum));
        });
    }
}