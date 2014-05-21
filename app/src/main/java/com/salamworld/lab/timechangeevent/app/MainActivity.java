package com.salamworld.lab.timechangeevent.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calculate the difference between current time and elapsedRealtime
        long difference = Util.getCurrentDifference();

        //store this difference in SharedPreference (or anywhere)
        Util util = new Util(this);
        util.setTimeDifference(difference);
    }
}
