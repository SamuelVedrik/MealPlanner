package com.samuelvedrik.mealplanner.ui.manage_dish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samuelvedrik.mealplanner.R;
import com.samuelvedrik.mealplanner.ui.edit_food.EditFoodActivity;

import java.util.ArrayList;

public class ManageDishActivity extends AppCompatActivity implements ManageDishView {

    private ManageDishPresenter presenter;
    private ListView dishList;
    private FloatingActionButton addDishButton;
    public static final String DISH_KEY = "DISH_KEY";
    public static final String NEW_DISH = "NEW_DISH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_dish);
        presenter = new ManageDishPresenter(this);
        addDishButton = findViewById(R.id.addDish);
        dishList = findViewById(R.id.dishList);
        presenter.setup();
    }

    public void populateList(ArrayList<String> menu){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        dishList.setAdapter(adapter);
        dishList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dishClicked = presenter.getFood(position);
                Intent goToDish = new Intent(getApplicationContext(), EditFoodActivity.class);
                goToDish.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                goToDish.putExtra(DISH_KEY, dishClicked);
                startActivity(goToDish);
            }
        });
    }

    public void addDishClick(View view){
        Intent createDish = new Intent(this, EditFoodActivity.class);
        createDish.putExtra(DISH_KEY, NEW_DISH);
        createDish.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(createDish);
    }


}
