package de.dnldttmr.workouttracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

import de.dnldttmr.workouttracker.adapter.ExerciseListAdapter;
import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.ExerciseTable;

public class ExerciseOverview extends AppCompatActivity {

    private ListView lv;
    private ExerciseTable exerciseTable;
    private List<Exercise> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_overview);

        lv = (ListView) findViewById(R.id.lv_exerciseOverview);
        exerciseTable = new ExerciseTable(getApplicationContext());

        loadExerciseData();
    }

    private void loadExerciseData() {
        exerciseList = exerciseTable.getAllExercises();

        final ExerciseListAdapter adapter = new ExerciseListAdapter(this, exerciseList);
        lv.setAdapter(adapter);
    }
}
