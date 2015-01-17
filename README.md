SET_TIME Intent: calculate previous time
=============

This is an example how the previous time can be calculated in event of a "SET_TIME"-intent.
[My original answer on stackoverflow:](http://stackoverflow.com/questions/23785770/get-difference-between-old-and-new-time/23790083#23790083)
The main idea is this: You need a independent time to compare to, we can take SystemClock.elapsedRealtime() which is the time since the device booted. Now we can calculate the difference between this elapsedTime and System.currentTimeMillis(). This difference will always be constant, unless the SystemTime has changed (e.g. by the user). In this case, we can calculate the new difference and compare them to determine how much the it has changed.

Let's do it:

Calculate the current difference:
```java
public static long getCurrentDifference() {
  long elapsedTime = SystemClock.elapsedRealtime();
  long currentTime = System.currentTimeMillis();
  return currentTime - elapsedTime;
}
```
Store this difference at begining* to SharedPreferences

When SET_TIME is received, calculate the difference between the current difference and the stored difference
```java
long lastDifference = mySharedPrefs.getLastTimeDifference();

//Calculate the new difference
long currentDifference = getCurrentDifference();

//Calculate the difference of the differences
//this is the change the user made
long userChangeInMillis = lastDifference - currentDifference;

//and here we get the old time:
long previousTime = System.currentTimeMillis() + userChangeInMillis;
```
You can also make a Date out of it (new Date(previousTime)).

*To always have an initial time difference, you need three things:
- store difference to SharedPreferences when the app is opened
- do same when the device is booted (needs another BroadCastReceiver)
- save the newly calculated difference after SET_TIME has been received.

The output of my example looks like this:
```java
      Before it was Wed May 21 17:43:28 GMT+00:00 2014
      Now it is:    Wed May 21 18:43:00 GMT+00:00 2014
```
Somehow SET_TIME is received twice. Therefore there are two Log-messages and the second one shows twice the current time, because I overwrite the lastDifference with the newly calculated one.
