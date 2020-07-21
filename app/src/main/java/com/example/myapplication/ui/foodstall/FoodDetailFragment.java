package com.example.myapplication.ui.foodstall;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Customer;
import com.example.myapplication.Food;
import com.example.myapplication.Order;
import com.example.myapplication.R;
import com.example.myapplication.ui.adapter.FoodDetailRecyclerViewAdapter;
import com.example.myapplication.ourData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailFragment extends Fragment {

    String urlAddOrder = "http://foodcourt2020.medianewsonline.com/insertOrder.php";
    String urlGetCusData = "http://foodcourt2020.medianewsonline.com/getCusData.php";
    String urlGetFoodData = "http://foodcourt2020.medianewsonline.com/getFoodData.php";
    String urlAddDetailOrder = "http://foodcourt2020.medianewsonline.com/insertDetailOrder.php";
    String urlGetOrderData = "http://foodcourt2020.medianewsonline.com/getOrderData.php";
    String urlUpdateMoney = "http://foodcourt2020.medianewsonline.com/updateCustomerMoney.php";
    ArrayList<Food> arrayListFood = new ArrayList<>();
    ArrayList<Customer> arrayListCus = new ArrayList<>();
    ArrayList<Order> arrayOrder = new ArrayList<>();
    String user_account = "";

    @BindView(R.id.button_pay)
    Button buttonPay;

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

//        Toast.makeText(view.getContext(), "account " + ourData.account[0], Toast.LENGTH_SHORT).show();

        GetCustomerData(urlGetCusData,arrayListCus);
        GetFoodData(urlGetFoodData,arrayListFood);
        GetOrderData(urlGetOrderData);

        sum = Integer.parseInt(ourData.tolPrice[0]);
        totalPrice.setText(String.valueOf(sum));

        adapter.setOnItemClickListener((v, position, check, SUM) -> {
            int price = Integer.parseInt(ourData.price[position]);
            if(check == 1){
                ourData.ammount[position]++;
                sum = sum + price;
            }
            else if(check == 0) {

                if (ourData.ammount[position] > 0){
                    ourData.ammount[position]--;
                }
                Toast.makeText(getActivity(), SUM +"", Toast.LENGTH_SHORT).show();
                if (SUM >= 0) {
                    sum = sum - price;
                }
            }
            totalPrice.setText(String.valueOf(sum));
        });

        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // find id stall
                String id_of_stall="";
                for (int i = 0;i < arrayListFood.size(); i++){
                    if (ourData.food[0].equals(arrayListFood.get(i).getName())){
                        id_of_stall = arrayListFood.get(i).getIDstall();
                        break;
                    }
                }

                // find id account
                String idcustomeerorder = "";
                float totalmoneyofcus = 0;
                for (int i = 0;i < arrayListCus.size();i++){
                    if (arrayListCus.get(i).getAccount().equals(ourData.account[0])){
                        idcustomeerorder = arrayListCus.get(i).getID() + "";
                        totalmoneyofcus = arrayListCus.get(i).getMoney() + 0;
                        break;
                    }
                }
                // find total of total
                int totaloftotal = 0;
                for (int i = 0; i < ourData.num; i++){
                    totaloftotal += ourData.ammount[i];
                }
                // Add order to db
                AddOrdertoDb(urlAddOrder, id_of_stall, idcustomeerorder, totalPrice.getText().toString(), totaloftotal + "");

                // update total money of customer
                int cai_nay_la_chuyen_tu_string_sang_int = Integer.parseInt(totalPrice.getText().toString());
                String nay_la_tong_so_tien_moi_ma_khach_da_mua = (totalmoneyofcus + (float) cai_nay_la_chuyen_tu_string_sang_int) + "";
                Toast.makeText(getActivity(), nay_la_tong_so_tien_moi_ma_khach_da_mua, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), idcustomeerorder, Toast.LENGTH_SHORT).show();
                UpdateLastPay(urlUpdateMoney, idcustomeerorder, nay_la_tong_so_tien_moi_ma_khach_da_mua);

                // Add order detail to db
                for (int i = 0;i < ourData.num; i++){
                    // find IDorder
                    String idorder = arrayOrder.get(arrayOrder.size()-1).getID() + "";
                    int idorderint = Integer.parseInt(idorder)  + 1;
                    idorder = idorderint + "";
                    //find IDfood
                    String idfood ="";
                    int ind = -1;
                    for (int j = 0;j < arrayListFood.size(); j++){
                        if (arrayListFood.get(j).getName().equals(ourData.food[i])){
                            idfood = arrayListFood.get(j).getID();
                            ind = j;
                        }
                    }
                    // find number
                    String number = ourData.ammount[i] + "";
                    // find total price for this food
                    String total = (arrayListFood.get(ind).getPrice() * (float)ourData.ammount[i]) + "";
                    AddDetailOrder(urlAddDetailOrder, idorder, idfood, number, total);
                }
                FragmentActivity activity = (FragmentActivity) view.getContext();
                Fragment fragment = new PaySuccessFragment();
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameFoodDetail, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    public void UpdateLastPay(String url, String idcustomer, String money){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")){
                            Toast.makeText(getActivity(), "Update money success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Update money fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Conneect fail", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IDcustomer", idcustomer);
                params.put("Money", money);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void AddOrdertoDb(String url, String idstall, String idcus, String totalprice, String total){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")){
                            Toast.makeText(getActivity(), "Pay successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Pay fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Load link fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IDStall", idstall);
                params.put("IDcustomer", idcus);
                params.put("TotalPrice", totalprice);
                params.put("Total", total);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void AddDetailOrder(String url, String idorder, String idfood, String number, String total){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Success")){
                            Toast.makeText(getActivity(), "Add detail success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getActivity(), "Add detail fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Load fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("IDorder", idorder);
                params.put("IDfood", idfood);
                params.put("Number", number);
                params.put("Total", total);
                return params;
            }
        };
        requestQueue.add(stringRequest);
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
                                arrayListCustomer.get(arrayListCustomer.size()-1).setMoney(object.getInt("Money"));
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

    public void GetFoodData(String url, ArrayList<Food> arrayFood){
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Get food data success", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayFood.add(new Food(
                                        object.getString("ID"),
                                        object.getString("Name"),
                                        object.getString("IDstall"),
                                        (float) object.getDouble("Price"),
                                        object.getString("Url")
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
                        Toast.makeText(getActivity(), "Load data fail due to" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetOrderData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(getActivity(), "Get Order data success", Toast.LENGTH_SHORT).show();
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