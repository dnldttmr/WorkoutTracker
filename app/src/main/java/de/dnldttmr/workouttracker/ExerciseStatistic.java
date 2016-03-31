package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import de.dnldttmr.workouttracker.database.DatabaseTables;

public class ExerciseStatistic extends AppCompatActivity {

    private int exerciseID;
    private DatabaseTables databaseTables;
    private String tag = "WorkoutTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_statistic);

        Intent intent = getIntent();
        exerciseID = intent.getIntExtra("ID", 0);

        Log.d(tag, "" + exerciseID);
    }
}
