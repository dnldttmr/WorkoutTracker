package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import de.dnldttmr.workouttracker.adapter.ExerciseListAdapter;
import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.DatabaseTables;

public class ExerciseOverview extends AppCompatActivity {

    private ListView lv;
    private DatabaseTables databaseTables;
    private List<Exercise> exerciseList;
    private EditText filter;
    private Button btn_addCustomExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_overview);

        filter = (EditText) findViewById(R.id.et_filter);
        lv = (ListView) findViewById(R.id.lv_exerciseOverview);
        btn_addCustomExercise = (Button) findViewById(R.id.btn_addCustomExercise);

        databaseTables = new DatabaseTables(getApplicationContext());

        loadExerciseData();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Check on which item the user clicked
                for (Exercise e : exerciseList) {
                    if (e.getName() == exerciseList.get(position).getName()) {
                        //Get the information
                        Intent intent = new Intent(getBaseContext(), SingleExerciseView.class);

                        /*intent.putExtra("id", e.getId());
                        intent.putExtra("name", e.getName());
                        intent.putExtra("muscleGroup", e.getMuscle_group());
                        intent.putExtra("desc", e.getDescription());
                        intent.putExtra("sets", e.getSets());
                        intent.putExtra("reps", e.getReps());
                        intent.putExtra("difficulty", e.getDifficulty());
                        intent.putExtra("photo", e.getPhoto());
                        intent.putExtra("video", e.getVideo());
                        intent.putExtra("link", e.getLink());
                        startActivity(intent);*/

                        intent.putExtra("exerciseObject", e);
                        startActivity(intent);
                    }
                }
            }
        });

        btn_addCustomExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddCustomExercise.class);
                startActivity(intent);
            }
        });
    }

    private void loadExerciseData() {
        exerciseList = databaseTables.getAllExercises();

        final ExerciseListAdapter adapter = new ExerciseListAdapter(this, exerciseList);
        lv.setAdapter(adapter);

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //apply the filter from the text
                adapter.filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
