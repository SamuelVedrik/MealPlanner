package com.samuelvedrik.mealplanner.dao;

import java.util.ArrayList;

public interface MenuDAO {
    public void save(Food food);
    public Food getFoodByName(String name);
    public void removeFood(String name);
    public ArrayList<String> getAllFoodNames();
}
