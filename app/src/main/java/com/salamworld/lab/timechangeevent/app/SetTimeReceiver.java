package com.salamworld.lab.timechangeevent.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;


public class SetTimeReceiver extends BroadcastReceiver {
    private static final String TAG = "SetTimeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //get the last difference
        Util util = new Util(context);
        long lastDifference = util.getLastTimeDifference();

        //Calculate the new difference
        long currentDifference = Util.getCurrentDifference();

        //Calculate the difference of the differences
        //this is the change the user made
        long userChangeInMillis = lastDifference - currentDifference;

        //and here we get the old time:
        long previousTime = System.currentTimeMillis() + userChangeInMillis;

        //store the new difference as current difference for the next time
        util.setLastTimeDifference(currentDifference);

        //we print the dates to check the result
        Log.d(TAG, "Before it was " +
                new Date(previousTime).toString()
                + " \n Now it is: " + new Date().toString());
    }
}