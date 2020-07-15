package com.example.myapplication.ui.foodstall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.myapplication.R;
import com.example.myapplication.model.Meals;
import com.squareup.picasso.Picasso;

import static com.example.myapplication.ui.foodstall.FoodstallFragment.EXTRA_DETAIL;

public class foodDetail extends AppCompatActivity implements FoodDetailView {

    @BindView(R.id.food_icon_detail)
    ImageView mealThumb;

    @BindView(R.id.description_of_food_detail)
    TextView instructions;

    @BindView(R.id.name_of_food_detail)
    TextView nameMeal;

    @BindView(R.id.buttonMul)
    Button buttonMul;

    @BindView(R.id.buttonAdd)
    Button buttonAdd;

    @BindView(R.id.textResult)
    TextView textResult;

    public static String NameOfMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // for hiding title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_DETAIL);
        NameOfMeal = intent.getStringExtra(EXTRA_DETAIL);
        FoodDetailPresenter presenter = new FoodDetailPresenter(this);
        presenter.getMealByID(mealName);
    }

    @Override
    public void setMeal(Meals.Meal meal) {
        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        instructions.setText(meal.getStrInstructions());
        nameMeal.setText(NameOfMeal);
        buttonAdd.setText("+");
        buttonMul.setText("-");
        textResult.setText("0");
    }
}