package com.samuelvedrik.mealplanner.ui.manage_dish;

import com.samuelvedrik.mealplanner.dao.MenuDAO;
import com.samuelvedrik.mealplanner.dao.MenuDataManager;

public class ManageDishPresenter {

    private ManageDishView manageDishView;
    private MenuDAO menuDAO;


    public ManageDishPresenter(ManageDishView manageDishView){
        this.manageDishView = manageDishView;
        this.menuDAO = new MenuDataManager();
    }

    public void setup(){

//        ArrayList<String> test = new ArrayList<>();
//        test.add("food1");
//        test.add("food2");
//
//        manageDishView.populateList(test);
        manageDishView.populateList(menuDAO.getAllFoodNames());
    }

    public String getFood(int position) {
        return menuDAO.getAllFoodNames().get(position);
    }
}
