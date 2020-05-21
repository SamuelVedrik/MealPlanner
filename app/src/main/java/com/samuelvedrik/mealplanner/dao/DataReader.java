package com.samuelvedrik.mealplanner.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class to read from the file.
 */
public class DataReader {

    private static final String TAG = "DataReader";

    /**
     * Reads the JSON from the file, and returns it.
     * @param dataFile The file to read from
     * @return The JSON object stored in the file.
     */
    public JSONObject readJSON(File dataFile) {
        String oldData = readFile(dataFile);
        JSONObject result = null;
        try {
            result = new JSONObject(oldData);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }

    /**
     * Returns a string from the file specified in dataFile.
     *
     * @return String representation of the file.
     */
    private String readFile(File dataFile) {
        StringBuilder result = new StringBuilder();
        try (FileReader reader = new FileReader(dataFile)) {
            BufferedReader br = new BufferedReader(reader);
            String newLine;
            while ((newLine = br.readLine()) != null) {
                result.append(newLine);
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return result.toString();
    }


}
