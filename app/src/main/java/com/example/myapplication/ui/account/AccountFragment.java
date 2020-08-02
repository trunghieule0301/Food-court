package com.example.myapplication.ui.account;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.Order;
import com.example.myapplication.ourData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ChangePasswordActivity;
import com.example.myapplication.Customer;
import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
import com.example.myapplication.ui.foodstall.FoodDetailFragment;
import com.example.myapplication.ui.trackOrder.TrackOrderFragment;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class AccountFragment extends Fragment {

    Button buttonChangePasswordAccount, buttonLogoutAccount;
    TextView textViewNameAcocunt, textViewAccount, textViewEmailAccount, textViewAgeAccount, textViewGenderAccount;
    CardView cardViewTrackOrder;
    private String urlGetCusData = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    public String urlGetOrderData = "http://foodcourt2020.medianewsonline.com/getOrderData.php";

    private ArrayList<Customer> arrayListCustomer;
    public static String args;
    private int index = -1;
    private String IDcustomerne = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
        args = ourData.account[0];
//        Toast.makeText(getActivity(), "test click: " + args, Toast.LENGTH_SHORT).show();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        args = ourData.account[0];
        Anhxa();
        arrayListCustomer =  new ArrayList<>();
        GetCustomerData(urlGetCusData, arrayListCustomer);
        GetOrderData(urlGetOrderData, ourData.orderArrayList);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!args.equals("")) {
                    for (int i = 0; i < arrayListCustomer.size(); i++) {
                        if (args.equals(arrayListCustomer.get(i).getAccount().toString().trim())) {
                            index = i;

                        }
                    }
//                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    textViewAccount.setText(arrayListCustomer.get(index).getAccount());
                    textViewNameAcocunt.setText(arrayListCustomer.get(index).getName());
                    textViewEmailAccount.setText(arrayListCustomer.get(index).getEmail());
                    textViewAgeAccount.setText(arrayListCustomer.get(index).getAge() + "");
                    IDcustomerne = arrayListCustomer.get(index).getID() + "";
                }
            }
        }, 2000);

        Handler handler1 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ourData.orderArrayList.size(); i++){
                    if (String.valueOf(ourData.orderArrayList.get(i).getIDcustomer()).equals(String.valueOf(IDcustomerne))){
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
            }
        }, 3000);

        buttonChangePasswordAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                intent.putExtra("index_cus", index);
                startActivity(intent);
            }
        });
        buttonLogoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        cardViewTrackOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Track Order
//                Toast.makeText(getActivity(), "Feature is in progress", Toast.LENGTH_SHORT).show();
                FragmentActivity activity = (FragmentActivity) view.getContext();
                Fragment fragment = new TrackOrderFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameAccount, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        args = ourData.account[0];
    }

    private void Anhxa(){
        buttonChangePasswordAccount = (Button) getView().findViewById(R.id.btnChangePasswordAccount);
        buttonLogoutAccount = (Button) getView().findViewById(R.id.btnLogoutAccount);
        textViewNameAcocunt = (TextView) getView().findViewById(R.id.textViewName);
        textViewAccount = (TextView) getView().findViewById(R.id.textViewAccount);
        textViewEmailAccount = (TextView) getView().findViewById(R.id.textViewEmail);
        textViewAgeAccount = (TextView) getView().findViewById(R.id.textViewAge);
        textViewGenderAccount = (TextView) getView().findViewById(R.id.textViewGender);
        cardViewTrackOrder = (CardView) getView().findViewById(R.id.cardviewTrackOrder);
    }

    public void GetCustomerData(String url, ArrayList<Customer> arrayListCustomer){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getActivity(), "Load Customer Data success", Toast.LENGTH_SHORT).show();
                        for (int i = 0;i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListCustomer.add(new Customer(
                                        object.getInt("ID"),
                                        object.getString("Account"),
                                        object.getString("Name"),
                                        object.getString("Email"),
                                        object.getString("PassWord"),
                                        object.getInt("Age"),
                                        object.getString("Sex"),
                                        object.getString("Address")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Load data fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
    public void GetOrderData(String url, ArrayList<Order> arrayOrder){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getActivity(), "Get Order data success", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayOrder.add(new Order(
                                        object.getString("ID"),
                                        object.getString("IDstall"),
                                        object.getInt("IDcustomer"),
                                        (float) object.getDouble("TotalPrice"),
                                        object.getInt("Total"),
                                        object.getString("Status"),
                                        object.getString("Date")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Load data fail due to" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }


}
