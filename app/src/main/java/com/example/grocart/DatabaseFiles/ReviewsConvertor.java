package com.example.grocart.DatabaseFiles;

import androidx.room.TypeConverter;

import com.example.grocart.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReviewsConvertor {
    @TypeConverter
    public String reviewToJson(ArrayList<Review> reviews){
        Gson gson = new Gson();
        return gson.toJson(reviews);
    }

    @TypeConverter
    public ArrayList<Review> jsonToReview(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Review>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
