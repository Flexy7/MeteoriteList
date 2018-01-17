package com.example.lukyn.meteoritesapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;



public interface MeteoriteService {
    @GET("/resource/y77d-th95.json?$where=year%20between%20%272010-01-10T12:00:00%27%20and%20%272017-01-10T14:00:00%27&$order=mass%20DESC&$$app_token=5xaRvbjo5mE8I4z7CffGRbWTo")
    Call<List<Meteorite>> meteoriteList();
}