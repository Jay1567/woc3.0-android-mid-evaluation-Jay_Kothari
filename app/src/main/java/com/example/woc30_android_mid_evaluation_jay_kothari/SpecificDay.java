package com.example.woc30_android_mid_evaluation_jay_kothari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpecificDay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_day);

        Intent intent = getIntent();

        ((TextView)findViewById(R.id.txtDay)).setText(intent.getStringExtra(MainActivity.DAY));
        ListView timeTable = findViewById(R.id.listViewTimeTable);

        ArrayList<ActivitySchedule> temp = (ArrayList<ActivitySchedule>)intent.getSerializableExtra(MainActivity.TIME_TABLE);

        if(temp.size() > 0) {
            ArrayAdapter<ActivitySchedule> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_text_view, temp);
            timeTable.setAdapter(arrayAdapter);
        }
        else{
            ArrayList<String> t = new ArrayList<>();
            t.add("Nothing for " + intent.getStringExtra(MainActivity.DAY));
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_text_view, t);
            timeTable.setAdapter(arrayAdapter);
        }

        timeTable.addFooterView(new View(this));
    }
}