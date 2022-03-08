package com.example.movietime.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun stringToListOfInts(value: String): List<Int>  {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listOfStringsToString(list: List<Int>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}