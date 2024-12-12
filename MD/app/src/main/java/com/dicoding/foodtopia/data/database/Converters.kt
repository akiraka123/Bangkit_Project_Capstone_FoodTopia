package com.dicoding.foodtopia.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromList(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMap(value: Map<String, String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMap(value: String?): Map<String, String>? {
        val mapType = object : TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(value, mapType)
    }
}
