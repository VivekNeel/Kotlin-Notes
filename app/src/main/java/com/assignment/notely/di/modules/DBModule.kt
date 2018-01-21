package com.assignment.notely.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.RoomConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vivek on 21/01/18.
 */
@Module
class DBModule {


    @Provides
    @Singleton
    fun provideDB(context: Context): NoteDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, RoomConfig.NAME_DB).build()
}