package com.example.woc30_android_mid_evaluation_jay_kothari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;


/*
    Using Class to store details of classes.
    Currently hardcoding the values. But can use JSON or other kind of files to load the timetable and add or delete classes too.
 */

public class MainActivity extends AppCompatActivity {
    Hashtable <String, ArrayList<ActivitySchedule>> timeTable = new Hashtable<>();

    public static final String DAY = "com.JayKothari.woc3_midEvaluation.DAY";
    public static final String TIME_TABLE = "com.JayKothari.woc3_midEvaluation.TIME_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hardcoding the timetable
        //Monday
        ArrayList<ActivitySchedule> temp1 = new ArrayList<>();
        temp1.add(new ActivitySchedule("Computer Networks", LocalTime.parse("10:15"), LocalTime.parse("11:30")));
        temp1.add(new ActivitySchedule("System Programming", LocalTime.parse("12:00"), LocalTime.parse("13:15")));
        timeTable.put("Monday", temp1);


        //Tuesday
        ArrayList<ActivitySchedule> temp2 = new ArrayList<>();
        temp2.add(new ActivitySchedule("Object Oriented Programming", LocalTime.parse("08:30"), LocalTime.parse("09:45")));
        temp2.add(new ActivitySchedule("Web Programming", LocalTime.parse("12:00"), LocalTime.parse("13:15")));
        timeTable.put("Tuesday", temp2);

        //Wednesday
        ArrayList<ActivitySchedule> temp3 = new ArrayList<>();
        temp3.add(new ActivitySchedule("System Programming", LocalTime.parse("08:30"), LocalTime.parse("09:45")));
        timeTable.put("Wednesday", temp3);

        //Thursday
        ArrayList<ActivitySchedule> temp4 = new ArrayList<>();
        temp4.add(new ActivitySchedule("Object Oriented Programming", LocalTime.parse("08:30"), LocalTime.parse("09:45")));
        temp4.add(new ActivitySchedule("Software Engineering", LocalTime.parse("10:15"), LocalTime.parse("11:30")));
        temp4.add(new ActivitySchedule("Computer Networks", LocalTime.parse("12:00"), LocalTime.parse("13:15")));
        timeTable.put("Thursday", temp4);

        //Friday
        ArrayList<ActivitySchedule> temp5 = new ArrayList<>();
        temp5.add(new ActivitySchedule("Web Programming", LocalTime.parse("08:30"), LocalTime.parse("09:45")));
        temp5.add(new ActivitySchedule("Software Engineering", LocalTime.parse("10:15"), LocalTime.parse("11:30")));
        timeTable.put("Friday", temp5);

        //Saturday
        timeTable.put("Saturday", new ArrayList<ActivitySchedule>());

        //Displaying the next upcoming activity of the day
        showUpcoming();
    }

    private void showUpcoming(){
        Date currentTime = Calendar.getInstance().getTime();
        ArrayList<ActivitySchedule> temp = new ArrayList<>();

        switch(currentTime.getDay()){
            case 1:
                temp = timeTable.get("Monday");
                break;
            case 2:
                temp = timeTable.get("Tuesday");
                break;
            case 3:
                temp = timeTable.get("Wednesday");
                break;
            case 4:
                temp = timeTable.get("Thursday");
                break;
            case 5:
                temp = timeTable.get("Friday");
                break;
            case 6:
                temp = timeTable.get("Saturday");
                break;
        }

        LocalTime t = LocalTime.parse(new SimpleDateFormat("HH:m").format(currentTime));
        for(ActivitySchedule a : temp){
            if(t.compareTo(a.from)<=0){
                ((TextView)findViewById(R.id.txtUpcomingMessage)).setText(a.toString());
                return;
            }
        }
        ((TextView)findViewById(R.id.txtUpcomingMessage)).setText("No more classes for today");
    }

    public void showTimeTable(View view){
        Intent intent = new Intent(this, SpecificDay.class);
        String selectedDay = ((Button)view).getTag().toString();

        intent.putExtra(DAY, selectedDay);
        ArrayList<ActivitySchedule> temp = timeTable.get(selectedDay);
        intent.putExtra(TIME_TABLE, temp);

        startActivity(intent);
    }
}