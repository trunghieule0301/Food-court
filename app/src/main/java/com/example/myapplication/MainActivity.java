package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ui.account.AccountFragment;
import com.example.myapplication.ui.foodstall.FoodstallFragment;
import com.example.myapplication.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    /*
    Dang Nguyen connect DATA =======================================================
    */
//  URL
    public String urlGetStallData = "http://foodcourt2020.medianewsonline.com/getStallData.php";
    public String urlGetFoodData = "http://foodcourt2020.medianewsonline.com/getFoodData.php";
    public String urlGetOrderData = "http://foodcourt2020.medianewsonline.com/getOrderData.php";
    public String urlGetDetailOrderData = "http://foodcourt2020.medianewsonline.com/getDetailOrder.php";
    public String urlGetCusData = "http://foodcourt2020.medianewsonline.com/getCusData.php";
//
    public ArrayList<Stall> arrayStall;
    public ArrayList<Food> arrayFood;
    public ArrayList<Order> arrayOrder;
    public ArrayList<DetailOrder> arrayDetailOrder;
    public ArrayList<Customer> arrayListCustomer;
    /*
    ===============================================================================
    */
    public static  String dataCus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());

        dataCus = getIntent().getStringExtra("putExtraToMain");

        /*
        Dang Nguyen connect DATA  =======================================================
        */
        arrayStall = new ArrayList<>();
        arrayFood  = new ArrayList<>();
        arrayOrder = new ArrayList<>();
        arrayDetailOrder = new ArrayList<>();
        arrayListCustomer = new ArrayList<>();

//        GetStallData(urlGetStallData);
//        GetFoodData(urlGetFoodData);
//        GetOrderData(urlGetOrderData);
//        GetDetailOrderData(urlGetDetailOrderData);
//        GetCustomerData(urlGetCusData);
//        Intent intent = getIntent();

//        Bundle bundle = intent.getBundleExtra("Data");
//        UserAccount = bundle.getString("AccountCus");

//        Toast.makeText(this, UserAccount.toString(), Toast.LENGTH_SHORT).show();

        /*
        ===============================================================================
        */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_foodstall:
                    selectedFragment = new FoodstallFragment();
                    break;
                case R.id.navigation_account:
                    selectedFragment = new AccountFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("account", dataCus);
                    selectedFragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container,  selectedFragment).commit();
            return loadFragment(selectedFragment);
        }
    };


    /*
    Dang Nguyen connect DATA =====================================================================
    */

    public void GetStallData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Get stall data success", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayStall.add(new Stall(
                                        object.getString("ID"),
                                        object.getString("Name"),
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
                        Toast.makeText(MainActivity.this, "Load data fail due to:" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetFoodData(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Get food data success", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Load data fail due to" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetOrderData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Get Order data success", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Load data fail due to" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetDetailOrderData(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Get detail order success", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayDetailOrder.add(new DetailOrder(
                                        object.getString("IDorder"),
                                        object.getString("IDfood"),
                                        object.getInt("Number"),
                                        (float) object.getDouble("Price")
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
                        Toast.makeText(MainActivity.this, "Load data fail due to" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void GetCustomerData(String url, ArrayList<Customer> arrayListCustomer){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, "Load Customer Data success", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this, "Load data fail due to " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    /*
    =====================================================================================================
    */
}