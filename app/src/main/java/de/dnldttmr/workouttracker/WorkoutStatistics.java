package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.dnldttmr.workouttracker.adapter.WorkoutLogOverviewAdapter;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.Exercise;

public class WorkoutStatistics extends AppCompatActivity {

    private DatabaseTables databaseTables;
    private int day;
    private int month;
    private int year;
    private ListView lv;
    private List<Exercise> exerciseList;
    private String tag = "WorkoutTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_statistics);

        lv = (ListView) findViewById(R.id.lv_workoutLogOverview);
        exerciseList = new ArrayList<Exercise>();
        databaseTables = new DatabaseTables(getApplicationContext());

        Intent intent = getIntent();
        day = intent.getIntExtra("day", 0);
        month = intent.getIntExtra("month", 0);
        year = intent.getIntExtra("year", 0);

        month++;
        year+= 1900;

        exerciseList = databaseTables.getExercisesForDate(day, month, year);

        Log.d(tag, "ExerciseList Size: " + exerciseList.size());
        Log.d(tag,"Date: " + day + "." + month + "." + year);

        if(exerciseList.size() == 0) {
            Toast.makeText(getApplicationContext(), "You didn´t complete any workouts on this date!", Toast.LENGTH_LONG).show();
            finish();
        }
        else {
            final WorkoutLogOverviewAdapter adapter = new WorkoutLogOverviewAdapter(this, exerciseList);

            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int exerciseID = exerciseList.get(position).getId();

                Intent intent = new Intent(getApplicationContext(), ExerciseStatistic.class);
                intent.putExtra("ID", exerciseID);

                startActivity(intent);
            }
        });
    }
}
