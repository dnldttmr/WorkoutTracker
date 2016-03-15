package de.dnldttmr.workouttracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import de.dnldttmr.workouttracker.adapter.TrackWorkoutPlanAdapter;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.WorkoutPlan;

public class TrackWorkoutPlanOverview extends AppCompatActivity {

    private ListView lv;
    private DatabaseTables databaseTables;
    private List<WorkoutPlan> workoutPlanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_workout_plan_overview);

        lv = (ListView) findViewById(R.id.lv_trackWorkoutPlanOverview);

        databaseTables = new DatabaseTables(getApplicationContext());

        loadData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "You clicked on item number " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        workoutPlanList = databaseTables.getAllWorkoutPlans();

        final TrackWorkoutPlanAdapter adapter = new TrackWorkoutPlanAdapter(this, workoutPlanList);

        lv.setAdapter(adapter);
    }
}
