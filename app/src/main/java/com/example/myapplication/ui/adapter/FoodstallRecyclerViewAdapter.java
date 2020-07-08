package com.example.myapplication.ui.adapter;

import android.content.Intent;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ourData;
import com.example.myapplication.ui.foodstall.FoodFragment;
import com.example.myapplication.ui.foodstall.foodDetail;

public class FoodstallRecyclerViewAdapter extends RecyclerView.Adapter<FoodstallRecyclerViewAdapter.MyViewHolder> {

    Fragment myFragment;
    FragmentActivity c;

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        view = mInflater.inflate(R.layout.foodstall_item,parent,false);
        final FoodstallRecyclerViewAdapter.MyViewHolder viewHolder = new FoodstallRecyclerViewAdapter.MyViewHolder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodstallRecyclerViewAdapter.MyViewHolder holder, final int position) {

        ((MyViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return ourData.foodstallName.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView itemFoodstall;
        TextView textView;
        ImageView img_food_thumbnail;
        FragmentActivity c;

        public MyViewHolder(final View itemView) {
            super(itemView);
            itemFoodstall = (CardView) itemView.findViewById(R.id.cardview_id);
            textView = (TextView) itemView.findViewById(R.id.foodstall_title_id);
            img_food_thumbnail = (ImageView) itemView.findViewById(R.id.foodstall_img_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentActivity activity = (FragmentActivity) view.getContext();
                    Fragment myFragment = new FoodFragment();
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameFoodstall, myFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });
        }

        public void bindView(int position){
            img_food_thumbnail.setImageResource(ourData.Foodstall[position]);
            textView.setText(ourData.foodstallName[position]);
        }

        public void onClick(View view) {
        }
    }
}
