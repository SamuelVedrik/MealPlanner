package com.samuelvedrik.mealplanner.ui.edit_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.samuelvedrik.mealplanner.R;
import com.samuelvedrik.mealplanner.ui.main.MainActivity;

import java.util.ArrayList;

public class EditMenuActivity extends AppCompatActivity implements EditMenuView {

    private EditMenuPresenter presenter;
    private Spinner dishSpinner;
    private ListView lunchList;
    private ListView dinnerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        presenter = new EditMenuPresenter(this);

        Intent contextIntent = getIntent();
        String date = contextIntent.getStringExtra(MainActivity.DATE_KEY);

        dishSpinner = findViewById(R.id.dishSpinner);
        lunchList = findViewById(R.id.lunchList);
        dinnerList = findViewById(R.id.dinnerList);

        presenter.setup(date);
    }



    public void addLunchClick(View view){
        String foodSelected = dishSpinner.getSelectedItem().toString();
        presenter.addLunch(foodSelected);
    }

    public void addDinnerClick(View view){
        String foodSelected = dishSpinner.getSelectedItem().toString();
        presenter.addDinner(foodSelected);
    }

    public void doneClick(View view){
        Intent goToMain = new Intent(this, MainActivity.class);
        startActivity(goToMain);
    }

    public void populateSpinner(ArrayList<String> menu){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        dishSpinner.setAdapter(adapter);
    }


    @Override
    public void populateLunch(ArrayList<String> menu) {
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);

        EditMenuAdapter adapter = new EditMenuAdapter(this, menu, new DeleteLunchListener(presenter));
        lunchList.setAdapter(adapter);
    }

    @Override
    public void populateDinner(ArrayList<String> menu) {
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        EditMenuAdapter adapter = new EditMenuAdapter(this, menu, new DeleteDinnerListener(presenter));
        dinnerList.setAdapter(adapter);
    }
}
