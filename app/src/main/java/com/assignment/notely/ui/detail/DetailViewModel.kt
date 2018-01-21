package com.assignment.notely.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.assignment.notely.NotelyApplication
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class DetailViewModel(var app: Application, var id: Int = -1) : AndroidViewModel(app) {

    @Inject
    lateinit var db: NoteDatabase

    init {
        (app as NotelyApplication).appComponent.inject(this)

        if (id > 0) {
            getNote()
        }
    }

    var note: Note = Note()

    fun getNote() {
        Thread(Runnable { note = db.noteDao().getNote(id) }).start()
        //  putFav()

    }
}