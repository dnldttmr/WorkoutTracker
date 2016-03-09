package de.dnldttmr.workouttracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import de.dnldttmr.workouttracker.R;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.Exercise;

public class WorkoutPlanExercisesListView extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<Exercise> exerciseList;
    private List<Exercise> workoutPlanExerciseList;
    private View view;
    private DatabaseTables databaseTables;
    private int id;

    public WorkoutPlanExercisesListView(Context context, List<Exercise> wholeList, List<Exercise> workoutPlanExerciseList, int id) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.exerciseList = wholeList;
        this.workoutPlanExerciseList = workoutPlanExerciseList;
        databaseTables = new DatabaseTables(context);
        this.id = id;
        Toast.makeText(context, "Exercises on Plan: " + workoutPlanExerciseList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getCount() {
        return exerciseList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        //update
        workoutPlanExerciseList = databaseTables.returnExercisesForWorkoutPlan(id);

        final Exercise exercise = exerciseList.get(position);
        view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.simple_exercise_list, null);
        }

        //Getting the values from the xml file
        TextView name = (TextView) view.findViewById(R.id.tv_simpleExerciseName);
        final Button btn_change = (Button) view.findViewById(R.id.btn_changeWorkoutPlanExercise);

        //Setting the values
        name.setText(exercise.getName());

        boolean check = false;

        for(Exercise e : exerciseList) {
            for(Exercise el : workoutPlanExerciseList) {
                if(e.getName() == el.getName()) {
                    //Exercise is part of the Workout Plan
                    check = true;
                }
            }

            if(check) {
                btn_change.setText("Delete");
                btn_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseTables.deleteExerciseFromWorkoutPlan(exercise.getId(), id);
                    }
                });
            }
            else {
                btn_change.setText("Add");
                btn_change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseTables.addExerciseToWorkoutPlan(exercise.getId(), id);
                    }
                });

            }
        }

        return view;
    }
}
