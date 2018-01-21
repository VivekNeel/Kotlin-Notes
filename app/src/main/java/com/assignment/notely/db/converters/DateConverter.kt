package com.assignment.notely.db.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by vivek on 21/01/18.
 */
class DateConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimeStamp(date: Date?): Long? = date?.time
}