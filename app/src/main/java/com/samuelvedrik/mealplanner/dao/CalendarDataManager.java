package com.samuelvedrik.mealplanner.dao;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to handle the data IO for the calender. The format of the data saved is as follows:
 * {"YYYY-MM-DD": {"Lunch": [string1, string2, ....], "Dinner": [string1, string2, ...]}
 *
 */


public class CalendarDataManager implements CalendarDAO {


    private static final String TAG = "CalendarDataManager";
    private static File dataFile;
    private DataReader dataReader = new DataReader();

    /**
     * Sets the file for loading and saving.
     * @param file
     */
    public static void setDataFile(File file){
        dataFile = file;
    }

    @Override
    public void save(Day day){
        HashMap<String, ArrayList<String>> dayData = formatDay(day);

        updateFile(day.getDate(), dayData);
    }

    @Override
    public Day load(String Date){
        JSONObject dayData = dataReader.readJSON(dataFile);
        if(dayData != null && dayData.has(Date)) {
            try {
                JSONObject menuData = dayData.getJSONObject(Date);
                JSONArray lunch = menuData.getJSONArray("Lunch");
                JSONArray dinner = menuData.getJSONArray("Dinner");

                // The food in the menu are not populated.

                Menu lunchMenu = new Menu(toArrayList(lunch));
                Menu dinnerMenu = new Menu(toArrayList(dinner));
                return new Day(Date, lunchMenu, dinnerMenu);
            } catch (Exception e) {
//                Log.e(TAG, e.toString());

            }

        }
        return new Day(Date);
    }


    /**
     * Formats the given day into a hashmap representation.
     * @param day The day to be formatted
     * @return A hashmap that represents the given day. The format is documented above.
     */
    private HashMap<String, ArrayList<String>> formatDay(Day day) {
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        result.put("Lunch", day.getLunchMenu().getMenuFoodOnly());
        result.put("Dinner", day.getDinnerMenu().getMenuFoodOnly());
        return result;

    }

    /**
     * Updates the given file.
     * @param date The key to be updated
     * @param dayData The value to be updated in date.
     */
    private void updateFile(String date, HashMap<String, ArrayList<String>> dayData){
        JSONObject oldData = dataReader.readJSON(dataFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            if (oldData == null) {
                oldData = new JSONObject();
            }
            oldData.put(date, new JSONObject(dayData));
            writer.write(oldData.toString());
        } catch (IOException | JSONException e) {
//            Log.e(TAG, e.toString());

        }

    }


    /**
     * Converts a JSONArray to ArrayList.
     * @param array The JSONArray to convert.
     * @return The same array in an ArrayList object.
     */
    private ArrayList<Food> toArrayList(JSONArray array){
        ArrayList<Food> result = new ArrayList<>();
        for(int i = 0; i < array.length(); i++){
            try {
                result.add(new Food(array.getString(i)));
            } catch (JSONException e) {
//                Log.e(TAG, e.toString());
            }

        }
        return result;
    }


}
