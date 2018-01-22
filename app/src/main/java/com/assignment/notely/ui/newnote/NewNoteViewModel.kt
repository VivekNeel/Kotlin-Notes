package com.assignment.notely.ui.newnote

import android.arch.lifecycle.AndroidViewModel
import com.assignment.notely.NotelyApplication
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import java.util.*
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class NewNoteViewModel(private var listener: () -> Unit, app: NotelyApplication, var id: Int = -1) : AndroidViewModel(app) {


    @Inject
    lateinit var db: NoteDatabase

    var note: Note = Note()

    fun getNote() {
        Thread(Runnable { note = db.noteDao().getNote(id) }).start()

    }

    init {
        app.appComponent.inject(this)

        if (id > 0) {
            getNote()
        }
    }


    fun insertNote() {
        note.createdAt = Date()
        Thread({
            db.noteDao().insertNote(note)
        }).start()
        listener.invoke()
    }
}