package com.example.movietime.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class GenreConverters {
    @TypeConverter
    fun stringToListOfInts(value: String): List<Genre>  {
        val listType: Type = object : TypeToken<ArrayList<Genre?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listOfStringsToString(list: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}