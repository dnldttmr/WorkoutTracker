package de.dnldttmr.workouttracker.database;

public class WorkoutLog {

    private int workoutLog_id;
    private int day;
    private int month;
    private int year;

    public WorkoutLog() {

    }

    public WorkoutLog(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getID() {
        return workoutLog_id;
    }

    public void setID(int workoutLog_id) {
        this.workoutLog_id = workoutLog_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
