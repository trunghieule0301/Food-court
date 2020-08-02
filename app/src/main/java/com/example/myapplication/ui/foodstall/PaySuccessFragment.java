package com.example.myapplication.ui.foodstall;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Order;
import com.example.myapplication.R;
import com.example.myapplication.ui.trackOrder.TrackOrderFragment;
import com.example.myapplication.ourData;

import java.util.jar.JarEntry;

import butterknife.ButterKnife;

public class PaySuccessFragment extends Fragment {
    Button buttonTurnBack, buttonTrackOrder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_pay_success, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonTurnBack = (Button) view.findViewById(R.id.btnTurnHome);
        buttonTrackOrder = (Button) view.findViewById(R.id.btnTrackOrder);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ourData.orderArrayList.isEmpty()) {
//                    Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
                }
                else{
//                    Toast.makeText(getActivity(), ourData.orderArrayList.size() + "", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < ourData.orderArrayList.size(); i++){
                        if (String.valueOf(ourData.orderArrayList.get(i).getIDcustomer()).equals(String.valueOf(ourData.id[0]))){
                            boolean check_trung_nhau = false;
                            for (int j = 0; j < ourData.arrayListOrderOfCustomer.size(); j ++){
                                if (ourData.arrayListOrderOfCustomer.get(j).getID().equals(ourData.orderArrayList.get(i).getID())){
                                    check_trung_nhau = true;
                                    break;
                                }
                            }
                            if (check_trung_nhau){
                                continue;
                            }
                            else {
                                ourData.arrayListOrderOfCustomer.add(ourData.orderArrayList.get(i));
                            }
                        }
                        else {
//                            Toast.makeText(getActivity(), "Khog giong dau a " + ourData.orderArrayList.get(i).getIDcustomer() + " " + ourData.id[0], Toast.LENGTH_SHORT).show();
                        }
                    }
//                    Toast.makeText(getActivity(), ourData.orderArrayList.get(10).getIDcustomer() + "", Toast.LENGTH_SHORT).show();
                }
            }

        }, 2000);


        buttonTurnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourData.tolPrice[0] = "0";
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourData.tolPrice[0] = "0";
//                Toast.makeText(getActivity(), ourData.arrayListOrderOfCustomer.size() + "", Toast.LENGTH_SHORT).show();
                FragmentActivity activity = (FragmentActivity) view.getContext();
                Fragment fragment = new TrackOrderFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.paySuccess, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}