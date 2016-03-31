package de.dnldttmr.workouttracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.WorkoutPlan;

public class TrackWorkout extends AppCompatActivity {

    private WorkoutPlan workoutPlan;
    private DatabaseTables databaseTables;
    private List<Exercise> workoutPlanExerciseList;

    public int[] getSetsArray() {
        return setsArray;
    }

    public void setSetsArray(int[] setsArray) {
        this.setsArray = setsArray;
    }

    public int[] setsArray;
    public int[] repsArray;
    public int[] weightArray;
    private ListView lv;
    private Button btn_submit;
    private String tag = "WorkoutTracker";
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_workout);

        lv = (ListView) findViewById(R.id.lv_trackWorkoutOverview);
        btn_submit = (Button) findViewById(R.id.btn_trackWorkoutSubmit);

        databaseTables = new DatabaseTables(getApplicationContext());
        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        workoutPlan = (WorkoutPlan) intent.getSerializableExtra("workoutPlanObject");
        loadExerciseData();

        setsArray = new int[workoutPlanExerciseList.size()];
        repsArray = new int[workoutPlanExerciseList.size()];
        weightArray = new int[workoutPlanExerciseList.size()];

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correctValues = true;

                for(int i = 0; i < workoutPlanExerciseList.size(); i++) {
                    Log.d(tag, "Sets pos: " + i + " value: " + setsArray[i]);
                    Log.d(tag, "Reps pos: " + i + " value: " + repsArray[i]);
                    Log.d(tag, "Weight pos: " + i + " value: " + weightArray[i]);

                    if(setsArray[i] == 0 || repsArray[i] == 0 || weightArray[i] == 0) {
                        correctValues = false;
                    }
                }

                if(correctValues) {
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    month++;
                    Log.d(tag, day + "." + month + "." + year);

                    int workoutLogID = (int) databaseTables.createWorkoutLog(day, month, year);
                    Log.d(tag, "ID: " + workoutLogID);

                    for(int i = 0; i < workoutPlanExerciseList.size(); i++) {
                        databaseTables.addExerciseToWorkoutLog(setsArray[i], repsArray[i], weightArray[i], workoutPlanExerciseList.get(i).getId(), workoutLogID);
                    }

                    Toast.makeText(getApplicationContext(), "Your Workout has been tracked succesfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You cannot enter the number 0 for any amount of Reps, Sets or Weight!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadExerciseData() {
        workoutPlanExerciseList = databaseTables.returnExercisesForWorkoutPlan(workoutPlan.getId());

        final TrackWorkoutAdapter adapter = new TrackWorkoutAdapter(this, workoutPlanExerciseList, workoutPlan.getId());

        lv.setAdapter(adapter);
    }

    private class TrackWorkoutAdapter extends BaseAdapter {

        private Context context;
        private List<Exercise> workoutPlanExerciseList;
        private View view;
        private LayoutInflater inflater;
        private int id;
        private String tag = "WorkoutTracker";

        public TrackWorkoutAdapter(Context context, List<Exercise> workoutPlanExerciseList, int id) {
            inflater = LayoutInflater.from(context);
            this.context = context;
            this.workoutPlanExerciseList = workoutPlanExerciseList;
            this.id = id;
        }

        @Override
        public int getCount() {
            return workoutPlanExerciseList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Exercise exercise = workoutPlanExerciseList.get(position);
            final ViewHolder holder;

            view = convertView;

            if (view ==  null) {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.track_workout_exercise_list, null);
                holder.nSets = (EditText) view.findViewById(R.id.et_trackWorkoutNumberOfSets);
                holder.nReps = (EditText) view.findViewById(R.id.et_trackWorkoutNumberOfReps);
                holder.nWeight = (EditText) view.findViewById(R.id.et_trackWorkoutWeight);
                view.setTag(holder);
            }
            else {
                holder = (ViewHolder) view.getTag();
            }

            holder.ref = position;

            //Getting the values from the xml file
            TextView name = (TextView) view.findViewById(R.id.tv_trackWorkoutExerciseName);
            TextView recSets = (TextView) view.findViewById(R.id.tv_trackWorkoutRecommendedSets);
            TextView recReps = (TextView) view.findViewById(R.id.tv_trackWorkoutRecommendedReps);
            //final EditText sets = (EditText) view.findViewById(R.id.et_trackWorkoutNumberOfSets);
            //EditText reps = (EditText) view.findViewById(R.id.et_trackWorkoutNumberOfReps);
            //EditText weight = (EditText) view.findViewById(R.id.et_trackWorkoutWeight);

            //Setting values
            name.setText(exercise.getName());
            recSets.setText("" + exercise.getSets());
            recReps.setText("" + exercise.getReps());
            holder.nSets.setText("" + setsArray[position]);
            holder.nReps.setText("" + repsArray[position]);
            holder.nWeight.setText("" + weightArray[position]);

            holder.nSets.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().equals("")) {
                        //catches exception of null being not an int
                        //value will be not taken over by array
                        //instead it will stay the value from before
                    } else {
                        setsArray[holder.ref] = Integer.parseInt(s.toString());
                    }
                }
            });

            holder.nReps.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.toString().equals("")) {
                        //catches exception of null being not an int
                        //value will be not taken over by array
                        //instead it will stay the value from before
                    }
                    else {
                        repsArray[holder.ref] = Integer.parseInt(s.toString());
                    }
                }
            });
            holder.nWeight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.toString().equals("")) {
                        //catches exception of null being not an int
                        //value will be not taken over by array
                        //instead it will stay the value from before
                    }
                    else {
                        weightArray[holder.ref] = Integer.parseInt(s.toString());
                    }
                }
            });


            return view;
        }

        private class ViewHolder {
            EditText nSets;
            EditText nReps;
            EditText nWeight;
            int ref;
        }
    }
}


