package de.dnldttmr.workouttracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import de.dnldttmr.workouttracker.R;
import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.WorkoutPlan;

public class TrackWorkoutPlanAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<WorkoutPlan> workoutPlanList;
    private View view;
    private DatabaseTables databaseTables;

    public TrackWorkoutPlanAdapter(Context context, List<WorkoutPlan> workoutPlanList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.workoutPlanList = workoutPlanList;
        databaseTables = new DatabaseTables(context);
    }

    @Override
    public int getCount() {
        return workoutPlanList.size();
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
        final WorkoutPlan workoutPlan = workoutPlanList.get(position);
        view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.trackworkoutplan_overview, null);
        }

        //Getting the values from the workoutplan
        TextView name = (TextView) view.findViewById(R.id.tv_workoutPlanName2);

        //set values
        name.setText(workoutPlan.getName());

        return view;
    }
}
