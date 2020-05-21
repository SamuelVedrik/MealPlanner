package com.samuelvedrik.mealplanner.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samuelvedrik.mealplanner.R;
import com.samuelvedrik.mealplanner.ui.edit_menu.EditMenuActivity;
import com.samuelvedrik.mealplanner.ui.manage_dish.ManageDishActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private CalendarView calendarView;
    private Button editMenu;
    private Button manageDishes;
    private LinearLayout lunch;
    private LinearLayout dinner;
    private static final String CALENDAR_FILE = "calendar.txt";
    private static final String MENU_FILE = "menu.txt";
    public static final String DATE_KEY = "DATE_KEY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File calendarFile = new File(getFilesDir(), CALENDAR_FILE);
        File menuFile = new File(getFilesDir(), MENU_FILE);
        try{
            calendarFile.createNewFile();
            menuFile.createNewFile();
        }
        catch(IOException e){
            ;
        }

        presenter = new MainPresenter(this, calendarFile, menuFile);


        calendarView = findViewById(R.id.calendarView);
        editMenu = findViewById(R.id.editMenu);
        manageDishes = findViewById(R.id.manageDishes);
        lunch = findViewById(R.id.lunchLayout);
        dinner = findViewById(R.id.dinnerLayout);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String monthstr = "00" + (month + 1);
                monthstr = monthstr.substring(monthstr.length() - 2);
                String daystr = "00" + dayOfMonth;
                daystr = daystr.substring(daystr.length() -2);
                presenter.setDateSelected(String.format("%d-%s-%s", year, monthstr, daystr));
                presenter.updateMenu();
            }
        });

        presenter.setup();
    }

    public void setLunch(ArrayList<String> lunch){
        TextView lunchDish;
        this.lunch.removeAllViews();
        for(String dish : lunch){
            lunchDish = new TextView(this);
            lunchDish.setText(dish);
            lunchDish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            lunchDish.setTextColor(Color.BLACK);
            this.lunch.addView(lunchDish);
        }
    }

    public void setDinner(ArrayList<String> lunch){
        TextView dinnerDish;
        this.dinner.removeAllViews();
        for(String dish : lunch){
            dinnerDish = new TextView(this);
            dinnerDish.setText(dish);
            dinnerDish.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
            dinnerDish.setTextColor(Color.BLACK);
            this.dinner.addView(dinnerDish);
        }
    }

    public void editMenuClick(View view){
        Intent intent = new Intent(this, EditMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(DATE_KEY, presenter.getDateSelected());
        startActivity(intent);
    }

    public void manageDishesClick(View view){
        Intent intent = new Intent(this, ManageDishActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }


}
