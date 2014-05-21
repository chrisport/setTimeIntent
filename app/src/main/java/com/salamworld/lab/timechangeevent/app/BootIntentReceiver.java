package com.salamworld.lab.timechangeevent.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This part is untested. On Boot of the device, we want to calculate
 * the time difference as initial values for future calculations. Same thing happens
 * when you start the App
 */
public class BootIntentReceiver extends BroadcastReceiver {
    private static final String TAG = "BootIntentReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        long difference = Util.getCurrentDifference();

        //store the new difference as current difference for the next time
        Util util = new Util(context);
        util.setLastTimeDifference(difference);
    }
}