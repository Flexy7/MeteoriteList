package com.example.lukyn.meteoritesapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.OnItemClick;


public class MainActivity extends AppCompatActivity {
    static final String FILENAME = "Meteorite data";

    static CustomAdapter customAdapter;
    // @BindView(R.id.progress_bar)
    static ProgressBar mProgressBar;
    // @BindView(R.id.list_of_meteorites)
    static ListView listOfMeteorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listOfMeteorites = (ListView) findViewById(R.id.list_of_meteorites);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);


        if (FileUtils.fileExist(this, FILENAME)) {
            customAdapter = new CustomAdapter(this, FileUtils.loadDataFromFile(this, FILENAME));
            listOfMeteorites.setAdapter(customAdapter);

        } else {
            MyRetrofit.fetchDataFromInternet(this);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1);
            setAlarm(calendar.getTimeInMillis());

        }

    }

    private void setAlarm(long time) {

        //getting the alarm manager
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //creating a new intent specifying the broadcast receiver
        Intent i = new Intent(this, MyPeriodicTask.class);

        //creating a pending intent using the intent
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }

    @OnItemClick(R.id.list_of_meteorites)
    public void onItemClick(int position) {
        String latitude = customAdapter.getItem(position).getMeteoriteLatitude();
        String longitude = customAdapter.getItem(position).getMeteoriteLongitude();
        String coordinates = String.format("geo:0,0?q=" + latitude + "," + longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(coordinates));
        startActivity(intent);
    }


}



