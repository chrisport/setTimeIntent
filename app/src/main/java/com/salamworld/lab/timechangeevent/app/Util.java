package com.salamworld.lab.timechangeevent.app;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class Util {
    SharedPreferences prefs;

    Util(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public void setLastTimeDifference(long millis) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("BOOT_TIME", millis);
        editor.commit();
    }

    public long getLastTimeDifference() {
        return prefs.getLong("BOOT_TIME", -1);
    }

    /**
     * Calculates the difference between System.currentTimeMillis and
     * SystemClock.elapsedRealtime
     * @return the calculated difference
     */
    public static long getCurrentDifference() {
        long elapsedTime = SystemClock.elapsedRealtime();
        long currentTime = System.currentTimeMillis();
        return currentTime - elapsedTime;
    }
}
