package com.example.myapplication.ui.account;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Customer;
import com.example.myapplication.Login;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.RegisterActivity;
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
    private String urlGetCusData = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    private ArrayList<Customer> arrayListCustomer;
    String AccountFromLogin;
    public static String account = "null";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);
//        if(getArguments() != null) {
//            account = getArguments().getString("AccountCus");
//        }

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Anhxa();

        AccountFromLogin = getArguments().getString("AccountCus");
        Toast.makeText(getActivity(), AccountFromLogin, Toast.LENGTH_SHORT).show();

        arrayListCustomer =  new ArrayList<>();
        GetCustomerData(urlGetCusData, arrayListCustomer);
        buttonChangePasswordAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonLogoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void Anhxa(){
        buttonChangePasswordAccount = (Button) getView().findViewById(R.id.btnChangePasswordAccount);
        buttonLogoutAccount = (Button) getView().findViewById(R.id.btnLogoutAccount);
        textViewNameAcocunt = (TextView) getView().findViewById(R.id.textViewName);
        textViewAccount = (TextView) getView().findViewById(R.id.textViewAccount);
        textViewEmailAccount = (TextView) getView().findViewById(R.id.textViewEmail);
        textViewAgeAccount = (TextView) getView().findViewById(R.id.textViewAge);
        textViewGenderAccount = (TextView) getView().findViewById(R.id.textViewGender);
    }

    public void GetCustomerData(String url, ArrayList<Customer> arrayListCustomer){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Load Customer Data success", Toast.LENGTH_SHORT).show();
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

}
