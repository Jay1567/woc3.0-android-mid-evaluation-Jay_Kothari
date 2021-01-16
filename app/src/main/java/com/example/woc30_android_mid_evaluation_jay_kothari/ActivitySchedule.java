package com.example.woc30_android_mid_evaluation_jay_kothari;

import java.io.Serializable;
import java.time.LocalTime;

public class ActivitySchedule implements Serializable {
    //Letting the Data Types be default so they are accessible within the package
    String activityName;
    LocalTime from, to;

    public ActivitySchedule(String activityName, LocalTime from, LocalTime to) {
        this.activityName = activityName;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from.toString() + " - " + to.toString() + "\t " + activityName;
    }
}
