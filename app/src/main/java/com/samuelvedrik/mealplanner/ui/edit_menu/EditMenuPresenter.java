package com.samuelvedrik.mealplanner.ui.edit_menu;

import com.samuelvedrik.mealplanner.dao.CalendarDAO;
import com.samuelvedrik.mealplanner.dao.CalendarDataManager;
import com.samuelvedrik.mealplanner.dao.Day;
import com.samuelvedrik.mealplanner.dao.MenuDAO;
import com.samuelvedrik.mealplanner.dao.MenuDataManager;

public class EditMenuPresenter {

    private EditMenuView editMenuView;
    private MenuDAO menuDAO;
    private CalendarDAO calendarDAO;
    private String dateSelected;
    private Day day;

    public EditMenuPresenter(EditMenuView editMenuView){
        this.editMenuView = editMenuView;
        this.menuDAO = new MenuDataManager();
        this.calendarDAO = new CalendarDataManager();
    }

    public void setup(String date){
        this.dateSelected = date;
        this.day = calendarDAO.load(dateSelected);
        editMenuView.populateLunch(day.getLunchMenu().getMenuFoodOnly());
        editMenuView.populateDinner(day.getDinnerMenu().getMenuFoodOnly());
        editMenuView.populateSpinner(menuDAO.getAllFoodNames());
    }

    public void addLunch(String food){
        day.addLunchMenu(menuDAO.getFoodByName(food));
        calendarDAO.save(day);
        editMenuView.populateLunch(day.getLunchMenu().getMenuFoodOnly());
    }

    public void addDinner(String food){
        day.addDinnerMenu(menuDAO.getFoodByName(food));
        calendarDAO.save(day);
        editMenuView.populateDinner(day.getDinnerMenu().getMenuFoodOnly());
    }

    public void removeLunch(int position){
        String foodRemoved = day.getLunchMenu().getMenuFoodOnly().get(position);
        day.removeLunchMenu(menuDAO.getFoodByName(foodRemoved));
        calendarDAO.save(day);
        editMenuView.populateLunch(day.getLunchMenu().getMenuFoodOnly());
    }

    public void removeDinner(int position){
        String foodRemoved = day.getDinnerMenu().getMenuFoodOnly().get(position);
        day.removeDinnerMenu(menuDAO.getFoodByName(foodRemoved));
        calendarDAO.save(day);
        editMenuView.populateDinner(day.getDinnerMenu().getMenuFoodOnly());
    }
}
