package de.dnldttmr.workouttracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.dnldttmr.workouttracker.R;
import de.dnldttmr.workouttracker.database.Exercise;

public class ExerciseListAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private List<Exercise> exerciseList;
    private View view;

    public ExerciseListAdapter(Context context, List<Exercise> exerciseList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.exerciseList = exerciseList;
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
        Exercise exercise = exerciseList.get(position);
        view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.exercise_list_layout, null);
        }
        TextView name = (TextView) view.findViewById(R.id.tv_exerciseName);
        TextView muscleGroup = (TextView) view.findViewById(R.id.tv_exerciseMuscleGroup);

        name.setText(exercise.getName());
        muscleGroup.setText(exercise.getMuscle_group());

        return view;
    }
}
