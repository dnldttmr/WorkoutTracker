package de.dnldttmr.workouttracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTable extends SQLiteOpenHelper{

    //Logcat
    private static final String LOG = ExerciseTable.class.getName();

    //Db Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "workoutDB";

    //Table Names
    private static final String TABLE_EXERCISES = "exercises";

    //Column names for TABLE_EXERCISES
    private static final String EXERCISES_ID = "exercises_id";
    private static final String EXERCISES_NAME = "name";
    private static final String EXERCISES_MUSCLEGROUP = "muscle_group";
    private static final String EXERCISES_DESC = "description";
    private static final String EXERCISES_SETS = "sets";
    private static final String EXERCISES_REPS = "reps";
    private static final String EXERCISES_PHOTO = "photo";
    private static final String EXERICSES_VIDEO = "video";
    private static final String EXERCISES_LINK = "link";

    //Column name Array
    private String[] columns = {EXERCISES_ID, EXERCISES_NAME, EXERCISES_MUSCLEGROUP, EXERCISES_DESC, EXERCISES_SETS,
        EXERCISES_REPS, EXERCISES_PHOTO, EXERICSES_VIDEO, EXERCISES_LINK};
    //Create tables
    //Exercises
    private static final String CREATE_TABLE_EXERCISES =
            "CREATE TABLE " + TABLE_EXERCISES
                    + "(" + EXERCISES_ID + " INTEGER PRIMARY KEY, "
                    + EXERCISES_NAME + " TEXT, "
                    + EXERCISES_MUSCLEGROUP + " TEXT, "
                    + EXERCISES_DESC + " TEXT, "
                    + EXERCISES_SETS + " INTEGER, "
                    + EXERCISES_REPS + " INTEGER, "
                    + EXERCISES_PHOTO + " TEXT, "
                    + EXERICSES_VIDEO + " TEXT, "
                    + EXERCISES_LINK + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);

        onCreate(db);
    }

    //Constructor
    public ExerciseTable(Context context) {
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
        values.put(EXERCISES_PHOTO, exercise.getPhoto());
        values.put(EXERICSES_VIDEO, exercise.getVideo());
        values.put(EXERCISES_LINK, exercise.getLink());

        long exercise_id = db.insert(TABLE_EXERCISES, null, values);
        return exercise_id;
    }

    public List<Exercise> getAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Exercise> exercises = new ArrayList<Exercise>();

        Cursor cursor = db.query(TABLE_EXERCISES, columns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Exercise exercise = new Exercise();
            exercise.setId(Integer.parseInt(cursor.getString(0)));
            exercise.setName(cursor.getString(1));
            exercise.setMuscle_group(cursor.getString(2));
            exercise.setDescription(cursor.getString(3));
            exercise.setSets(Integer.parseInt(cursor.getString(4)));
            exercise.setReps(Integer.parseInt(cursor.getString(5)));
            exercise.setPhoto(cursor.getString(6));
            exercise.setVideo(cursor.getString(7));
            exercise.setLink(cursor.getString(8));

            exercises.add(exercise);

            cursor.moveToNext();
        }

        cursor.close();

        return exercises;
    }
}
