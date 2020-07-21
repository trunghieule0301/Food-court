package com.example.myapplication.ui.trackOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.example.myapplication.ourData;
import com.example.myapplication.ui.adapter.FoodDetailRecyclerViewAdapter;
import com.example.myapplication.ui.adapter.RecyclerViewAdapter;
import com.example.myapplication.ui.adapter.TrackOrderRecyclerViewAdapter;
import com.example.myapplication.ui.foodstall.FoodDetailFragment;
import com.example.myapplication.ui.foodstall.FoodPresenter;
import com.example.myapplication.ui.foodstall.FoodView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackOrderFragment extends Fragment {

    @BindView(R.id.recyclerview_id_in_track_frag)
    RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_track_order, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TrackOrderRecyclerViewAdapter adapter =
                new TrackOrderRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}