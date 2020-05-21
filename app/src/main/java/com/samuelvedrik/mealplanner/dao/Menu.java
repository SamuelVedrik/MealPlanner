package com.samuelvedrik.mealplanner.dao;


import java.util.ArrayList;

public class Menu {

    private ArrayList<Food> menu;

    public Menu(){
        this.menu = new ArrayList<>();
    }

    public Menu(ArrayList<Food> menu){
        this.menu = menu;
    }

    public void addFood(Food food){
        menu.add(food);
    }

    public boolean removeFood(Food food){
        return menu.remove(food);
    }

    public ArrayList<String> getMenuFoodOnly(){
        ArrayList<String> result = new ArrayList<>();
        for(Food food : menu){
            result.add(food.getName());
        }
        return result;
    }

}
