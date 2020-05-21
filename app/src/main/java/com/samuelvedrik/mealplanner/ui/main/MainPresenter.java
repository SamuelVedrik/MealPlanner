package com.samuelvedrik.mealplanner.ui.main;

import com.samuelvedrik.mealplanner.dao.CalendarDAO;
import com.samuelvedrik.mealplanner.dao.CalendarDataManager;
import com.samuelvedrik.mealplanner.dao.Day;
import com.samuelvedrik.mealplanner.dao.MenuDAO;
import com.samuelvedrik.mealplanner.dao.MenuDataManager;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainPresenter {

    private MainView mainView;
    private CalendarDAO calendarDAO;
    private MenuDAO menuDAO;
    private String dateSelected;

    public MainPresenter(MainView mainView, File calanderFile, File menuFile){
        this.mainView = mainView;
        this.calendarDAO = new CalendarDataManager();
        this.menuDAO = new MenuDataManager();
        CalendarDataManager.setDataFile(calanderFile);
        MenuDataManager.setDataFile(menuFile);
    }

    public void setup(){
        LocalDate currDate = LocalDate.now();
        dateSelected = currDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        updateMenu();
    }

    public void setDateSelected(String date){
        this.dateSelected = date;
    }

    public String getDateSelected(){
        return this.dateSelected;
    }

    public void updateMenu(){
        System.out.println("Updating!");
        Day day = calendarDAO.load(dateSelected);
        mainView.setLunch(day.getLunchMenu().getMenuFoodOnly());
        mainView.setDinner(day.getDinnerMenu().getMenuFoodOnly());
    }
}
