package com.samuelvedrik.mealplanner.ui.edit_menu;

import java.util.ArrayList;

public interface EditMenuView {

    public void populateLunch(ArrayList<String> menu);
    public void populateDinner(ArrayList<String> menu);
    public void populateSpinner(ArrayList<String> menu);
}
