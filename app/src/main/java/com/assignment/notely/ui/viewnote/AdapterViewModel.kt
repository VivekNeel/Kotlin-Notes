package com.assignment.notely.ui.viewnote

import android.app.Application
import android.content.Intent
import android.databinding.BaseObservable
import com.assignment.notely.Constants
import com.assignment.notely.NotelyApplication
import com.assignment.notely.R
import com.assignment.notely.db.NoteDatabase
import com.assignment.notely.db.entities.Note
import com.assignment.notely.ui.detail.DetailViewActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by vivek on 21/01/18.
 */
class AdapterViewModel(var app: Application) : BaseObservable() {

    @Inject
    lateinit var db: NoteDatabase

    init {
        (app as NotelyApplication).appComponent.inject(this)
    }

    lateinit var note: Note
    private var currentTimeFormat = SimpleDateFormat("MMM dd HH:mm a", Locale.getDefault())
    private val todayTimeFormat = SimpleDateFormat("HH:mm a", Locale.getDefault())

    private var passTimeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun getNoteTitle() = note.title
    fun getNoteText() = note.description

    fun getNoteDate(): String {
        val today = Date()
        return if (note.createdAt.day == today.day) {
            val time = todayTimeFormat.format(note.createdAt)
            "Today at $time"
        } else if (note.createdAt.month == today.month && note.createdAt.year == note.createdAt.year)
            currentTimeFormat.format(note.createdAt)
        else
            passTimeFormat.format(note.createdAt)
    }

    fun onClick() {
        val intent = Intent(app, DetailViewActivity::class.java)
        intent.putExtra(Constants.EXTRAS_NOTE_ID, note.id)
        app.startActivity(intent)
    }

    fun onFavClick() {
        Thread({
            note.markedFav = true
            db.noteDao().insertNote(note)
            notifyPropertyChanged(R.id.fav)
            notifyChange()
        }).start()


    }

    fun onStarredClick() {
        Thread({
            note.markedStar = true
            db.noteDao().insertNote(note)
            notifyPropertyChanged(R.id.star)
            notifyChange()
        }).start()

    }

    fun deleteNote(note: Note) {
        Thread({ db.noteDao().deleteNote(note) })
    }

}