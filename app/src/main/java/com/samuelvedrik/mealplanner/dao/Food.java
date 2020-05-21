package com.samuelvedrik.mealplanner.dao;

public class Food {

    private String name;
    private String description;

    public Food(String name) {
        this.name = name;
        this.description = "";
    }

    public Food(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        Food other = (Food) o;
        return other.getName().equals(name) && other.getDescription().equals(description);
    }
}
