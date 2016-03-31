package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.Exercise;

public class ExerciseStatistic extends AppCompatActivity {

    private int exerciseID;
    private DatabaseTables databaseTables;
    private String tag = "WorkoutTracker";
    private List<Exercise> exerciseList;
    private LineChart lineChart;
    private ArrayList<Entry> yVals;
    private ArrayList<String> xVals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_statistic);

        //Initialise Variables
        exerciseList = new ArrayList<Exercise>();
        yVals = new ArrayList<Entry>();
        xVals = new ArrayList<String>();
        databaseTables = new DatabaseTables(getApplicationContext());

        Intent intent = getIntent();
        exerciseID = intent.getIntExtra("ID", 0);

        Log.d(tag, "Exercise ID: " + exerciseID);

        //load and create view
        loadExerciseData();
        createStatistics();
    }

    private void loadExerciseData() {
        exerciseList = databaseTables.getExercisesFromID(exerciseID);
        Log.d("WorkoutTracker", "ListSize: " + exerciseList.size());
    }

    private void createStatistics() {
        lineChart = (LineChart) findViewById(R.id.linechart);

        for(int i = 0; i < exerciseList.size(); i++) {
            yVals.add(new Entry(exerciseList.get(i).getDifficulty(), i));
            xVals.add("Day " + (i + 1));
        }

        LineDataSet dataSet = new LineDataSet(yVals, "Weight");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData lineData = new LineData(xVals, dataSet);
        lineChart.setData(lineData);
        lineChart.setDescription("Your lifted weight seperated by days");
        lineChart.invalidate();
    }
}
