package com.assignment.notely.ui.viewnote

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Intent
import com.assignment.notely.NotelyApplication
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import com.assignment.notely.ui.detail.DetailViewActivity
import com.assignment.notely.ui.newnote.NewNoteActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class AdapterViewModel(var app: Application) : AndroidViewModel(app) {

    @Inject
    lateinit var db: NoteDatabase

    init {
        (app as NotelyApplication).appComponent.inject(this)
    }

    lateinit var note: Note
    var currentTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    var passTimeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun getNoteTitle() = note.title
    fun getNoteText() = note.description

    fun getNoteDate(): String {
        val today = Date()
        if (note.createdAt.day == today.day && note.createdAt.month == today.month && note.createdAt.year == note.createdAt.year)
            return currentTimeFormat.format(note.createdAt)
        else
            return passTimeFormat.format(note.createdAt)
    }

    fun onClick() {
        val intent = Intent(app, DetailViewActivity::class.java)
        intent.putExtra("noteId", note.id)
        app.startActivity(intent)
    }

    fun onFavClick() {
        Thread({
            note.markedFav = true
            db.noteDao().insertNote(note)
        }).start()
    }

    fun onStarredClick() {
        Thread({
            note.markedStar = true
            db.noteDao().insertNote(note)
        }).start()
    }

    fun deleteNote(note: Note) {
        Thread({ db.noteDao().deleteNote(note) })
    }

}