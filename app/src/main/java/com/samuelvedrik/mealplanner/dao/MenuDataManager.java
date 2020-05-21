package com.samuelvedrik.mealplanner.dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 A class to handle data IO for the menu. The format of the data is as follows:
 {"food1": "desc1", "food2": "desc2" ...}

 **/


public class MenuDataManager implements MenuDAO{

    private static final String TAG = "MenuDataManager";
    private static File dataFile;
    private DataReader dataReader = new DataReader();

    public static void setDataFile(File file){ dataFile = file;
    }

    public void save(Food food){
        updateFile(food.getName(), food.getDescription());
    }


    public Food getFoodByName(String name){
        JSONObject data = dataReader.readJSON(dataFile);
//        try {
////            if(data.has(name)) {
////                return new Food(name, data.getString(name));
////            }
////            } catch (JSONException e) {
////                Log.e(TAG, e.toString());
////            }
////        return null;

        if(data != null && data.has(name)) {
            try {
                return new Food(name, data.getString(name));
            } catch (JSONException e) {
//                Log.e(TAG, e.toString());
            }
        }
        return null;

    }

    @Override
    public void removeFood(String name) {
        JSONObject oldData = dataReader.readJSON(dataFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            if (oldData == null) {
                oldData = new JSONObject();
            }
            oldData.remove(name);
            writer.write(oldData.toString());
        } catch (IOException e) {
//            Log.e(TAG, e.toString());
        }
    }

    public ArrayList<String> getAllFoodNames(){
        JSONObject data = dataReader.readJSON(dataFile);
        ArrayList<String> result = new ArrayList<>();
        if(data != null){
            Iterator<String> iterator = data.keys();
            while(iterator.hasNext()){
                result.add(iterator.next());
            }
        }
        return result;
    }



    private void updateFile(String name, String description) {
        JSONObject oldData = dataReader.readJSON(dataFile);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            if (oldData == null) {
                oldData = new JSONObject();
            }
            oldData.put(name, description);
            writer.write(oldData.toString());
        } catch (IOException | JSONException e) {
//            Log.e(TAG, e.toString());
        }
    }


}

