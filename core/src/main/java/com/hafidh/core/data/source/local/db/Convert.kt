package com.hafidh.core.data.source.local.db

import androidx.room.TypeConverter
import com.hafidh.core.data.source.remote.network.response.Source


class Convert {

    @TypeConverter
    fun convertToSource(input: String): Source = Source(input, input)

    @TypeConverter
    fun convertToString(input: Source): String = input.name

}