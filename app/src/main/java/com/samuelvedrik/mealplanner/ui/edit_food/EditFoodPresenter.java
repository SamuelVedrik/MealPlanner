package com.samuelvedrik.mealplanner.ui.edit_food;

import com.samuelvedrik.mealplanner.dao.Food;
import com.samuelvedrik.mealplanner.dao.MenuDAO;
import com.samuelvedrik.mealplanner.dao.MenuDataManager;
import com.samuelvedrik.mealplanner.ui.manage_dish.ManageDishActivity;

public class EditFoodPresenter {

    private EditFoodView editFoodView;
    private MenuDAO menuDAO;
    private String dishSelected;


    public EditFoodPresenter(EditFoodView editFoodView){
        this.editFoodView = editFoodView;
        this.menuDAO = new MenuDataManager();
    }

    public void setup(String dishSelected){

        this.dishSelected = dishSelected;
        editFoodView.setHints("Enter Name of Dish", "Enter Description of Dish.");
        if(!dishSelected.equals(ManageDishActivity.NEW_DISH)) {
            Food selected = menuDAO.getFoodByName(dishSelected);
            editFoodView.setNameAndDesc(selected.getName(), selected.getDescription());
        }
    }


    public void saveFood(String name, String desc){
        name = name.toLowerCase();
        if(!name.equals(dishSelected)){
            menuDAO.removeFood(dishSelected);
        }
        Food toSave = new Food(name, desc);
        menuDAO.save(toSave);
    }

    public void removeFood(String name){
        menuDAO.removeFood(name);
    }
}
