package com.example.myapplication.ui.foodstall;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class FoodstallFragment extends Fragment {

    private FoodstallViewModel foodstallViewModel;
    private FoodstallViewModel foodstall1ViewModel;

    ImageButton btnStall1;
    Fragment myFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        foodstallViewModel =
                ViewModelProviders.of(this).get(FoodstallViewModel.class);
        View root = inflater.inflate(R.layout.fragment_foodstall, container, false);
        final TextView textView = root.findViewById(R.id.text_foodstall);
        foodstallViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodstall1ViewModel = ViewModelProviders.of(this).get(FoodstallViewModel.class);
        // TODO: Use the ViewModel
        btnStall1 = (ImageButton) view.findViewById(R.id.stall1);
        btnStall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFragment = new FoodFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameFoodstall, myFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
