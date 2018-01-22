package com.assignment.notely.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.assignment.notely.db.entities.Note
import com.assignment.notely.db.entities.RoomConfig

/**
 * Created by vivek on 21/01/18.
 */
@Dao
interface NoteDao {

    @Query(RoomConfig.QUERY_NOTE_ALL)
    fun getNotes(): LiveData<MutableList<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Query(RoomConfig.QUERY_SINGLE_NOTE)
    fun getNote(noteId: Int): Note

    @Query(RoomConfig.QUERY_NOTE_FAV)
    fun getFavNotes(): LiveData<MutableList<Note>>

    @Query(RoomConfig.QUERY_NOTE_STARRED)
    fun getStarredNotes(): LiveData<MutableList<Note>>

    @Query(RoomConfig.QUERY_NOTE_STARRED_FAV)
    fun getFavAndStarredNotes(): LiveData<MutableList<Note>>

    @Delete
    fun deleteNote(note: Note)

}