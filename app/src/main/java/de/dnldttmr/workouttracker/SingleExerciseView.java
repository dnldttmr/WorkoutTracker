package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleExerciseView extends AppCompatActivity {

    //Layout variables
    private TextView tv_name;
    private TextView tv_muscleGroup;
    private TextView tv_description;
    private ImageView iv_muscleGroup;
    private TextView tv_link;

    //Content variables
    private int id;
    private String name;
    private String muscleGroup;
    private String description;
    private int sets;
    private int reps;
    private int difficulty;
    private String photo;
    private String video;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_exercise_view);

        //Setting the IDs
        tv_name = (TextView) findViewById(R.id.tv_singleName);
        tv_muscleGroup = (TextView) findViewById(R.id.tv_singleMuscleGroup);
        tv_description = (TextView) findViewById(R.id.tv_singleDescription);
        iv_muscleGroup = (ImageView) findViewById(R.id.iv_singleMuscleGroup);
        tv_link = (TextView) findViewById(R.id.tv_singleLink);

        //Getting the content
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("name");
        muscleGroup = intent.getStringExtra("muscleGroup");
        description = intent.getStringExtra("desc");
        sets = intent.getIntExtra("sets", 3);
        reps = intent.getIntExtra("reps", 12);
        difficulty = intent.getIntExtra("difficulty", 2);
        photo = intent.getStringExtra("photo");
        video = intent.getStringExtra("video");
        link = intent.getStringExtra("link");

        //Filling the fields with the content
        tv_name.setText(name);

        tv_muscleGroup.setText(muscleGroup);

        tv_description.setText(description);

        switch (muscleGroup) {
            case "Chest/Triceps":
                iv_muscleGroup.setImageResource(R.drawable.mg_chesttriceps);
                break;
            case "Abdominals":
                iv_muscleGroup.setImageResource(R.drawable.mg_abs);
                break;
            case "Back/Biceps":
                iv_muscleGroup.setImageResource(R.drawable.mg_backbiceps);
                break;
            case "Legs":
                iv_muscleGroup.setImageResource(R.drawable.mg_quadriceps);
                break;
            case "Calves":
                iv_muscleGroup.setImageResource(R.drawable.mg_calves);
                break;
            default:
                iv_muscleGroup.setImageResource(R.drawable.no_picture);
                break;
        }

        tv_link.setClickable(true);
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        String htmlLink = "<a href='" + link + "'>Check exercise online</a>";
        tv_link.setText(Html.fromHtml(htmlLink));
    }
}
