package de.dnldttmr.workouttracker;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.dnldttmr.workouttracker.database.DatabaseTables;
import de.dnldttmr.workouttracker.database.WorkoutLog;

public class WorkoutCalendar extends AppCompatActivity {

    private CaldroidFragment caldroidFragment;
    private DatabaseTables databaseTables;
    private List<de.dnldttmr.workouttracker.database.WorkoutLog> workoutLogsList;
    private ColorDrawable light_red;
    private String tag = "WorkoutTracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_log);

        workoutLogsList = new ArrayList<WorkoutLog>();
        databaseTables = new DatabaseTables(getApplicationContext());
        workoutLogsList = databaseTables.getAllWorkoutLogs();

        light_red = new ColorDrawable(getResources().getColor(R.color.caldroid_light_red));

        caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        caldroidFragment.setCaldroidListener(listener);

        Log.d(tag, "WorkoutLogList size: " + workoutLogsList.size());

        for(de.dnldttmr.workouttracker.database.WorkoutLog workoutLog: workoutLogsList) {
            int day = workoutLog.getDay();
            int month = workoutLog.getMonth();
            int year = workoutLog.getYear();
            year -= 1900;
            month--;

            //Log.d(tag, "Date: " + day + "." + month + "." + year);

            Date date = new Date(year, month, day);
            caldroidFragment.setBackgroundDrawableForDate(light_red, date);
        }

        //caldroidFragment.setBackgroundDrawableForDate(light_red, new Date(116, 3, 12));

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();
    }

    final CaldroidListener listener = new CaldroidListener() {

        @Override
        public void onSelectDate(Date date, View view) {
            Intent intent = new Intent(getApplicationContext(), WorkoutStatistics.class);
            intent.putExtra("day", date.getDate());
            intent.putExtra("month", date.getMonth());
            intent.putExtra("year", date.getYear());

            startActivity(intent);
        }

        @Override
        public void onChangeMonth(int month, int year) {

        }

        @Override
        public void onLongClickDate(Date date, View view) {

        }

        @Override
        public void onCaldroidViewCreated() {

        }

    };
}
