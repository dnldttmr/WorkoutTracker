package de.dnldttmr.workouttracker.database;

import java.io.Serializable;

public class Exercise implements Serializable {

    private int exercise_id;
    private String name;
    private String muscle_group;
    private String desc;
    private int sets;
    private int reps;
    private int difficulty;
    private String photo;
    private String video;
    private String link;

    public Exercise() {

    }

    public Exercise(String name, String muscle_group, String description, int sets, int reps, int difficulty, String photo, String video, String link) {
        this.name = name;
        this.muscle_group = muscle_group;
        this.desc = description;
        this.sets = sets;
        this.reps = reps;
        this.difficulty = difficulty;
        this.photo = photo;
        this.video = video;
        this.link = link;
    }

    public int getId() {
        return exercise_id;
    }

    public void setId(int id) {
        this.exercise_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscle_group() {
        return muscle_group;
    }

    public void setMuscle_group(String muscle_group) {
        this.muscle_group = muscle_group;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getDifficulty() { return difficulty; }

    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
