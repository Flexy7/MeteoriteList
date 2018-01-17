package com.example.lukyn.meteoritesapp;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.List;

public class FileUtils {


    public static void saveDataToFile(Context context, String jsonString) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(MainActivity.FILENAME, Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonString);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public static List<Meteorite> loadDataFromFile(Context context, String fileName) {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            ret = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            Log.e("FileUtils.java", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("FileUtils.java", "Can not read file: " + e.toString());
        }

        Type type = new TypeToken<List<Meteorite>>() {
        }.getType();
        List<Meteorite> inpList = new Gson().fromJson(ret, type);
        return inpList;
    }

    public static boolean fileExist(Context context, String fileName) {
        File file = context.getFileStreamPath(fileName);
        return file.exists();
    }

}
