package com.example.myapplication.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Categories;
import com.example.myapplication.ui.foodstall.FoodFragment;
import com.example.myapplication.ui.foodstall.FoodstallFragment;
import com.example.myapplication.ui.foodstall.foodDetail;
import com.squareup.picasso.Picasso;
import com.example.myapplication.ourData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.myapplication.ourData.promotion;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

    private static List<Categories.Category> categories;
    private FoodstallFragment context;


    @NonNull
    @Override
    public HomeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.promotion_item,parent,false);

        return new MyViewHolder(view);
    }

    public static int promotion_icon;

    @Override
    public void onBindViewHolder(HomeRecyclerViewAdapter.MyViewHolder holder, final int position) {
        holder.categoryThumb.setImageResource(promotion[position]);
        promotion_icon = promotion[position];
    }

    @Override
    public int getItemCount() {
        return promotion.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.promotion_icon)
        ImageView categoryThumb;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(v -> {
                Intent i = new Intent(v.getContext(), foodDetail.class);
                i.putExtra("icon_promotion", promotion_icon);
                v.getContext().startActivity(i);
            });
        }

    }

}
