package com.samuelvedrik.mealplanner.dao;


/**
 * An interface for retrieving and saving data for the calendar.
 */
public interface CalendarDAO {

    /**
     * Saves the given Day to the file. This includes both the lunch menu and dinner menu.
     * @param day The day to be saved.
     */
    public void save(Day day);

    /**
     * Retrieves information about the given date. The Day object returned does not have it's
     * food objects filled with the description, which should be loaded by the MenuDAO.
     * @param date The date to load.
     * @return A day object that represents the menu for that day.
     */
    public Day load(String date);
}
