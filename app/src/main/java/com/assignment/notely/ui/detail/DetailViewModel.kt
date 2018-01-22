package com.assignment.notely.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.assignment.notely.NotelyApplication
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class DetailViewModel(app: Application, var id: Int = -1) : AndroidViewModel(app) {

    @Inject
    lateinit var db: NoteDatabase
    private var currentTimeFormat = SimpleDateFormat("MMM dd HH:mm a", Locale.getDefault())
    private val todayTimeFormat = SimpleDateFormat("HH:mm a", Locale.getDefault())

    private var passTimeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())



    init {
        (app as NotelyApplication).appComponent.inject(this)

        if (id > 0) {
            getNote()
        }
    }

    var note: Note = Note()

    fun getNoteDate(): String {
        val today = Date()
        return if (note.createdAt.day == today.day) {
            val time = todayTimeFormat.format(note.createdAt)
            "Last Updated :Today at $time"
        } else if (note.createdAt.month == today.month && note.createdAt.year == note.createdAt.year)
            currentTimeFormat.format(note.createdAt)
        else
            passTimeFormat.format(note.createdAt)
    }

    private fun getNote() {
        Thread(Runnable { note = db.noteDao().getNote(id) }).start()
    }
}