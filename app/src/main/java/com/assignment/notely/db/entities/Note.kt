package com.assignment.notely.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.assignment.notely.db.converters.DateConverter
import java.util.*

/**
 * Created by vivek on 21/01/18.
 */
@Entity(tableName = RoomConfig.NAME_TABLE_NOTE)
@TypeConverters(DateConverter::class)
class Note {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var createdAt: Date = Date()
    var markedFav : Boolean  = false
}