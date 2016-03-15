package de.dnldttmr.workouttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTables extends SQLiteOpenHelper{

    //Logcat
    private static final String LOG = DatabaseTables.class.getName();

    //Db Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "workoutDB";

    //Table Names
    private static final String TABLE_EXERCISES = "exercises";
    private static final String TABLE_WORKOUTPLAN = "workoutPlans";
    private static final String TABLE_WORKOUTPLAN_EXERCISES = "workoutPlans_exercises";

    //Column names for TABLE_EXERCISES
    private static final String EXERCISES_ID = "exercises_id";
    private static final String EXERCISES_NAME = "name";
    private static final String EXERCISES_MUSCLEGROUP = "muscle_group";
    private static final String EXERCISES_DESC = "description";
    private static final String EXERCISES_SETS = "sets";
    private static final String EXERCISES_REPS = "reps";
    private static final String EXERCISE_DIFFICULTY = "difficulty";
    private static final String EXERCISES_PHOTO = "photo";
    private static final String EXERICSES_VIDEO = "video";
    private static final String EXERCISES_LINK = "link";

    //Column names for TABLE_WORKOUTPLAN
    private static final String WORKOUTPLAN_ID = "workoutPlan_id";
    private static final String WORKOUTPLAN_NAME = "name";

    //Colum names for TABLE_WORKOUTPLAN_EXERCISES
    private static final String WORKOUTPLAN_EXERCISES_ID = "workoutPlans_exercises_id";

    //Column name Array
    private String[] columns_exercise = {EXERCISES_ID, EXERCISES_NAME, EXERCISES_MUSCLEGROUP, EXERCISES_DESC, EXERCISES_SETS,
        EXERCISES_REPS, EXERCISE_DIFFICULTY, EXERCISES_PHOTO, EXERICSES_VIDEO, EXERCISES_LINK};
    private String[] columns_workoutPlan = {WORKOUTPLAN_ID, WORKOUTPLAN_NAME};
    private String[] columns_workoutPlan_exercise = {WORKOUTPLAN_EXERCISES_ID, EXERCISES_ID, WORKOUTPLAN_ID};
    //Create table
    //Exercises
    private static final String CREATE_TABLE_EXERCISES =
            "CREATE TABLE " + TABLE_EXERCISES
                    + "(" + EXERCISES_ID + " INTEGER PRIMARY KEY, "
                    + EXERCISES_NAME + " TEXT, "
                    + EXERCISES_MUSCLEGROUP + " TEXT, "
                    + EXERCISES_DESC + " TEXT, "
                    + EXERCISES_SETS + " INTEGER, "
                    + EXERCISES_REPS + " INTEGER, "
                    + EXERCISE_DIFFICULTY + " INTEGER, "
                    + EXERCISES_PHOTO + " TEXT, "
                    + EXERICSES_VIDEO + " TEXT, "
                    + EXERCISES_LINK + " TEXT)";

    //workoutPlan
    private static final String CREATE_TABLE_WORKOUTPLAN =
            "CREATE TABLE " + TABLE_WORKOUTPLAN
                    + "(" + WORKOUTPLAN_ID + " INTEGER PRIMARY KEY, "
                    + WORKOUTPLAN_NAME + " TEXT)";

    //workoutPlan Exercises
    private static final String CREATE_TABLE_WORKOUTPLAN_EXERCISES =
            "CREATE TABLE " + TABLE_WORKOUTPLAN_EXERCISES
                + "(" + WORKOUTPLAN_EXERCISES_ID + " INTEGER PRIMARY KEY, "
                + EXERCISES_ID + " INTEGER, "
                + WORKOUTPLAN_ID + " INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISES);
        db.execSQL(CREATE_TABLE_WORKOUTPLAN);
        db.execSQL(CREATE_TABLE_WORKOUTPLAN_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTPLAN);

        onCreate(db);
    }

    //Constructor
    public DatabaseTables(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public long createExercise(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXERCISES_NAME, exercise.getName());
        values.put(EXERCISES_MUSCLEGROUP, exercise.getMuscle_group());
        values.put(EXERCISES_DESC, exercise.getDescription());
        values.put(EXERCISES_SETS, exercise.getSets());
        values.put(EXERCISES_REPS, exercise.getReps());
        values.put(EXERCISE_DIFFICULTY, exercise.getDifficulty());
        values.put(EXERCISES_PHOTO, exercise.getPhoto());
        values.put(EXERICSES_VIDEO, exercise.getVideo());
        values.put(EXERCISES_LINK, exercise.getLink());

        long exercise_id = db.insert(TABLE_EXERCISES, null, values);
        return exercise_id;
    }

    public List<Exercise> getAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = db.query(TABLE_EXERCISES, columns_exercise, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Exercise exercise = new Exercise();
            exercise.setId(Integer.parseInt(cursor.getString(0)));
            exercise.setName(cursor.getString(1));
            exercise.setMuscle_group(cursor.getString(2));
            exercise.setDescription(cursor.getString(3));
            exercise.setSets(Integer.parseInt(cursor.getString(4)));
            exercise.setReps(Integer.parseInt(cursor.getString(5)));
            exercise.setDifficulty(Integer.parseInt(cursor.getString(6)));
            exercise.setPhoto(cursor.getString(7));
            exercise.setVideo(cursor.getString(8));
            exercise.setLink(cursor.getString(9));

            exercises.add(exercise);

            cursor.moveToNext();
        }

        cursor.close();

        return exercises;
    }

    public long createWorkoutPlan() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORKOUTPLAN_NAME, "My WorkoutPlan");

        long workoutPlan_id = db.insert(TABLE_WORKOUTPLAN, null, values);
        return workoutPlan_id;
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<WorkoutPlan> workoutPlans = new ArrayList<WorkoutPlan>();

        Cursor cursor = db.query(TABLE_WORKOUTPLAN, columns_workoutPlan, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            WorkoutPlan workoutPlan = new WorkoutPlan();
            workoutPlan.setId(Integer.parseInt(cursor.getString(0)));
            workoutPlan.setName(cursor.getString(1));

            workoutPlans.add(workoutPlan);

            cursor.moveToNext();
        }

        cursor.close();

        return  workoutPlans;
    }

    public void editWorkoutPlanName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_WORKOUTPLAN + " SET " + WORKOUTPLAN_NAME + " = '" + name + "' WHERE " + WORKOUTPLAN_ID + " = " + id);
    }

    public List<Exercise> returnExercisesForWorkoutPlan(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Exercise> exerciseList = new ArrayList<Exercise>();

        //Cursor cursor = db.exeqSQL("SELECT e.exercises_id, e.name, e.muscle_group");
        Cursor cursor = db.rawQuery("SELECT e.exercises_id, e.name, e.muscle_group from exercises e " +
                "JOIN " + TABLE_WORKOUTPLAN_EXERCISES + " using (" + EXERCISES_ID +") " +
                "JOIN " + TABLE_WORKOUTPLAN + " using (" + WORKOUTPLAN_ID + ") " +
                "WHERE " + WORKOUTPLAN_ID + " = " + id, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Exercise exercise = new Exercise();
            exercise.setId(Integer.parseInt(cursor.getString(0)));
            exercise.setName(cursor.getString(1));
            exercise.setMuscle_group(cursor.getString(2));

            exerciseList.add(exercise);

            cursor.moveToNext();
        }

        cursor.close();

        return exerciseList;
    }

    public void addExerciseToWorkoutPlan(int exercise_id, int workoutPlan_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("INSERT INTO " + TABLE_WORKOUTPLAN_EXERCISES + " (" + EXERCISES_ID + ", " + WORKOUTPLAN_ID + ")" +
                "VALUES (" + exercise_id + ", " + workoutPlan_id + ")");
    }

    public void deleteExerciseFromWorkoutPlan(int exercise_id, int workoutPlan_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_WORKOUTPLAN_EXERCISES + " " +
                "WHERE " + EXERCISES_ID + " = " + exercise_id + " AND " + WORKOUTPLAN_ID + " = " + workoutPlan_id);
    }

    public void deleteWorkoutPlan(int workoutPlanId) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_WORKOUTPLAN + " " +
                "WHERE " + WORKOUTPLAN_ID + " = " + workoutPlanId);
        db.execSQL("DELETE FROM " + TABLE_WORKOUTPLAN_EXERCISES + " " +
                "WHERE " + WORKOUTPLAN_ID + " = " + workoutPlanId);
    }
}
