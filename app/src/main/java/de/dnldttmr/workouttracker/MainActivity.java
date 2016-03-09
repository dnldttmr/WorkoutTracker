package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import de.dnldttmr.workouttracker.database.Exercise;
import de.dnldttmr.workouttracker.database.DatabaseTables;

public class MainActivity extends AppCompatActivity {

    //Variables
    private SharedPreferences prefs = null;
    private DatabaseTables exercise_table;
    private Button btn_exerciseOverview;
    private Button btn_workoutPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn_exerciseOverview = (Button) findViewById(R.id.btn_exerciseOverview);
        btn_workoutPlan = (Button) findViewById(R.id.btn_workoutPlan);

        exercise_table = new DatabaseTables(getApplicationContext());

        //check for the first run of the application
        prefs = getSharedPreferences("de.dnldttmr.workouttracker", MODE_PRIVATE);

        if (prefs.getBoolean("firstrun", true)) {
            //first run of the application
            setupDatabase();

            //set first run to false
            prefs.edit().putBoolean("firstrun", false).commit();
        }
        else {
            //do something on every run but not first run
        }

        btn_exerciseOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ExerciseOverview.class);
                startActivity(intent);
            }
        });

        btn_workoutPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WorkoutPlanOverview.class);
                startActivity(intent);
            }
        });

    }

    private void setupDatabase() {
        //setup database here
        //Exercise(String name, String muscle_group, String description, int sets, int reps, int difficulty , String photo, String video, String link)
        exercise_table.createExercise(new Exercise("Barbell Bench Press", "Chest/Triceps",
                "Lie back on a flat bench. Using a medium width grip (a grip that creates a 90-degree angle in the middle of the movement between forearm and the upper arm), lift the bar from the rack and hold it straight over you. Slowly drop the weight until die bar touches your middle chest. After a brief pause, push the bar back to the starting position. Repeat the movement for the recommended number of repetitions and place the bar back in the rack, when you are done.",
                3, 12, 2, "barbellBenchPress", "", "http://www.exrx.net/WeightExercises/PectoralSternal/BBBenchPress.html"));
        exercise_table.createExercise(new Exercise("Sit-Up", "Abdominals",
                "Lie down on the floor and secure your feet. Your legs should be bent at the knees at a 90-degree angle. Place your hands behind your head for a high amount of weight or on your belly for a light amount of weight. Flex your hips and raise your torso toward your knees. At the top of the contraction, your torso should be perpendicular to the ground. Reverse the motion but do not drop your torso on the floor. Repeat the movement until you reached the number of the recommended repetitions.",
                5, 20, 1, "situp", "", "http://www.exrx.net/WeightExercises/RectusAbdominis/BWSitUp.html"));
        exercise_table.createExercise(new Exercise("Chin-Up", "Back/Biceps",
                "Grab the pull bar with the palms facing your torso and grap closer than your shoulder width. Pull your torso up until your head is around the height of the pull bar. Concentrate on using your back and arm muscles. After a second of squeezing, slowly drop your torso back to the starting position. Repeat the movement until you reached the recommended number of repetitions",
                3, 10, 1, "chinup", "", "http://www.exrx.net/WeightExercises/LatissimusDorsi/BWUnderhandChinup.html"));
        exercise_table.createExercise(new Exercise("Leg Press", "Legs",
                "Sit on the machine with your back on the padded support. Place your feet on the platform in front of you. Grap the handels to your left and your right. Push the platform with your feet until your knees are almost fully extended. After a second of squeezing, slowly lower the platform until you reach the starting position. Repeat the movement until you reached the recommended number of repetitions",
                3, 13, 1, "legpress", "", "http://www.exrx.net/WeightExercises/Quadriceps/SLSeatedLegPress.html"));
        exercise_table.createExercise(new Exercise("Standing Calf Raises", "Calves",
                "Place your shoulders under the pads provided and position your toes facing forward. The balls of your feet should be secured on the top of the machine. Push the lever up by extending your hips and knees until your torso is standing errect. Knees should be always bend; never locked. Raise your heels as your breath out. Hold the contracted position by a second before returning to the starting position. Repeat the movement until you reach the recommended amount of repetitions.",
                3, 12, 1, "calfraises", "", "www.exrx.net/WeightExercises/Gastrocnemius/BWStandingCalfRaise.html"));
        exercise_table.createExercise(new Exercise("Deadlift", "Back",
                "Approach the bar so that it is centered over your feet. Your feet should be about hip-width apart. Bend at the hip to grip the bar at shoulder-width allowing your shoulder blades to protract. Typically, you would use an alternating grip. With your feet and your grip set, take a big breath and then lower your hips and flex the knees until your shins contact the bar. Look forward with your head. Keep your chest up and your back arched, and begin driving through the heels to move the weight upward. After the bar passes the knees aggressively pull the bar back, pulling your shoulder blades together as you drive your hips forward into the bar. Lower the bar by bending at the hips and guiding it to the floor. Repeat the movement until your reached the recommend amount of repetitions.",
                3,10, 3, "deadlift", "deadlift", "http://www.exrx.net/WeightExercises/ErectorSpinae/BBDeadlift.html"));
    }
}
