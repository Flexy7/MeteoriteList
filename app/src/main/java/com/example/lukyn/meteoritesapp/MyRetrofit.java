package com.example.lukyn.meteoritesapp;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyRetrofit {

    public static void fetchDataFromInternet(final Context context) {
        if (MainActivity.listOfMeteorites != null) {
            MainActivity.mProgressBar.setVisibility(View.VISIBLE);


        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://data.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MeteoriteService client = retrofit.create(MeteoriteService.class);
        Call<List<Meteorite>> call = client.meteoriteList();

        call.enqueue(new Callback<List<Meteorite>>() {
            @Override
            public void onResponse(Call<List<Meteorite>> call, Response<List<Meteorite>> response) {

                List<Meteorite> meteorites = response.body();
                FileUtils.saveDataToFile(context, new Gson().toJson(meteorites));
                if (MainActivity.listOfMeteorites != null) {
                    MainActivity.customAdapter = new CustomAdapter(context, meteorites);
                    MainActivity.listOfMeteorites.setAdapter(MainActivity.customAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Meteorite>> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
