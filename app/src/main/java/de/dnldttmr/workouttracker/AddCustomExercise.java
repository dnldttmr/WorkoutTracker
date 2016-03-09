package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.DatabaseTables;

public class AddCustomExercise extends AppCompatActivity {

    private EditText name;
    private EditText desc;
    private Button btn_add;
    private DatabaseTables databaseTables;
    private Spinner spinner_mg;
    private Spinner spinner_reps;
    private Spinner spinner_sets;
    private Spinner spinner_difficulty;
    private ArrayAdapter<CharSequence> adapter_mg;
    private ArrayAdapter<CharSequence> adapter_reps;
    private ArrayAdapter<CharSequence> adapter_sets;
    private ArrayAdapter<CharSequence> adapter_difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_exercise);

        spinner_mg = (Spinner) findViewById(R.id.spinner);
        spinner_difficulty = (Spinner) findViewById(R.id.spinnerDifficulty);
        spinner_reps = (Spinner) findViewById(R.id.spinnerReps);
        spinner_sets = (Spinner) findViewById(R.id.spinnerSets);
        name = (EditText) findViewById(R.id.et_customName);
        desc = (EditText) findViewById(R.id.et_customDescription);
        btn_add = (Button) findViewById(R.id.btn_customAdd);

        databaseTables = new DatabaseTables(getApplicationContext());

        adapter_mg = ArrayAdapter.createFromResource(this, R.array.spinner_mg, android.R.layout.simple_spinner_dropdown_item);
        spinner_mg.setAdapter(adapter_mg);
        adapter_reps = ArrayAdapter.createFromResource(this, R.array.spinner_reps, android.R.layout.simple_spinner_dropdown_item);
        spinner_reps.setAdapter(adapter_reps);
        adapter_sets = ArrayAdapter.createFromResource(this, R.array.spinner_sets, android.R.layout.simple_spinner_dropdown_item);
        spinner_sets.setAdapter(adapter_sets);
        adapter_difficulty = ArrayAdapter.createFromResource(this, R.array.spinner_difficulty, android.R.layout.simple_spinner_dropdown_item);
        spinner_difficulty.setAdapter(adapter_difficulty);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customName = name.getText().toString();
                String customDesc = desc.getText().toString();

                int position = spinner_mg.getSelectedItemPosition();
                String mg = adapter_mg.getItem(position).toString();

                position = spinner_reps.getSelectedItemPosition();
                int nReps = Integer.parseInt(adapter_reps.getItem(position).toString());

                position = spinner_sets.getSelectedItemPosition();
                int nSets = Integer.parseInt(adapter_sets.getItem(position).toString());

                position = spinner_difficulty.getSelectedItemPosition();
                int nDifficulty;
                switch (adapter_difficulty.getItem(position).toString()) {
                    case "Easy":
                        nDifficulty = 1;
                        break;
                    case "Medium":
                        nDifficulty = 2;
                        break;
                    case "Hard":
                        nDifficulty = 3;
                        break;
                    default:
                        nDifficulty = 1;
                        break;
                }

                //public Exercise(String name, String muscle_group, String description, int sets, int reps, int difficulty, String photo, String video, String link)
                databaseTables.createExercise(new Exercise(customName, mg, customDesc, nSets, nReps, nDifficulty, "", "", ""));
                Intent intent = new Intent(getBaseContext(), ExerciseOverview.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
