package com.example.lukyn.meteoritesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyPeriodicTask extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyPeriodicTask.java", "Alarm just fired");
        Toast.makeText(context, "update", Toast.LENGTH_LONG).show();
        MyRetrofit.fetchDataFromInternet(context);


    }


}