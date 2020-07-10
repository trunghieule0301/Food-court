package com.example.myapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Categories;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ourData;
import com.example.myapplication.ui.foodstall.FoodFragment;
import com.example.myapplication.ui.foodstall.foodDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Fragment myFragment;

    private List<Categories.Category> categories;

    private List<Meals.Meal> meals;
    private FoodFragment context;
    private static ClickListener clickListener;

    public RecyclerViewAdapter(FoodFragment context, List<Meals.Meal> meals) {
        this.meals = meals;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.food_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        String strMealThumb = meals.get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(holder.mealThumb);

        String strMealName = meals.get(position).getStrMeal();
        holder.mealName.setText(strMealName);

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.food_icon)
        ImageView mealThumb;
        @BindView(R.id.name_of_food)
        TextView mealName;

        private CardView itemFood;
        TextView textView;
        TextView textView2;
        ImageView img_food_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

            itemFood = (CardView) itemView.findViewById(R.id.cardview_id_1);
            textView = (TextView) itemView.findViewById(R.id.name_of_food);
            textView2 = (TextView) itemView.findViewById(R.id.description_of_food);
            img_food_thumbnail = (ImageView) itemView.findViewById(R.id.food_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), foodDetail.class);
                    i.putExtra("title", ourData.title1[getAdapterPosition()]);
                    i.putExtra("title1", ourData.title2[getAdapterPosition()]);
                    i.putExtra("icon", ourData.picturePath[getAdapterPosition()]);
                    view.getContext().startActivity(i);
                }
            });
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onClick(View view, int position);
    }


}
