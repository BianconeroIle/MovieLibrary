package com.example.ilijaangeleski.careem.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.ilijaangeleski.careem.model.GenreDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ilija Angeleski on 1/15/2018.
 */

public class AppPreference {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        this.context = context;
        this.sp = context.getSharedPreferences("Showbox.preference", Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }

    public List<GenreDTO> getMovieGenres() {
        Log.d("AppPreferences", "getMovieGenres()");
        Gson gson = new Gson();
        String jsonString = sp.getString("app.movieGenres", "");
        if (!"".equals(jsonString)) {
            Type collectionType = new TypeToken<List<GenreDTO>>() {
            }.getType();

            List<GenreDTO> listObj = gson.fromJson(jsonString, collectionType);
            return listObj;
        }
        return Collections.emptyList();
    }
}
