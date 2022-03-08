package br.com.dbdinfos.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromListToString(value: List<MainDTODBLocal>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MainDTODBLocal>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun fromStringToList(value: String): List<MainDTODBLocal> {
        val gson = Gson()
        val type = object : TypeToken<List<MainDTODBLocal>>() {}.type
        return gson.fromJson(value, type)
    }
}