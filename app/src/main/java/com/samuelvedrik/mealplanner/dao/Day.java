package com.samuelvedrik.mealplanner.dao;


/**
 * A class that represents a single day.
 */
public class Day {

    private Menu lunchMenu;
    private Menu dinnerMenu;
    private int year, month, day;


    /**
     * Constructs an empty day.
     * @param date The date of this day.
     */
    public Day(String date){
        String[] d = date.split("-");
        this.lunchMenu = new Menu();
        this.dinnerMenu = new Menu();
        this.year = Integer.parseInt(d[0]);
        this.month = Integer.parseInt(d[1]);
        this.day =  Integer.parseInt(d[2]);

    }

    /**
     * Constructs a non empty day.
     * @param date The date of this day.
     * @param lunchMenu The lunch menu for this day.
     * @param dinnerMenu The dinner menu for this day.
     */
    public Day(String date, Menu lunchMenu, Menu dinnerMenu){
        this(date);
        this.lunchMenu = lunchMenu;
        this.dinnerMenu = dinnerMenu;
    }

    // ===== GETTER/SETTERS =====
    public Menu getLunchMenu() {
        return lunchMenu;
    }

    public Menu getDinnerMenu() {
        return dinnerMenu;
    }

    public void addLunchMenu(Food lunch){
        this.lunchMenu.addFood(lunch);
    }

    public void addDinnerMenu(Food dinner){
        this.dinnerMenu.addFood(dinner);
    }

    public boolean removeLunchMenu(Food lunch){
        return this.lunchMenu.removeFood(lunch);
    }

    public boolean removeDinnerMenu(Food dinner){
        return  this.dinnerMenu.removeFood(dinner);
    }

    /**
     * Returns the date of this day in YYYY-MM-DD format.
     * @return String representation of this day in YYYY-MM-DD format.
     */
    public String getDate(){

        // This converts single digit numbers to be preceded by 0
        String monthstr = "00" + month;
        monthstr = monthstr.substring(monthstr.length() - 2);
        String daystr = "00" + day;
        daystr = daystr.substring(daystr.length() -2);

        return String.format("%d-%s-%s", year, monthstr, daystr);
    }

}
