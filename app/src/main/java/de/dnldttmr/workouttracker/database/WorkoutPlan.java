package de.dnldttmr.workouttracker.database;

import java.io.Serializable;

public class WorkoutPlan implements Serializable {

    private int workoutPlan_id;
    private String name;

    public WorkoutPlan() {

    }

    public WorkoutPlan(String name) {
        this.name = name;
    }

    public int getId() {
        return workoutPlan_id;
    }

    public void setId(int id) {
        this.workoutPlan_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
