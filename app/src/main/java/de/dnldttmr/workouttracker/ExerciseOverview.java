package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Check on which item the user clicked
                for(Exercise e : exerciseList) {
                    if(e.getName() == exerciseList.get(position).getName()) {
                        //Get the information
                        Intent intent = new Intent(getBaseContext(), SingleExerciseView.class);
                        intent.putExtra("id", e.getId());
                        intent.putExtra("name", e.getName());
                        intent.putExtra("muscleGroup", e.getMuscle_group());
                        intent.putExtra("desc", e.getDescription());
                        intent.putExtra("sets", e.getSets());
                        intent.putExtra("reps", e.getReps());
                        intent.putExtra("difficulty", e.getDifficulty());
                        intent.putExtra("photo", e.getPhoto());
                        intent.putExtra("video", e.getVideo());
                        intent.putExtra("link", e.getLink());
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void loadExerciseData() {
        exerciseList = exerciseTable.getAllExercises();

        final ExerciseListAdapter adapter = new ExerciseListAdapter(this, exerciseList);
        lv.setAdapter(adapter);
    }
}
