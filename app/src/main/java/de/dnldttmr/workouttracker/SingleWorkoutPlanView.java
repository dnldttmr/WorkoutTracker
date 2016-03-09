package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import de.dnldttmr.workouttracker.adapter.WorkoutPlanExercisesListView;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.WorkoutPlan;

public class SingleWorkoutPlanView extends AppCompatActivity {

    private WorkoutPlan workoutPlan;
    private EditText et_workoutPlanName;
    private Button btn_edit;
    private DatabaseTables databaseTables;
    private ListView lv;
    private List<Exercise> exerciseList;
    private List<Exercise> workoutPlanExerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_workout_plan_view);

        btn_edit = (Button) findViewById(R.id.btn_workoutPlanEdit);
        et_workoutPlanName = (EditText) findViewById(R.id.et_workoutPlanEdit);
        lv = (ListView) findViewById(R.id.lv_workoutPlanExercisesOverview);

        databaseTables = new DatabaseTables(getApplicationContext());

        Intent intent = getIntent();
        workoutPlan = (WorkoutPlan) intent.getSerializableExtra("workoutPlanObject");

        et_workoutPlanName.setText((CharSequence) workoutPlan.getName());

        loadExerciseData();

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_workoutPlanName.getText().toString();
                databaseTables.editWorkoutPlanName(workoutPlan.getId(), name);
            }
        });
    }

    private void loadExerciseData() {
        exerciseList = databaseTables.getAllExercises();
        workoutPlanExerciseList = databaseTables.returnExercisesForWorkoutPlan(workoutPlan.getId());

        final WorkoutPlanExercisesListView adapter = new WorkoutPlanExercisesListView(this, exerciseList, workoutPlanExerciseList, workoutPlan.getId());

        lv.setAdapter(adapter);
    }
}
