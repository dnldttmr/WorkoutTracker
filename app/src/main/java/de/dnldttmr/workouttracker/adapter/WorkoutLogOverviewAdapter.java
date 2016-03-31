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

public class WorkoutLogOverviewAdapter extends BaseAdapter {

    private Context context;
    private List<Exercise> exerciseList;
    private View view;
    private LayoutInflater inflater;
    private String tag = "WorkoutTracker";

    public WorkoutLogOverviewAdapter(Context context, List<Exercise> exerciseList) {
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

        if(view == null) {
            view = inflater.inflate(R.layout.workoutlog_exercise_list, null);
        }

        TextView name = (TextView) view.findViewById(R.id.tv_tv_workoutLogName);
        TextView sets = (TextView) view.findViewById(R.id.tv_workoutLogSets);
        TextView reps = (TextView) view.findViewById(R.id.tv_workoutLogReps);
        TextView weight = (TextView) view.findViewById(R.id.tv_workoutLogWeight);

        name.setText("" + exercise.getName());
        sets.setText("" + exercise.getSets());
        reps.setText("" + exercise.getReps());
        weight.setText("" + exercise.getDifficulty());

        return view;
    }
}
