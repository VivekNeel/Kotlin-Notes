package com.assignment.notely.ui.viewnote

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import android.view.View
import com.assignment.notely.NotelyApplication
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import com.assignment.notely.ui.newnote.NewNoteActivity
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class NoteViewModel(var app: Application) : AndroidViewModel(app) {

    private var notes: LiveData<MutableList<Note>>? = null

    @Inject
    lateinit var db: NoteDatabase

    init {
        (app as NotelyApplication).appComponent.inject(this)

    }

    fun getFav(): LiveData<MutableList<Note>> =
            db.noteDao().getFavNotes()

    fun getStarred(): LiveData<MutableList<Note>> = db.noteDao().getStarredNotes()

    fun getFavAndStarredNotes(): LiveData<MutableList<Note>> = db.noteDao().getFavAndStarredNotes()


    fun getNotes(): LiveData<MutableList<Note>> {
        if (notes == null) {
            notes = MutableLiveData<MutableList<Note>>()
            loadNotes()
        }

        return notes!!
    }

    fun loadNotes() {
        notes = db.noteDao().getNotes()

    }

    fun fabClick() {
        app.startActivity(Intent(app, NewNoteActivity::class.java))
    }


}