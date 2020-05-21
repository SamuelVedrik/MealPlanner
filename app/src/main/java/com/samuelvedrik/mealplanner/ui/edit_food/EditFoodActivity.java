package com.samuelvedrik.mealplanner.ui.edit_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.samuelvedrik.mealplanner.R;
import com.samuelvedrik.mealplanner.ui.manage_dish.ManageDishActivity;

public class EditFoodActivity extends AppCompatActivity implements EditFoodView{

    private EditFoodPresenter presenter;
    private EditText nameText;
    private EditText descText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        presenter = new EditFoodPresenter(this);
        nameText = findViewById(R.id.nameText);
        descText = findViewById(R.id.descText);
        Intent contextIntent = getIntent();
        String dishSelected = contextIntent.getStringExtra(ManageDishActivity.DISH_KEY);

        presenter.setup(dishSelected);


    }

    public void cancelClick(View view){
        finish();
    }

    public void doneClick(View view){
        presenter.saveFood(nameText.getText().toString(), descText.getText().toString());
        Intent finish = new Intent(this, ManageDishActivity.class);
        finish.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(finish);
    }

    public void deleteClick(View view){
        presenter.removeFood(nameText.getText().toString());
        Intent finish = new Intent(this, ManageDishActivity.class);
        finish.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(finish);
    }

    @Override
    public void setHints(String name, String description) {
        nameText.setHint(name);
        descText.setHint(description);
        nameText.setText("");
        descText.setText("");
    }

    @Override
    public void setNameAndDesc(String name, String description) {
        nameText.setText(name);
        descText.setText(description);
    }
}
