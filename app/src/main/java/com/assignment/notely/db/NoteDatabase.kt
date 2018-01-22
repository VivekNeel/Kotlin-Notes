package com.assignment.notely.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.assignment.notely.db.dao.NoteDao
import com.assignment.notely.db.entities.Note

/**
 * Created by vivek on 21/01/18.
 */
@Database(entities = arrayOf(Note::class), version = 3)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}