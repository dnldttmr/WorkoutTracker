package de.dnldttmr.workouttracker.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        //Getting the values from the exercise
        ImageView photo = (ImageView) view.findViewById(R.id.iv_photo);
        TextView name = (TextView) view.findViewById(R.id.tv_exerciseName);
        TextView muscleGroup = (TextView) view.findViewById(R.id.tv_exerciseMuscleGroup);
        TextView difficulty = (TextView) view.findViewById(R.id.tv_exerciseDifficulty);

        //Setting the values from the exericse
        name.setText(exercise.getName());

        muscleGroup.setText(exercise.getMuscle_group());

        int check_difficulty = exercise.getDifficulty();
        switch (check_difficulty) {
            case 1:
                difficulty.setText("Easy");
                difficulty.setTextColor(ContextCompat.getColor(context, R.color.green));
                break;
            case 2:
                difficulty.setText("Medium");
                difficulty.setTextColor(ContextCompat.getColor(context, R.color.yellow));
                break;
            case 3:
                difficulty.setText("Hard");
                difficulty.setTextColor(ContextCompat.getColor(context, R.color.red));
                break;
            default:
                difficulty.setText("Unset!");
                break;
        }

        String check_muscleGroup = exercise.getMuscle_group();
        switch (check_muscleGroup) {
            case "Chest/Triceps":
                photo.setImageResource(R.drawable.mg_chesttriceps);
                break;
            case "Abdominals":
                photo.setImageResource(R.drawable.mg_abs);
                break;
            case "Back/Biceps":
                photo.setImageResource(R.drawable.mg_backbiceps);
                break;
            case "Legs":
                photo.setImageResource(R.drawable.mg_quadriceps);
                break;
            case "Calves":
                photo.setImageResource(R.drawable.mg_calves);
                break;
            case "Back":
                photo.setImageResource(R.drawable.mg_calves);
                break;
            default:
                photo.setImageResource(R.drawable.no_picture);
                break;
        }

        return view;
    }
}
