package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import de.dnldttmr.workouttracker.adapter.WorkoutPlanListAdapter;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.WorkoutPlan;

public class WorkoutPlanOverview extends AppCompatActivity {

    private ListView lv;
    private DatabaseTables workoutPlanTable;
    private List<WorkoutPlan> workoutPlanList;
    private Button btn_addNewWorkoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan_overview);

        lv = (ListView) findViewById(R.id.lv_workoutPlanOverview);

        workoutPlanTable = new DatabaseTables(getApplicationContext());
        btn_addNewWorkoutPlan = (Button) findViewById(R.id.btn_addWorkoutPlan);

        loadWorkoutPlanData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (WorkoutPlan w : workoutPlanList) {
                    //getting the ID since workout plans can have the same name
                    if (w.getId() == workoutPlanList.get(position).getId()) {
                        Intent intent = new Intent(getBaseContext(), SingleWorkoutPlanView.class);
                        intent.putExtra("workoutPlanObject", w);
                        startActivity(intent);
                    }
                }
            }
        });

        btn_addNewWorkoutPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutPlanTable.createWorkoutPlan();
                loadWorkoutPlanData();
            }
        });
    }

    private void loadWorkoutPlanData() {
        workoutPlanList = workoutPlanTable.getAllWorkoutPlans();

        final WorkoutPlanListAdapter adapter = new WorkoutPlanListAdapter(this, workoutPlanList);

        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWorkoutPlanData();
    }
}
